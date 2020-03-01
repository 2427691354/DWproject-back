package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbDuty;
import com.lixing.siitep.mapper.TbDutyMapper;
import com.lixing.siitep.service.AboutTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutTeacherServiceImpl implements AboutTeacherService {
    @Autowired
    TbDutyMapper tbDutyMapper;

    @Override
    public String selectDutyTeacherBytime() {
        String teacher=tbDutyMapper.selectDutyTeacherByTime();
        return teacher;
    }
}
