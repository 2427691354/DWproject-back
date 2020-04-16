package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author cc
 * @date 2020/03/23
 **/
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    // 不输入查询全部，输入长度，可查询最近几天的数据
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
            dateList = (List<Map<String, Object>>) mongoTemplate.aggregate(aggregation, "rpt", HashMap.class).getRawResults().get("results");
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
        dateList = (List<Map<String, Object>>) mongoTemplate.aggregate(aggregation, "rpt", HashMap.class).getRawResults().get("results");

        return dateList;

    }

    @Override
    public List<Map<String, Object>> getStudentTrip(String sId, String time) {

        List<Map<String,Object>> dateList = new ArrayList<>();

        List<AggregationOperation> operations = new ArrayList<>();
        GroupOperation groupOperation = Aggregation.group("upTime").count().as("count");
        operations.add(groupOperation);

       // operations.add(Aggregation.limit(14));
        operations.add(Aggregation.sort(Sort.Direction.DESC, "_id"));

        Aggregation aggregation = Aggregation.newAggregation(operations);
        dateList = (List<Map<String, Object>>) mongoTemplate.aggregate(aggregation, "rpt", HashMap.class).getRawResults().get("results");

        return dateList;

    }

    @Override
    public List<TbTeacher> teacherLogin(String teacher_id, String password) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("teacher_id").is(teacher_id).and("password").is(password));
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
    public List<Map<String, Object>> getStudentTripBySID(String sId, Date s,Date e) {

        List<Map<String,Object>> dateList = new ArrayList<>();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("sId").is(sId),
                Criteria.where("upTime").gte(s).lte(e)
        );

        Query query = new Query();
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.ASC,"upTime"));

        System.err.println(mongoTemplate.find(query, Tbrpt.class, "rpt"));
        return dateList;

    }

    @Override
    public List<Tbrpt> getStudentBySID(String sId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sId").is(sId));
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC,"upTime"));
        return mongoTemplate.find(query, Tbrpt.class, "rpt");
    }

}
