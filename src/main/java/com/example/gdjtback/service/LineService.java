package com.example.gdjtback.service;

import com.example.gdjtback.entity.Line;

import java.util.List;

public interface LineService {

    List<Line> getAll();

    //根据id查询某条线路信息
    Line findByLineIdOrName(Line line);

}
