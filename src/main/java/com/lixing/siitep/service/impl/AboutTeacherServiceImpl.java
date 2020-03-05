package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbDuty;
import com.lixing.siitep.mapper.TbDutyMapper;
import com.lixing.siitep.service.AboutTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutTeacherServiceImpl implements AboutTeacherService {
    @Autowired
    TbDutyMapper tbDutyMapper;

    @Override
    public List<TbDuty> selectDutyTeacherBytime() {
        //String teacher=tbDutyMapper.selectDutyTeacherByTime();
        return tbDutyMapper.selectDutyTeacherByTime();
    }

    @Override
    public List<TbDuty> selectTeacher() {
        return tbDutyMapper.selectTeacher();
    }
}
