package com.example.gdjtback.mapper;

import com.example.gdjtback.entity.PressureData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PressureDataMapper {
    public List<PressureData> findWeeklyPressureDataByDeviceID(String deviceID);
    List<PressureData> getDeviceInfoByDeviceID(String deviceID);
}
