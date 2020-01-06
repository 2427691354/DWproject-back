package com.example.gdjtback.mapper;

import com.example.gdjtback.entity.Line;
import com.example.gdjtback.entity.LineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LineMapper {

    List<Line> selectByExample(LineExample example);

}