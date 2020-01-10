package com.example.gdjtback.service.impl;

import com.example.gdjtback.entity.PressureData;
import com.example.gdjtback.mapper.PressureDataMapper;
import com.example.gdjtback.service.PressureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressureDataServiceImpl implements PressureDataService {

    @Autowired
    PressureDataMapper pressureDataMapper;
    @Override
    public List<PressureData> findWeeklyPressureDataByDeviceID(String deviceID) {
        return pressureDataMapper.findWeeklyPressureDataByDeviceID(deviceID);
    }
}
