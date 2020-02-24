package com.lixing.siitep.controller;

import com.lixing.siitep.service.EpKnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
