package com.example.gdjtback.service;

import com.example.gdjtback.entity.User;

import java.util.List;

public interface UserService {
    String register(User user);

   // public User dologin(User user);
    List<User> dologin(User user);

}
