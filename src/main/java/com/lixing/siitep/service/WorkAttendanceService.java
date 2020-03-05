package com.lixing.siitep.service;

import java.util.Map;

public interface WorkAttendanceService {
    Map<String,Object> sum();
    //请假/旷课比
    Map<String,Object> getMoM();
}
