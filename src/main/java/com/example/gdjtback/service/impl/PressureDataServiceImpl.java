package com.example.gdjtback.service.impl;

import com.example.gdjtback.entity.PressureData;
import com.example.gdjtback.mapper.PressureDataMapper;
import com.example.gdjtback.service.PressureDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressureDataServiceImpl implements PressureDataService {

    PressureDataMapper pressureDataMapper;
    @Override
    public List<PressureData> findWeeklyPressureDataBySensorId(String sensorID) {
        return pressureDataMapper.findWeeklyPressureDataBySensorId(sensorID);
    }
}
