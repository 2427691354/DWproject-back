package com.lixing.siitep.controller;


import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.entity.TblRecord;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.DayRptService;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.*;


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

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("sum")
    @ApiOperation("宏观统计 总人数、隔离人数、发烧人数")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
    private Map<String, Object> test(@RequestParam(name = "day",required = false)String day)
    {
        return dayRptService.sum(day);
    }


    @GetMapping("getStuInProvince")
    @ApiOperation("统计学生各省物理分布人数")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
    private List<TbDayrpt> getStuInProvince(@RequestParam(name = "day",required = false)String day){
            List<TbDayrpt> StuCount = dayRptService.StuInProvince(day);
            return StuCount;
    }
    @GetMapping("getStuHotInProvince")
    @ApiOperation("统计学生各省发烧分布人数")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
    private List<TbDayrpt> getStuHotInProvince(@RequestParam(name = "day",required = false)String day){
        List<TbDayrpt> StuHotCount=dayRptService.StuHotInProvince(day);
        return StuHotCount;
    }
    @GetMapping("getStuIsolatedInProvince")
    @ApiOperation("统计学生各省隔离分布人数")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
    private List<TbDayrpt> getStuIsolatedInProvince(@RequestParam(name = "day",required = false)String day){
        List<TbDayrpt> StuIsolatedCount=dayRptService.StuIsolatedInProvince(day);
        return StuIsolatedCount;
    }

    @GetMapping("getFocusStu")
    @ApiOperation("重点关注学生")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
    private List<TbDayrpt> getFocusStu(@RequestParam(name = "day",required = false)String day){
        List<TbDayrpt> FocusStu=dayRptService.FocusStu(day);
        return FocusStu;
    }
    @GetMapping("getTemperatureGradeRatio")
    @ApiOperation("体温等级比例")
    @ApiImplicitParam(value = "日期",name = "day",required = false,dataType = "String")
     private List<TbDayrpt> getTemperatureGradeRatio(@RequestParam(name = "day",required = false)String day){
        List<TbDayrpt> TemperatureGradeRatio=dayRptService.getTemperatureGradeRatio(day);
        return TemperatureGradeRatio;
    }

    @GetMapping("getNewTime")
    @ApiOperation("最后创建表时间")
    private String NewTime(){
        return dayRptService.NewTime();
    }

    @GetMapping("getStuInSuZhou")
    @ApiOperation("在苏人数，江苏人数")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "城市（苏州）",name = "city",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "省份（江苏）",name = "province",required = false,dataType = "String")
            }
    )
    private List<TbDayrpt> getStuInSuZhou(@RequestParam(name = "city",required = false)String  city,
                                          @RequestParam(name = "province",required = false)String province)
    {
        return dayRptService.StuInSuZhou(city,province);
    }

    @GetMapping("getFeverTrend")
    @ApiOperation("发烧人数趋势")
    private List<TbDayrpt> stuFeverTrend(){
        return dayRptService.StuFeverTrend();
    }


    @GetMapping("getStuIsolated")
    @ApiOperation("隔离人数趋势")
    private List<TbDayrpt> stuIsolated(){
        return dayRptService.StuIsolated();
    }



}
