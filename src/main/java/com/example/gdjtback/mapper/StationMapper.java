package com.example.gdjtback.mapper;

import com.example.gdjtback.entity.Station;
import com.example.gdjtback.entity.StationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationMapper {
    long countByExample(StationExample example);

    int deleteByExample(StationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Station record);

    int insertSelective(Station record);

    List<Station> selectByExample(StationExample example);

    Station selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Station record, @Param("example") StationExample example);

    int updateByExample(@Param("record") Station record, @Param("example") StationExample example);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);

    List<Station> selectStationByLineId(String lineID);


}