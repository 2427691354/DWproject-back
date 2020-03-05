package com.lixing.siitep.service.impl;

import com.lixing.siitep.mapper.TbWorkAttendanceMapper;
import com.lixing.siitep.service.WorkAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WorkAttendanceImpl implements WorkAttendanceService {
    @Autowired
    private TbWorkAttendanceMapper tbWorkAttendanceMapper;

    @Override
    public Map<String, Object> sum() {
        return tbWorkAttendanceMapper.sum();
    }

    @Override
    public Map<String, Object> getMoM() {
        return tbWorkAttendanceMapper.getMoM();
    }
}
