package com.example.gdjtback.service.impl;

import com.example.gdjtback.entity.User;
import com.example.gdjtback.mapper.UserMapper;
import com.example.gdjtback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String register(User user) {
        System.err.println(user.getUsername());
        int i=userMapper.insert(user);
        String message="";
        if(i>0) {
            message="添加成功";
        }else {
            message="添加失败";
        }
        return message;
    }
}
