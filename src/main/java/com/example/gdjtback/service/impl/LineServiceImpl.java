package com.example.gdjtback.service.impl;

import com.example.gdjtback.entity.Line;
import com.example.gdjtback.entity.LineExample;
import com.example.gdjtback.mapper.LineMapper;
import com.example.gdjtback.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineMapper lineMapper;

    @Override
    public List<Line> findByExample(Line line) {
        LineExample example = new LineExample();
        LineExample.Criteria criteria = example.createCriteria();

        if(line.getLineid() != null){
            criteria.andLineidEqualTo(line.getLineid());
            System.err.println(line.getLineid());
        }
        if(line.getLinename() != null ){
            criteria.andLinenameEqualTo(line.getLinename());
            System.err.println(line.getLinename());
        }

        return lineMapper.selectByExample(example);
    }
}
