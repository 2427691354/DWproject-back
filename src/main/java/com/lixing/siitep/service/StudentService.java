package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbStudent;
import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.Tbrpt;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Map<String,Object>> getDateList(Integer length);
    List<Map<String,Object>> getCityOrProvince(String city,String province);
    List<TbTeacher> teacherLogin(String teacher_id, String password);
    List<Map<String, Object>> getStudentTripBySID(String sId, Date s, Date e);
    List<TbStudent> getStudentBySID(String sId);
    /**
     * 两表联查
     * @return
     */
    //Object findStudentAndTest();

}
