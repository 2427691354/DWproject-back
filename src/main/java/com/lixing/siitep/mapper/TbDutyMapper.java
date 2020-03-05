package com.lixing.siitep.mapper;

import com.lixing.siitep.entity.TbDuty;
import com.lixing.siitep.entity.TbDutyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDutyMapper {
    long countByExample(TbDutyExample example);

    int deleteByExample(TbDutyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDuty record);

    int insertSelective(TbDuty record);

    List<TbDuty> selectByExample(TbDutyExample example);

    TbDuty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDuty record, @Param("example") TbDutyExample example);

    int updateByExample(@Param("record") TbDuty record, @Param("example") TbDutyExample example);

    int updateByPrimaryKeySelective(TbDuty record);

    int updateByPrimaryKey(TbDuty record);
    //返回当日值班教师姓名  号码
    List<TbDuty> selectDutyTeacherByTime();
    //轮播所有教师信息
    List<TbDuty> selectTeacher();
}