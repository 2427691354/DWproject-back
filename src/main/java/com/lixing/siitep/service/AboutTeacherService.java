package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbDuty;

import java.util.List;

public interface AboutTeacherService {
    List<TbDuty> selectDutyTeacherBytime();
    List<TbDuty> selectTeacher();
}
