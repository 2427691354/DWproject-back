package com.example.gdjtback.controller;

import com.example.gdjtback.entity.Line;
import com.example.gdjtback.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LineController {

    @Autowired
    LineService lineService;

    @GetMapping("/getAll")
    public List<Line> getAll(){
        return lineService.getAll();
    }

    @GetMapping("/findByIdOrName")
    public Line findByIdOrName(@RequestParam(value = "id",required = false)String id,
                               @RequestParam(value = "name",required = false)String name){

        Line line = new Line();
        line.setLineid(id);
        line.setLinename(name);
        return lineService.findByLineIdOrName(line);
    }
}
