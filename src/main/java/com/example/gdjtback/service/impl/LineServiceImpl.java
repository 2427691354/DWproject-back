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
    public List<Line> getAll() {
        return lineMapper.selectByExample(null);
    }

    @Override
    public Line findByLineIdOrName(Line line) {
        LineExample example = new LineExample();
        LineExample.Criteria criteria = example.createCriteria();

        if(line.getLineid() != null){
            criteria.andLineidEqualTo(line.getLineid());
        }
        if(line.getLinename() != null ){
            criteria.andLinenameEqualTo(line.getLinename());
        }
        List<Line> lines = lineMapper.selectByExample(example);
        if(lines.size()>0){
            return lines.get(0);
        }
        else{
            return null;
        }
    }
}
