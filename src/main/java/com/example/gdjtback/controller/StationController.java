package com.example.gdjtback.controller;

import com.example.gdjtback.comm.util.Result;
import com.example.gdjtback.comm.util.ResultUtil;
import com.example.gdjtback.entity.Station;
import com.example.gdjtback.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="StationController",tags={"站点"})
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    StationService stationService;

    @ApiOperation(value = "获取站点信息")
    @ApiImplicitParam(name = "lineID",value = "线路id",required = false,dataType = "String")
    @GetMapping("/selectStationByLineID")
   public Result selectStationByLineID(@RequestParam(value = "lineID",required = false)String lineID){

        List<Station> stations=stationService.findByLineID(lineID);
        return ResultUtil.success(stations);


   }
}
