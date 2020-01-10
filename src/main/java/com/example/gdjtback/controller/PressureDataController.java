package com.example.gdjtback.controller;


import com.example.gdjtback.comm.util.Result;
import com.example.gdjtback.comm.util.ResultUtil;
import com.example.gdjtback.entity.PressureData;
import com.example.gdjtback.service.PressureDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "PressureDataController",tags = {"传感器气瓶气压数据"})
@RequestMapping("/PressureData")
@RestController
public class PressureDataController {

    @Autowired
    PressureDataService pressureDataService;

    @ApiOperation(value = "获取气压数据")
    @ApiImplicitParam(name = "sensorID",value = "气瓶id",required = true,dataType = "String")
    @GetMapping("/findWeeklyPressureDataBySensorId")
    public Result findWeeklyPressureDataByDeviceID(@RequestParam(value = "sensorID",required = true) String deviceID){
        List<PressureData> pressureData = pressureDataService.findWeeklyPressureDataByDeviceID(deviceID);
            return ResultUtil.success(pressureData);
    }
    @GetMapping("/test")
    public String test(){
        return "测试";
    }

    @ApiOperation(value = "获取站内气瓶最新信息")
    @ApiImplicitParam(name = "deviceID",value = "站点id",required = false,dataType = "String")
    @GetMapping("/getDeviceInfoByDeviceID")
    public Result getDeviceInfoByDeviceID(@RequestParam(value = "deviceID",required = false) String deviceID){
        List<PressureData> pressureDataInfo=pressureDataService.getDeviceInfoByDeviceID(deviceID);
        return  ResultUtil.success(pressureDataInfo);
    }






}
