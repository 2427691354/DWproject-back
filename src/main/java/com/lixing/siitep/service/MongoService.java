package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbTeacher;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 2020/03/23
 **/
public interface MongoService {

    List<Map<String,Object>>  getDateList(Integer length);
    List<Map<String,Object>> getCityOrProvince(String city,String province);
    List<Map<String, Object>> getStudentTrip(String sId, String time);
    List<TbTeacher> teacherLogin(String teacher_id,String password);

    List<Map<String, Object>> getStudentTripBySID(String sId, Date s,Date e);

}
