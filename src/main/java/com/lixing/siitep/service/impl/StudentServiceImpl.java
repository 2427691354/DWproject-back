package com.lixing.siitep.service.impl;


import com.alibaba.fastjson.JSON;
import com.lixing.siitep.entity.TbStudent;
import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map<String,Object>>  getDateList(Integer length) {
        List<Map<String,Object>> dateList = new ArrayList<>();

        List<AggregationOperation> operations = new ArrayList<>();
        GroupOperation groupOperation = Aggregation.group("upTime").count().as("count");
        operations.add(groupOperation);

        operations.add(Aggregation.sort(Sort.Direction.DESC, "_id"));

        if(length!=null){
            operations.add(Aggregation.limit(length));
        }

        Aggregation aggregation = Aggregation.newAggregation(operations);
        dateList = (List<Map<String, Object>>) mongoTemplate.aggregate(aggregation, "test", HashMap.class).getRawResults().get("results");
        System.err.println(dateList.toArray());
        return dateList;

    }

    @Override
    public List<Map<String, Object>> getCityOrProvince(String city, String province) {

        List<Map<String,Object>> dateList = new ArrayList<>();

        List<AggregationOperation> operations = new ArrayList<>();
        GroupOperation groupOperation = Aggregation.group("upTime").count().as("count");
        operations.add(groupOperation);


        operations.add(Aggregation.sort(Sort.Direction.DESC, "_id"));

        operations.add(Aggregation.limit(7));

        Aggregation aggregation = Aggregation.newAggregation(operations);
        dateList = (List<Map<String, Object>>) mongoTemplate.aggregate(aggregation, "test", HashMap.class).getRawResults().get("results");

        return dateList;

    }

    @Override
    public List<TbTeacher> teacherLogin(String teacher_id, String password) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("teacher_id").is(teacher_id),Criteria.where("password").is(password));
        List<TbTeacher> teachers = mongoTemplate.find(new Query(criteria),TbTeacher.class,"teacher");
        System.err.println(teachers.size());
        if(teachers.size()>0){
            return teachers;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getStudentTripBySID(String sId, Date s, Date e) {
        List<Map<String,Object>> dateList = new ArrayList<>();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("sId").is(sId),
                Criteria.where("upTime").gte(s).lte(e)
        );
        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.ASC,"upTime"));

        System.err.println(mongoTemplate.find(query, Tbrpt.class, "test"));
        return dateList;

    }

    @Override
    public List<TbStudent> getStudentBySID(String sId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sId").is(sId));
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC,"upTime"));
        return mongoTemplate.find(query, TbStudent.class, "student");
    }

//    @Override
//    public Object findStudentAndTest() {
//        LookupOperation lookupOperation=LookupOperation.newLookup().
//                from("test").  //关联从表名
//                localField("sId").     //主表关联字段
//                foreignField("_id").//从表关联的字段
//                as("stuedntinfo");   //查询结果名
//
//        Aggregation aggregation=Aggregation.newAggregation(lookupOperation);
//        List<Map> results = mongoTemplate.aggregate(aggregation,"student", Map.class).getMappedResults();
//        //上面的student必须是查询的主表名
//        System.out.println(JSON.toJSONString(results));
//        return results;
//    }


}
