package xyz.yangkai.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import xyz.yangkai.spring.demo.common.req.UserReq;
import xyz.yangkai.spring.demo.common.resp.DemoResp;
import xyz.yangkai.spring.demo.common.resp.PublicPrivateKey;
import xyz.yangkai.spring.demo.domain.Ppk;
import xyz.yangkai.spring.demo.domain.Session;
import xyz.yangkai.spring.demo.domain.Users;

import xyz.yangkai.spring.demo.mapper.SessionMapper;
import xyz.yangkai.spring.demo.mapper.UserMapper;
import xyz.yangkai.spring.demo.utils.RSAEncrypt;
import xyz.yangkai.spring.demo.utils.RedisCacheUtil;


import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SessionMapper sessionMapper;
    /*
    生成公钥私钥存入数据库
    */
    public DemoResp producePpk(){
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        try {
            keyMap=RSAEncrypt.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Integer oldVersion= userMapper.getVersion();
        Ppk publicPrivateKey=new Ppk();
        publicPrivateKey.setPublic_key(keyMap.get(0));
        publicPrivateKey.setPrivate_key(keyMap.get(1));
        publicPrivateKey.setVersion(oldVersion+1);
        userMapper.savePpk(publicPrivateKey);
        return DemoResp.builder().msg("私钥公钥生成成功！").statusCode(200).build();
    }

    /*
    提供公钥和版本号
    */
    public PublicPrivateKey getPublicKeyAndVersion(){
        Ppk publicPrivateKey=userMapper.getPublicPrivateKey();
        PublicPrivateKey resp=new PublicPrivateKey();
        resp.setPublicKey(publicPrivateKey.getPublic_key());
        resp.setVersion(publicPrivateKey.getVersion());
        return resp;
    }

    /*
    根据版本号解密
     */
    public DemoResp decrypyByVersion(UserReq req){
        if(userMapper.getUserByUserName(req.getUsername())>0){
            return DemoResp.builder().msg("用户名已经存在，请重新输入！").statusCode(100).build();
        }
        Integer version=req.getVersion();
        String privateKey=new String();
        if(version==null || "".equals(version)){
            privateKey = userMapper.getPrivateKeyByVersion(0).getPrivate_key();
        }else {
            privateKey=userMapper.getPrivateKeyByVersion(version).getPrivate_key();
        }
        String password = null;
        try {
            password=RSAEncrypt.decrypt(req.getPassword(),privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Users user= new Users();
        user.setUsername(req.getUsername());
        user.setPassword(password);
        user.setIsDel(0);
        userMapper.save(user);
        DemoResp resp=DemoResp.builder().msg("注册成功").statusCode(200).build();
        return resp;

    }
    /*
    校验session,返回用户id
     */
    public DemoResp verifySession(String sessionId){
        Users users=sessionMapper.getUserBySessionId(sessionId);
        if(users==null){
            return DemoResp.builder().msg("登录过期，请重新登录！").statusCode(100).build();
        }
        return DemoResp.builder().userId(users.getId()).build();
    }


    /*
     登录，返回sessionid
     */
    public DemoResp login(UserReq userReq){
        Integer version=userReq.getVersion();
        String privateKey=new String();
        if(version==null || "".equals(version)){
            privateKey = userMapper.getPrivateKeyByVersion(0).getPrivate_key();
        }else {
            privateKey=userMapper.getPrivateKeyByVersion(version).getPrivate_key();
        }
        String password = null;
        try {
            password=RSAEncrypt.decrypt(userReq.getPassword(),privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Users users=Users.builder().username(userReq.getUsername()).password(password).build();
        //验证用户名密码是否存在
        Users userInfo=userMapper.getUserByUsernameAndPassword(users);
        if(userInfo==null){
            return DemoResp.builder().msg("用户名或密码错误，请重新输入！").statusCode(100).build();
        }
        String sessionId=generateSessionId(userInfo.getId());
        return DemoResp.builder().msg("登录成功！").statusCode(200).sessionId(sessionId).build();
    }

    /*
    登出
     */
    public void logout(String sessionId){
        if(sessionId!=null || !("".equals(sessionId))){
            sessionMapper.deleteSession(sessionId);
        }
    }

    /*
    生成sessionId
     */
    public String generateSessionId(Integer id){
        String sessionId= UUID.randomUUID().toString();
        Session session=new Session();
        session.setUser_id(id);
        session.setSession_id(sessionId);
//        Map<String,String> sessionMap=new HashMap<>();
//        sessionMap.put(id.toString(),sessionId);
//        RedisCacheUtil.setCacheMap("01",sessionMap);
        sessionMapper.saveSession(session);
        return sessionId;
    }


    /*
    缓存
     */

}
