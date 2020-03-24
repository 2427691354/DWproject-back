package com.lixing.siitep.controller;

import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
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

    @GetMapping("/sum")
    @ApiOperation("宏观统计 总人数")
    public Iterator<BasicDBObject> sum(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("upTime").count().as("总人数")
        );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation,"rpt",BasicDBObject.class);
        Iterator<BasicDBObject> iterator=results.iterator();
        return iterator;
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

            //改变_id的
            map.put("upTime",date.get("_id"));
            map.put("count",mongoTemplate.count(query,"rpt"));
            result.add(map);
        }

        return result;
    }



    @GetMapping("/getStuInSuZhou")
    @ApiOperation("在苏州人数")
    private Iterator<BasicDBObject> stuInSuZhou(){
        List<Map<String,Object>> result = new ArrayList<>();
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("locationCity").is("苏州 ")),
                Aggregation.group("upTime").count().as("在苏州人数")
        );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(agg,"rpt",BasicDBObject.class);
        Iterator<BasicDBObject> iterator=results.iterator();
        return iterator;
    }

    @GetMapping("/getStuInJiangSu")
    @ApiOperation("在江苏人数")
    private Iterator<BasicDBObject> stuInJiangsu(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("locationProvince").is("江苏")),
                Aggregation.group("upTime").count().as("在江苏人数")
        );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(agg,"rpt",BasicDBObject.class);
        Iterator<BasicDBObject> iterator=results.iterator();
        return iterator;
    }


    @GetMapping("/getStuNotNormal")
    @ApiOperation("不正常人数")
    public List<Map<String,Object>> stuNotNormal(){
        List<Map<String,Object>> result = new ArrayList<>();

        // 输入null查询全部，输入长度，可查询最近几天的数据
        List<Map<String,Object>> dateList = mongoService.getDateList(null);
        List key= Arrays.asList("有发热、咳嗽、呼吸困难等可以症状", "新冠疑似", "新冠确诊", "新冠排除");
        for(Map<String,Object> date:dateList){
            Map<String,Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
            query.addCriteria(Criteria.where("physicalCondition").in(key));
            //显示分组字段，不显示_id
            map.put("upTime",date.get("_id"));
            map.put("count",mongoTemplate.count(query,"rpt"));
            result.add(map);
        }

        return result;
    }


    @GetMapping("/getStuInProvince")
    @ApiOperation("统计学生各省物理分布人数")
    private Map<String,Object> StuInProvince(){
        Map<String,Object> date=mongoService.getDateList(1).get(0);
        System.err.println(date);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("locationProvince").count().as("总人数")
        );
        return mongoTemplate.aggregate(aggregation,"rpt",HashMap.class).getRawResults();



    }




    @GetMapping("getCodeColor")
    @ApiOperation("每日绿码的持有量")
    private Iterator<BasicDBObject> codeColor(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("codeColor").is("绿码")),
                Aggregation.group("upTime").count().as("所持有绿码人数")
        );
        AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(agg,"rpt",BasicDBObject.class);
        Iterator<BasicDBObject> iterator=results.iterator();
        return iterator;
    }

}
