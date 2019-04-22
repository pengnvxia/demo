package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import common.req.UserReq;
import common.resp.DemoResp;
import common.resp.PublicPrivateKey;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

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

}
