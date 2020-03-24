package com.lixing.siitep.service.impl;

import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
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

        if(length!=null){
            operations.add(Aggregation.limit(length));
        }

        operations.add(Aggregation.sort(Sort.Direction.DESC,"_id"));

        Aggregation aggregation = Aggregation.newAggregation(operations);
        dateList = (List<Map<String,Object>>)mongoTemplate.aggregate(aggregation, "rpt", HashMap.class).getRawResults().get("results");
        System.err.println(dateList);
        return dateList;

    }
}
