package com.lixing.siitep.service;

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



}
