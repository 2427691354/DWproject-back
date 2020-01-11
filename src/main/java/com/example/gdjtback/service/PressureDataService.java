package com.example.gdjtback.service;

import com.example.gdjtback.entity.PressureData;

import java.util.List;

public interface PressureDataService {
    public List<PressureData> findWeeklyPressureDataByDeviceID(String deviceID,String sensorID);
    List<PressureData> getDeviceInfoByDeviceID(String deviceID);
}
