package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cc
 * @Date: 2020/2/24
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    private String test(){
        TbDayrpt tbDayrpt = new TbDayrpt();
        return "Hello World!";
    }
}
