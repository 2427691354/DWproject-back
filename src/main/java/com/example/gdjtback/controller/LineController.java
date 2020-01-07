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
@Api(value="LineController",tags={"线路接口"})
@RestController
@RequestMapping("/line")
public class LineController {

    @Autowired
    LineService lineService;

    @ApiOperation(value = "获取全部线路信息")
    @GetMapping("/getAll")
    public List<Line> getAll(){
        return lineService.getAll();
    }

    @ApiOperation(value = "获取某条线路信息", notes="通过线路id或线路名获取对应线路信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "线路id", paramType = "query ", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "线路名称", paramType = "query ", dataType = "String")
    })
    @GetMapping("/findByIdOrName")
    public Line findByIdOrName(String id,String name){
        Line line = new Line();
        line.setLineid(id);
        line.setLinename(name);
        return lineService.findByLineIdOrName(line);
    }
}
