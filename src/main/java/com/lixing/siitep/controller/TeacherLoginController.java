package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherLoginController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoService mongoService;

    @GetMapping("/teacherLogin")
    @ApiOperation("老师登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "账号",name = "teacher_id",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "密码",name = "password",required = false,dataType = "Integer")
            }
    )
    public String getStudent(@RequestParam(name = "teacher_id",required = false)String  teacher_id,@RequestParam(name = "password",required = false)String password){

        List<TbTeacher> teachers = mongoService.teacherLogin(teacher_id,password);
        if(teachers == null){
            return "不存在";
        }
        return "存在";
    }

    @GetMapping("/getStudentTrip")
    @ApiOperation("获取学生行程信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "学生学号",name = "sId",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "开始时间",name = "starttime",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "结束时间",name = "endtime",required = false,dataType = "String")
            }
    )
    public List<Map<String,Object>> getStudentTrip(@RequestParam(name = "sId",required = false)String  sId,
                                                   @RequestParam(name = "starttime",required = false)String starttime,
                                                   @RequestParam(name = "endtime",required = false)String endtime ) throws ParseException {
        //根据学生ID，没有输入时间，默认从最新时间往前数几天
        List<Map<String, Object>> result = new ArrayList<>();
        if (starttime == null && endtime == null) {
            List<Map<String, Object>> dateList = mongoService.getStudentTrip(sId, null);
            for (Map<String, Object> date : dateList) {
                Map<String, Object> map = new HashMap<>();
                Query query = new Query();
                query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
                query.addCriteria(Criteria.where("sId").is(sId));
                map.put("result", mongoTemplate.find(query, Tbrpt.class, "rpt"));
                result.add(map);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (starttime != null && endtime != null) {
            Date startTime =  sdf.parse(starttime);
            Date endTime =  sdf.parse(endtime);
            Map<String, Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").gte(startTime).lte(endTime));
            query.addCriteria(Criteria.where("sId").is(sId));
            map.put("result", mongoTemplate.find(query, Tbrpt.class, "rpt"));
            result.add(map);

        }
        return result;
    }

}
