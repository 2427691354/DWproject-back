package com.lixing.siitep.controller;

import com.lixing.siitep.service.WorkAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "考勤相关接口")
@RequestMapping("/workattendance/")
public class WorkAttendanceController {
    @Autowired
    private WorkAttendanceService workAttendanceService;
    @GetMapping("sum")
    @ApiOperation("宏观统计 系部总人数  应上课人数  缺勤人数 迟到人数")
    private Map<String,Object> sum(){
        return workAttendanceService.sum();
    }

    @GetMapping("getMoM")
    @ApiOperation("缺勤（请假/旷课人数）环比")
    private Map<String,Object> getMoM(){
        return workAttendanceService.getMoM();
    }


}
