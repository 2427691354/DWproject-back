package com.example.gdjtback.service;

import com.example.gdjtback.entity.PressureData;

import java.util.List;

public interface PressureDataService {
    public List<PressureData> findWeeklyPressureDataBySensorId(String sensorID);
}
