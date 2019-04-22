package xyz.yangkai.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.yangkai.spring.demo.common.req.UserReq;
import xyz.yangkai.spring.demo.common.resp.DemoResp;
import xyz.yangkai.spring.demo.common.resp.PublicPrivateKey;
import xyz.yangkai.spring.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //生成私钥和公钥存入数据库，接口供生成私钥和公钥
    @GetMapping(value="/producePublicPrivateKey")
    @ResponseBody
    public DemoResp productPpk(){
        return userService.producePpk();
    }


    /**
     * @return publicKey,version
     */
    //提供公钥和版本号，客户端调用该接口，返回公钥和版本号
    @GetMapping(value="/getPublicAndVersion")
    public PublicPrivateKey getPublicKeyVersion(){
        return userService.getPublicKeyAndVersion();
    }

    /**
     * @param UserReq
     */
    //注册，传入加密的密码，将密码解密后存入数据库中
    @PostMapping(value="/register")
    @ResponseBody
    public DemoResp register(@RequestBody UserReq req){
        return userService.decrypyByVersion(req);
    }

    /**
     * @param UserReq
     * @return session
     */
    //登录，校验用户名和密码，返回session
    @PostMapping(value="/login")
    @ResponseBody
    public DemoResp login(@Valid @RequestBody UserReq req,HttpServletResponse response){
        DemoResp resp=userService.login(req);
        response.setHeader("session_id",resp.getSessionId());
        DemoResp newResp=DemoResp.builder().msg(resp.getMsg()).statusCode(resp.getStatusCode()).build();
        return newResp;
    }

    /**
     * @param sessionId
     * @return
     */
    //验证session，前端调用该接口验证session
    @GetMapping(value="/verifySession")
    @ResponseBody
    public DemoResp verifySession(HttpServletRequest request){
        return userService.verifySession(request.getHeader("session_id"));
    }

    /**
     * @param sessionId
     * @return
     */
    //登出，登出后删除数据库中的Session
    @GetMapping(value="/logout")
    @ResponseBody
    public void logout(HttpServletRequest request){
        userService.logout(request.getHeader("session_id"));
    }

}
