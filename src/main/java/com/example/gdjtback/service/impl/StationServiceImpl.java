package com.example.gdjtback.service.impl;


import com.example.gdjtback.entity.Station;
import com.example.gdjtback.mapper.StationMapper;
import com.example.gdjtback.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationMapper stationMapper;

    @Override
    public List<Station> findByLineID(String lineID) {
        return stationMapper.selectStationByLineId(lineID);
    }

    @Override
    public List<HashMap> getSensorNewInfoByStationID(String stationID) {
        return stationMapper.getSensorNewInfoByStationID(stationID);
    }


}
