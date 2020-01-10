package com.example.gdjtback.service.impl;

import com.example.gdjtback.entity.LineExample;
import com.example.gdjtback.entity.User;
import com.example.gdjtback.entity.UserExample;
import com.example.gdjtback.mapper.UserMapper;
import com.example.gdjtback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String register(User user) {
        //  System.err.println(user.getUsername());
        String message = "";
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            message = "此用户名已存在";
        } else {
            int i = userMapper.insert(user);

            if (i > 0) {
                message = "添加成功";
            } else {
                message = "添加失败";
            }
        }
        return message;
    }

    public List<User> dologin(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPwdEqualTo(user.getPwd());

        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return userList;
        }
        return null;
    }

    @Override
    public User testLogin(User user) {
        User u = userMapper.testLogin(user.getUsername(),user.getPwd());
        if (u != null) {
            return u;
        }
        return null;
    }


}
