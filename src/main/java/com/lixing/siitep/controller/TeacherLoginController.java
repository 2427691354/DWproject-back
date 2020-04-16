package com.lixing.siitep.controller;

import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.MongoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
                query.fields().include("sName");
                query.fields().include("sId");
                query.fields().include("locationProvince");
                query.fields().include("locationCity");
                query.fields().include("temperature");
                query.fields().include("upTime");
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
            query.fields().include("sName");
            query.fields().include("sId");
            query.fields().include("locationProvince");
            query.fields().include("locationCity");
            query.fields().include("temperature");
            query.fields().include("upTime");
            map.put("result", mongoTemplate.find(query, Tbrpt.class, "rpt"));
            result.add(map);

        }
        return result;
    }

    @GetMapping("/getStudentTrip_1")
    public List<Map<String,Object>> getStudentTrip_(@RequestParam(name = "sId",required = false)String  sId,
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
                query.fields().include("sName");
                query.fields().include("sId");
                query.fields().include("locationProvince");
                query.fields().include("locationCity");
                query.fields().include("temperature");
                query.fields().include("upTime");
                query.fields().include("cName");
                query.fields().include("deptName");
                query.fields().include("codeColor");
                map.put("result", mongoTemplate.find(query, Tbrpt.class, "rpt"));
                result.add(map);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (starttime != null && endtime != null) {
            List<String> list = new ArrayList<String>(); //保存日期的集合
            Date date_start = sdf.parse(starttime);
            Date date_end = sdf.parse(endtime);
            Date date =date_start;
            Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
            while (date.getTime() <= date_end.getTime()){
                list.add(sdf.format(date));
                cd.setTime(date);
                cd.add(Calendar.DATE, 1);//增加一天 放入集合
                date=cd.getTime();
            }
        for(String time:list) {
            System.err.println(time);
            Map<String, Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(time));
            query.addCriteria(Criteria.where("sId").is(sId));
            query.fields().include("sName");
            query.fields().include("sId");
            query.fields().include("locationProvince");
            query.fields().include("locationCity");
            query.fields().include("temperature");
            query.fields().include("upTime");
            query.fields().include("cName");
            query.fields().include("deptName");
            query.fields().include("codeColor");
            System.err.println( mongoTemplate.find(query, Tbrpt.class, "rpt").size());
            if( mongoTemplate.find(query, Tbrpt.class, "rpt").size()>0){
                map.put("result", mongoTemplate.find(query, Tbrpt.class, "rpt"));
            }else {
                map.put("result","未上报");
            }
            result.add(map);
            }
        }
        return result;
    }

    @GetMapping("/getStudentTripBySID")
    @ApiOperation("获取学生行程信息 -- 陈晨")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "学生学号",name = "sId",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "开始时间",name = "starttime",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "结束时间",name = "endtime",required = false,dataType = "String")
            }
    )
    public Map<String,Object> getStudentTripBySID(@RequestParam(name = "sId",required = false)String  sId,
                                                   @RequestParam(name = "starttime")String starttime,
                                                   @RequestParam(name = "endtime")String endtime ) throws ParseException {
        List<Map<String, Object>> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for(Date days: findDaysStr(starttime,endtime)){
            Map<String, Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("sId").is(sId));
            query.addCriteria(Criteria.where("upTime").is(days));
            List<Tbrpt> rpt = mongoTemplate.find(query, Tbrpt.class, "rpt");
            if(rpt.size() >0){
                map.put("upTime",sdf.format(days));
                map.put("locationProvince",rpt.get(0).getLocationProvince());
                map.put("locationCity",rpt.get(0).getLocationCity());
                map.put("temperature",rpt.get(0).getTemperature());
            }else {
                map.put("upTime",sdf.format(days));
                map.put("locationProvince","未上报");
                map.put("locationCity","未上报");
                map.put("temperature","未上报");
            }
            result.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("stuInfo",mongoService.getStudentBySID(sId));
        map.put("trip",result);
        return map;
    }


    public static List<Date> findDaysStr(String begintTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = sdf.parse(begintTime);
            dEnd = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Date> daysStrList = new ArrayList<Date>();
        daysStrList.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (dEnd.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Date dayStr = calBegin.getTime();
            daysStrList.add(dayStr);
        }
        return daysStrList;
    }
    }
