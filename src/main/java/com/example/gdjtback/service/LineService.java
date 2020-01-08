package com.example.gdjtback.service;

import com.example.gdjtback.entity.Line;

import java.util.List;

public interface LineService {

    //根据条件查询线路信息，无条件时显示全部
    List<Line> findByExample(Line line);


}
