package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.entity.TbGuide;
import com.lixing.siitep.service.EpKnowService;
import com.lixing.siitep.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cc
 * @date 2020/02/24
 **/
@RestController
@RequestMapping("/epknow/")
public class EpKnowController {

    @Autowired
    private EpKnowService epKnowService;


}
