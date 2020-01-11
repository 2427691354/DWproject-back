package com.example.gdjtback.service;

import com.example.gdjtback.entity.Station;

import java.util.HashMap;
import java.util.List;

public interface StationService {
    List<Station> findByLineID(String lineID);
    List<HashMap> getSensorNewInfoByStationID(String stationID);


}
