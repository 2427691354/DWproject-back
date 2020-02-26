package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbDayrpt;



import java.util.List;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2020/2/23
 */
public interface DayRptService {

    // 宏观统计 总人数、隔离人数、发烧人数
    Map<String,Object> sum();

    List<TbDayrpt> selectByExample(TbDayrpt tbDayrpt);
    //学生省份物理分布
    List<TbDayrpt> StuInProvince();
    //学生发烧人数分布省份
    List<TbDayrpt> StuHotInProvince();
    //学生隔离人数分布省份
    List<TbDayrpt> StuIsolatedInProvince();
    //体温等级比例
    List<TbDayrpt> getTemperatureGradeRatio();



}
