package ervice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import common.req.UserReq;
import common.resp.DemoResp;
import common.resp.PublicPrivateKey;
import domain.Ppk;
import domain.Session;
import domain.Users;

import mapper.SessionMapper;
import mapper.UserMapper;


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
    校验session,返回用户id
     */
    public DemoResp verifySession(String sessionId){
        Users users=sessionMapper.getUserBySessionId(sessionId);
        if(users==null){
            return DemoResp.builder().msg("登录过期，请重新登录！").statusCode(100).build();
        }
        return DemoResp.builder().userId(users.getId()).build();
    }
    
}
