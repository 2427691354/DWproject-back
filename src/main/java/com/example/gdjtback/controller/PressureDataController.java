package com.example.gdjtback.controller;


import com.example.gdjtback.entity.PressureData;
import com.example.gdjtback.service.PressureDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "PressureDataController",tags = {"传感器气瓶气压数据"})
@RequestMapping("/PressureData")
public class PressureDataController {

    @Autowired
    PressureDataService pressureDataService;

    @ApiOperation(value = "获取气压数据")
    @ApiImplicitParam(name = "sensorID",value = "气瓶id",required = true,dataType = "String")
    @GetMapping("/findWeeklyPressureDataBySensorId")
    public List<PressureData> findWeeklyPressureDataBySensorId(@RequestParam(value = "sensorID",required = true) String sensorID){

        return pressureDataService.findWeeklyPressureDataBySensorId(sensorID);
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }



}
