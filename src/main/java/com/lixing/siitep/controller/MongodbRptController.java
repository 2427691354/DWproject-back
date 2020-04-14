package com.lixing.siitep.controller;

import com.alibaba.fastjson.JSONObject;
import com.lixing.siitep.entity.Rrr;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/students")
public class MongodbRptController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoService mongoService;

    @GetMapping("/sum")
    @ApiOperation("宏观统计 总人数")
    public Object sum() throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> date = mongoService.getDateList(1).get(0);

        // 总人数
        Aggregation sum = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> s = ((List<Map<String,Object>>)mongoTemplate.aggregate(sum, "rpt", Rrr.class).getRawResults().get("results")).get(0);

        // 上报人数
        Aggregation sumsb = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("isReport").is(0)),
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> sb = ((List<Map<String,Object>>)mongoTemplate.aggregate(sumsb, "rpt", HashMap.class).getRawResults().get("results")).get(0);

        // 绿码人数
        Aggregation green = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> g = ((List<Map<String,Object>>)mongoTemplate.aggregate(green, "rpt", HashMap.class).getRawResults().get("results")).get(0);

        // 在苏州人数
        Aggregation InSu = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("locationCity").is("苏州 ")),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> js = ((List<Map<String,Object>>)mongoTemplate.aggregate(InSu, "rpt", HashMap.class).getRawResults().get("results")).get(0);

        // 在江苏 人数
        Aggregation InJiangSu = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("locationProvince").is("江苏")),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> sz = ((List<Map<String,Object>>)mongoTemplate.aggregate(InJiangSu, "rpt", HashMap.class).getRawResults().get("results")).get(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        result.put("sumAll",s.get("count"));
        result.put("upTime",sdf.format(new Date(s.get("_id").toString())));
        result.put("sumSB",sb.get("count"));
        result.put("sumGreen",g.get("count"));
        result.put("stuinJiang",js.get("count"));
        result.put("stuinSuzhou",sz.get("count"));

        return result;
    }

    @GetMapping("/getCityOrProvince")
    @ApiOperation("在某省份/城市人数近七天趋势")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "城市（苏州）",name = "city",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "省份（江苏）",name = "province",required = false,dataType = "String")
            }
    )
    public List<Map<String,Object>> getInJiangSu(@RequestParam(name = "city",required = false)String  city,
                                                 @RequestParam(name = "province",required = false)String province){

        List<Map<String,Object>> result = new ArrayList<>();
        if(city!=null) {
            List<Map<String, Object>> dateList = mongoService.getCityOrProvince(city,null);
            for (Map<String, Object> date : dateList) {
                Map<String, Object> map = new HashMap<>();
                Query query = new Query();
                query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
                query.addCriteria(Criteria.where("locationCity").is(city+" "));
                map.put("upTime", date.get("_id"));
                map.put("CityCount", mongoTemplate.count(query, "rpt"));
                result.add(map);
            }
        }
        if(province!=null) {
            List<Map<String, Object>> dateList = mongoService.getCityOrProvince(null, province);
            for (Map<String, Object> date : dateList) {
                Map<String, Object> map = new HashMap<>();
                Query query = new Query();
                query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
                query.addCriteria(Criteria.where("locationProvince").is(province));
                map.put("upTime", date.get("_id"));
                map.put("ProvinceCount", mongoTemplate.count(query, "rpt"));
                result.add(map);
            }
        }
        return result;
    }

    @GetMapping("/getStuInProvince")
    @ApiOperation("统计学生各省物理分布人数")
    private Map<String,Object> StuInProvince(){
        Map<String,Object> date=mongoService.getDateList(1).get(0);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("locationProvince").count().as("总人数")
        );
        return mongoTemplate.aggregate(aggregation,"rpt",HashMap.class).getRawResults();
    }



    @GetMapping("/getCodeRegisterCount")
    @ApiOperation("统计红黄绿码注册人数占比")
    private Map<String,Object> getCodeRegisterCount(){
        Map<String,Object> date=mongoService.getDateList(1).get(0);
        System.err.println(date);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("codeColor").count().as("持码人数")
        );
        return mongoTemplate.aggregate(aggregation,"rpt",HashMap.class).getRawResults();
    }

    @GetMapping("getNewTime")
    @ApiOperation("最后更新时间")
    private String NewTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> date=mongoService.getDateList(1).get(0);
        return sdf.format(new Date(date.get("_id").toString()));
    }


}
