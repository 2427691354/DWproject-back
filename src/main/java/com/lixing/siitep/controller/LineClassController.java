package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDuty;
import com.lixing.siitep.entity.TbWeibohotsearch;
import com.lixing.siitep.service.AboutTeacherService;
import com.lixing.siitep.service.ClassPlatformService;
import com.lixing.siitep.service.WeiboHotSearchService;
import com.lixing.siitep.service.WorkAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "网课相关接口")
@RequestMapping("/lineclass/")
public class LineClassController {
    @Autowired
    private WorkAttendanceService workAttendanceService;
    @Autowired
    ClassPlatformService classPlatformService;
    @Autowired
    private WeiboHotSearchService weiboHotSearchService;
    @Autowired
    AboutTeacherService aboutTeacherService;

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


    @GetMapping("platformCount")
    @ApiOperation("返回老师平台使用")
    private Map<String,Object> platformCount(){
        return classPlatformService.platformCount();
    }


    @GetMapping("selectweiboTitle")
    @ApiOperation("微博热搜")
    private List<TbWeibohotsearch> selectweiboTitle(){
        return weiboHotSearchService.selectTitle();
    }

    @GetMapping("dutyteacher")
    @ApiOperation("根据当前日期返回值日老师")
    public List<TbDuty> selectDutyTeacherByTime(){

        return aboutTeacherService.selectDutyTeacherBytime();
    }

    @GetMapping("selectTeacher")
    @ApiOperation("返回所有值班老师信息")
    private List<TbDuty> selectTeacher(){
        return aboutTeacherService.selectTeacher();
    }
}
