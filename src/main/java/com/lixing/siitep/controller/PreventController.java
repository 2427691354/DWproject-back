package com.lixing.siitep.controller;


import com.lixing.siitep.entity.TbDiagnosis;
import com.lixing.siitep.entity.TbGuide;
import com.lixing.siitep.entity.TbRumor;
import com.lixing.siitep.service.DiagnosisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "防疫知识三个接口检查诊断 / 预防指南 / 谣言")
@RequestMapping("/prevent")
public class PreventController {
    @Autowired
    DiagnosisService diagnosisService;


    @GetMapping("/selectFromDiagnosisTitle")
    @ApiOperation("检查诊断")
    public List<TbDiagnosis> selectFromDiagnosisTitle(){
        return diagnosisService.selectFromDiagnosisTitle();
    }

    @GetMapping("/selectFromGuideTitle")
    @ApiOperation("预防指南")
    public List<TbGuide> selectFromGuideTitle(){

        return diagnosisService.selectFromGuideTitle();
    }

    @GetMapping("/selectRumorTitle")
    @ApiOperation("谣言")
    public List<TbRumor> selectRumorTitle(){
        return diagnosisService.selectFromRumorTitle();
    }



}
