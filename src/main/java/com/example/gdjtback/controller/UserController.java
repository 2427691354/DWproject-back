package com.example.gdjtback.controller;

import com.example.gdjtback.entity.User;
import com.example.gdjtback.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="用户controller",tags={"用户接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public String register(@RequestBody User user){
        if(user.getUsername()=="" || user.getUsername()==null){
            return "请输入用户信息";
        }
        return userService.register(user);
    }
}
