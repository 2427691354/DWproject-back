package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbDiagnosis;
import com.lixing.siitep.entity.TbGuide;
import com.lixing.siitep.entity.TbRumor;
import com.lixing.siitep.mapper.TbDiagnosisMapper;
import com.lixing.siitep.mapper.TbGuideMapper;
import com.lixing.siitep.mapper.TbRumorMapper;
import com.lixing.siitep.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    TbDiagnosisMapper tbDiagnosisMapper;

    @Autowired
    TbGuideMapper tbGuideMapper;

   @Autowired
   TbRumorMapper tbRumorMapper;

    @Override
    public List<TbDiagnosis> selectFromDiagnosisTitle() {

        return tbDiagnosisMapper.selectFromTitle();
    }

    @Override
    public List<TbGuide> selectFromGuideTitle() {
        return tbGuideMapper.selectFromTitle();
    }

    public List<TbRumor> selectFromRumorTitle(){
        return tbRumorMapper.selectRumortitle();
    }



}
