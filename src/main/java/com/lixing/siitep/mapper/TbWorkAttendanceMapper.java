package com.lixing.siitep.mapper;

import com.lixing.siitep.entity.TbWorkAttendance;
import com.lixing.siitep.entity.TbWorkAttendanceExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbWorkAttendanceMapper {
    long countByExample(TbWorkAttendanceExample example);

    int deleteByExample(TbWorkAttendanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbWorkAttendance record);

    int insertSelective(TbWorkAttendance record);

    List<TbWorkAttendance> selectByExample(TbWorkAttendanceExample example);

    TbWorkAttendance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbWorkAttendance record, @Param("example") TbWorkAttendanceExample example);

    int updateByExample(@Param("record") TbWorkAttendance record, @Param("example") TbWorkAttendanceExample example);

    int updateByPrimaryKeySelective(TbWorkAttendance record);

    int updateByPrimaryKey(TbWorkAttendance record);
    //宏观信息
    Map<String,Object> sum();
    //请假/旷课比
    Map<String,Object> getMoM();
}