package com.bear.controller;

import com.bear.model.User;
import com.bear.model.UserDetailModel.LoginUser;
import com.bear.service.self.LoginServiceImpl;
import com.bear.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginOutController {

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/loginout")
    public String loginout(){
        // 从SecurityContextHolder中获取到用户id，再删除掉redis中用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser)authentication.getPrincipal();
        String userName = principal.getUser().getUserName();
        // 从redis中删除信息
        // 使用用户id将redis中的相关数据删除掉


        return "登出成功";
    }

    // 先注册





}
