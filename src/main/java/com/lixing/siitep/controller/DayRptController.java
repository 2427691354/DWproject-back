package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbDayrpt;
import com.lixing.siitep.service.DayRptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: cc
 * @Date: 2020/2/24
 */
@RestController
@RequestMapping("/dayrpt/")
public class DayRptController {

    @Autowired
    private DayRptService dayRptService;


    // 宏观统计 总人数、隔离人数、发烧人数
    @GetMapping("sum")
    private Map<String, Object> test() {
        return dayRptService.sum();
    }
}
