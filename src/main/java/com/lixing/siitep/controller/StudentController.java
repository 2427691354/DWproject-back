package com.lixing.siitep.controller;

import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoService mongoService;

@GetMapping("/list")
    public List<Tbrpt> list(){
        //指定文档
        List<Tbrpt> student = mongoTemplate.findAll(Tbrpt.class,"rpt");

        System.err.println(mongoTemplate.findAll(Tbrpt.class).size());
        return student;
    }

    // 参考
    @GetMapping("/list2")
    public List<Map<String,Object>> list2(){
        List<Map<String,Object>> result = new ArrayList<>();

        // 输入null查询全部，输入长度，可查询最近几天的数据
        List<Map<String,Object>> dateList = mongoService.getDateList(null);

        for(Map<String,Object> date:dateList){
            Map<String,Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
            query.addCriteria(Criteria.where("locationCity").is("苏州 "));

            map.put("upTime",date.get("_id"));
            map.put("count",mongoTemplate.count(query,"rpt"));
            result.add(map);
        }

        return result;
    }


}
