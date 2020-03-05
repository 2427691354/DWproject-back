package com.lixing.siitep.controller;

import com.lixing.siitep.service.ClassPlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "老师上网课使用平台统计接口")
@RequestMapping("/classplatform/")
public class ClassPlatformController {
    @Autowired
    ClassPlatformService classPlatformService;
    @GetMapping("platformCount")
    @ApiOperation("返回所有值班老师信息")
    private Map<String,Object> platformCount(){
        return classPlatformService.platformCount();
    }
}
