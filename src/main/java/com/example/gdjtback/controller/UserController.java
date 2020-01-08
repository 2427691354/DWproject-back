package com.example.gdjtback.controller;

import com.example.gdjtback.comm.util.Result;
import com.example.gdjtback.comm.util.ResultUtil;
import com.example.gdjtback.entity.User;
import com.example.gdjtback.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(user.getUsername()==null || user.getPwd()==null){
            return ResultUtil.warn("请输入用户名或密码");
        }

        List<User> userList=userService.dologin(user);
        if(userList == null){
            return ResultUtil.warn("用户不存在");
        }
        return ResultUtil.success(userList);

    }
}
