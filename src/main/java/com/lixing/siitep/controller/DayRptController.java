package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.service.DayRptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2020/2/24
 */
@RestController
@Api(tags = "日报统计相关接口")
@RequestMapping("/dayrpt/")
public class DayRptController {

    @Autowired
    private DayRptService dayRptService;

    @GetMapping("sum")
    @ApiOperation("宏观统计 总人数、隔离人数、发烧人数")
    private Map<String, Object> test() {
        return dayRptService.sum();
    }

    @GetMapping("getStuInProvince")
    @ApiOperation("统计学生各省物理分布人数")
    private List<TbDayrpt> getStuInProvince(){
        List<TbDayrpt> StuCount=dayRptService.StuInProvince();
        return StuCount;
    }

    @GetMapping("getStuHotInProvince")
    @ApiOperation("统计学生各省发烧分布人数")
    private List<TbDayrpt> getStuHotInProvince(){
        List<TbDayrpt> StuHotCount=dayRptService.StuHotInProvince();
        return StuHotCount;
    }

    @GetMapping("getStuIsolatedInProvince")
    @ApiOperation("统计学生各省隔离分布人数")
    private List<TbDayrpt> getStuIsolatedInProvince(){
        List<TbDayrpt> StuIsolatedCount=dayRptService.StuIsolatedInProvince();
        return StuIsolatedCount;
    }

}
