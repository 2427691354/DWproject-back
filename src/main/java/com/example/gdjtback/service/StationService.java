package com.example.gdjtback.service;

import com.example.gdjtback.entity.PressureData;
import com.example.gdjtback.entity.Station;

import java.util.List;

public interface StationService {
    List<Station> findByLineID(String lineID);
    List<PressureData> getStationInfoByStationID(String stationID);


}
