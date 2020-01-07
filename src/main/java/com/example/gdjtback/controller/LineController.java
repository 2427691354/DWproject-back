package com.example.gdjtback.controller;

import com.example.gdjtback.entity.Line;
import com.example.gdjtback.service.LineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cc on 2020/1/6
 */
@Api(value="线路controller",tags={"线路接口"})
@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService lineService;

    @ApiOperation(value = "获取线路信息", notes="根据条件查询线路信息，无条件时显示全部")
    @GetMapping("/findByExample")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "线路id",  required = false,dataType = "String"),
            @ApiImplicitParam(name = "name", value = "线路名称", required = false, dataType = "String")
    })
    public List<Line> findByExample(@RequestParam(value = "id",required = false) String id,@RequestParam(value = "name",required = false) String name){
        Line line = new Line();
        line.setLineid(id);
        line.setLinename(name);
        return lineService.findByExample(line);
    }

    @ApiOperation(value = "获取线路信息-Json", notes="根据Line对象查询线路信息")
    @PostMapping("/findByLine")
    public List<Line> findByExampleJson(@RequestBody  Line line){
        return lineService.findByExample(line);
    }
}
