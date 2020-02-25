package com.lixing.siitep.service;

import com.lixing.siitep.entity.TbDiagnosis;
import com.lixing.siitep.entity.TbGuide;
import com.lixing.siitep.entity.TbRumor;

import java.util.List;

public interface DiagnosisService {

    List<TbDiagnosis> selectFromDiagnosisTitle();
     List<TbGuide> selectFromGuideTitle();
    List<TbRumor> selectFromRumorTitle();
}
