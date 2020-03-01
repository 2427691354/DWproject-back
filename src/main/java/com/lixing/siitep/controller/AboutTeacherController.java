package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDuty;
import com.lixing.siitep.service.AboutTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "值日老师相关接口")
@RequestMapping("/duty/")
public class AboutTeacherController {
    @Autowired
    AboutTeacherService aboutTeacherService;

    @GetMapping("dutyteacher")
    @ApiOperation("根据当前日期返回值日老师")
    public String selectDutyTeacherByTime(){
        String teacher=aboutTeacherService.selectDutyTeacherBytime();
        return teacher;
    }

}
