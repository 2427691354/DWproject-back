package com.lixing.siitep.controller;

import com.alibaba.fastjson.JSONObject;
import com.lixing.siitep.entity.Rrr;
import com.lixing.siitep.service.MongoService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/getInJiangSu")
    @ApiOperation("在江苏人数")
    public List<Map<String,Object>> getInJiangSu(){
        List<Map<String,Object>> result = new ArrayList<>();
        List<Map<String,Object>> dateList = mongoService.getDateList(null);
        for(Map<String,Object> date:dateList){
            Map<String,Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
            query.addCriteria(Criteria.where("locationProvince").is("江苏"));
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



    @GetMapping("getpass")
    @ApiOperation("统计隔离人数")
    public List<Map<String,Object>> getpass(){
        List<Map<String,Object>> result = new ArrayList<>();
        List<Map<String,Object>> dateList = mongoService.getDateList(null);
        List key= Arrays.asList("浙江省温州市","湖北省黄冈市","河南省信阳市","安徽省阜阳市","湖北省荆门市");
        for(Map<String,Object> date:dateList){
            Map<String,Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
            query.addCriteria(Criteria.where("localPass").in(key));
            //显示分组字段，不显示_id
            map.put("upTime",date.get("_id"));
            map.put("count",mongoTemplate.count(query,"rpt"));
            result.add(map);
        }

        return result;
    }

    @GetMapping("/getNewDayStuPassInProvince")
    @ApiOperation("统计最新隔离学生各省物理分布人数")
    private Map<String,Object> getNewDayStuPassInProvince(){
        Map<String,Object> date=mongoService.getDateList(1).get(0);
        System.err.println(date);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("localPass").count().as("隔离人数"),
                Aggregation.limit(5)
        );
        return mongoTemplate.aggregate(aggregation,"rpt",HashMap.class).getRawResults();
    }

    @GetMapping("/getStuPassInProvince")
    @ApiOperation("统计隔离学生各省物理分布人数")
    public List<Map<String,Object>> getStuPassInProvince(){
        List<Map<String,Object>> result = new ArrayList<>();
        List<Map<String,Object>> dateList = mongoService.getDateList(null);
        for(Map<String,Object> date:dateList){
            System.err.println(date);
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                    Aggregation.group("localPass").count().as("隔离人数"),
                    Aggregation.limit(5)
            );
            result.add(mongoTemplate.aggregate(aggregation,"rpt",HashMap.class).getRawResults());
        }
        return result;
    }

    @GetMapping("getCodeRegisterCount")
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
