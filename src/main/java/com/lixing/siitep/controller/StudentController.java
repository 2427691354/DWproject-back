package com.lixing.siitep.controller;



import com.lixing.siitep.entity.Rrr;
import com.lixing.siitep.entity.TbTeacher;
import com.lixing.siitep.entity.TbTest;
import com.lixing.siitep.entity.Tbrpt;
import com.lixing.siitep.service.StudentService;
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
@RequestMapping("/studentSearch")
public class StudentController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StudentService studentService;

    @GetMapping("/sum")
    @ApiOperation("宏观统计 总人数")
    public Object sum() throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> date = studentService.getDateList(1).get(0);


        // 总人数
        Aggregation sum = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("upTime").count().as("count")
        );

        Map<String,Object> s = ((List<Map<String,Object>>)mongoTemplate.aggregate(sum, "test", Rrr.class).getRawResults().get("results")).get(0);

        // 上报人数
        Aggregation sumsb = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("isReport").is(0)),
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> sb = ((List<Map<String,Object>>)mongoTemplate.aggregate(sumsb, "test", HashMap.class).getRawResults().get("results")).get(0);

        // 绿码人数
        Aggregation green = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("codeColor").is("绿码")),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> g = ((List<Map<String,Object>>)mongoTemplate.aggregate(green, "test", HashMap.class).getRawResults().get("results")).get(0);

        // 在苏州人数
        Aggregation InSu = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("locationCity").is("苏州 ")),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> js = ((List<Map<String,Object>>)mongoTemplate.aggregate(InSu, "test", HashMap.class).getRawResults().get("results")).get(0);

        // 在江苏 人数
        Aggregation InJiangSu = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("locationProvince").is("江苏")),
                Aggregation.group("upTime").count().as("count")
        );
        Map<String,Object> sz = ((List<Map<String,Object>>)mongoTemplate.aggregate(InJiangSu, "test", HashMap.class).getRawResults().get("results")).get(0);


        //当日隔离人数
        List key= Arrays.asList("新冠疑似","新冠确诊");
        Aggregation TodayPassCount = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.match(Criteria.where("physicalCondition").in(key)),
                Aggregation.group("upTime").count().as("count")
        );

        Map<String,Object> tpc = new HashMap<>();
        if(((List<Map<String,Object>>)mongoTemplate.aggregate(TodayPassCount, "test", HashMap.class).getRawResults().get("results")).size()>0){
            tpc = ((List<Map<String,Object>>)mongoTemplate.aggregate(TodayPassCount, "test", HashMap.class).getRawResults().get("results")).get(0);
        }
        else{
            tpc.put("count",0);
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStr=date.get("_id").toString().split(" ");
        String strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";

        result.put("sumAll",s.get("count"));
        result.put("upTime",sdf.format(new Date(Date.parse(strGMT))));
        result.put("sumSB",sb.get("count"));
        result.put("sumGreen",g.get("count"));
//        result.put("stuinJiang",js.get("count"));
//        result.put("stuinSuzhou",sz.get("count"));
        result.put("stuinSuzhou",js.get("count"));
        result.put("stuinJiang",sz.get("count"));
        result.put("TodayPassCount",tpc.get("count"));

        return result;
    }

    @GetMapping("getNewTime")
    @ApiOperation("最后更新时间")
    private String NewTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> date=studentService.getDateList(1).get(0);
        String[] dateStr=date.get("_id").toString().split(" ");
        String strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";
        return sdf.format(new Date(Date.parse(strGMT)));
    }

    @GetMapping("/getCityOrProvince")
    @ApiOperation("在某省份/城市人数近七天趋势")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "城市（苏州）",name = "city",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "省份（江苏）",name = "province",required = false,dataType = "String")
            }
    )
    public List<Map<String,Object>> getCityOrProvinceCount(@RequestParam(name = "city",required = false)String  city,
                                                 @RequestParam(name = "province",required = false)String province){

        List<Map<String,Object>> result = new ArrayList<>();
        if(city!=null) {
            List<Map<String, Object>> dateList = studentService.getCityOrProvince(city,null);
            for (Map<String, Object> date : dateList) {
                Map<String, Object> map = new HashMap<>();
                Query query = new Query();
                query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
                query.addCriteria(Criteria.where("locationCity").is(city+" "));
                map.put("upTime", date.get("_id"));
                map.put("CityCount", mongoTemplate.count(query, "test"));
                result.add(map);
            }
        }
        if(province!=null) {
            List<Map<String, Object>> dateList = studentService.getCityOrProvince(null, province);
            for (Map<String, Object> date : dateList) {
                Map<String, Object> map = new HashMap<>();
                Query query = new Query();
                query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
                query.addCriteria(Criteria.where("locationProvince").is(province));
                map.put("upTime", date.get("_id"));
                map.put("ProvinceCount", mongoTemplate.count(query, "test"));
                result.add(map);
            }
        }
        return  result;
    }

    @GetMapping("/getCodeRegisterCount")
    @ApiOperation("统计红黄绿码注册人数占比")
    private Map<String,Object> getCodeRegisterCount(){
        Map<String,Object> date=studentService.getDateList(1).get(0);
        System.err.println(date);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("codeColor").count().as("持码人数")
        );
        return mongoTemplate.aggregate(aggregation,"test",HashMap.class).getRawResults();
    }

    @GetMapping("getpassTrend")
    @ApiOperation("统计隔离人数趋势")
    public List<Map<String,Object>> getpassTrend(){
        List<Map<String,Object>> result = new ArrayList<>();
        List<Map<String,Object>> dateList = studentService.getDateList(null);
        List key= Arrays.asList("新冠疑似","新冠确诊");
        for(Map<String,Object> date:dateList){
            Map<String,Object> map = new HashMap<>();
            Query query = new Query();
            query.addCriteria(Criteria.where("upTime").is(date.get("_id")));
            query.addCriteria(Criteria.where("physicalCondition").in(key));
            query.limit(6);
            map.put("upTime",date.get("_id"));
            map.put("count",mongoTemplate.count(query,"student"));
            result.add(map);
        }

        return result;
    }

    @GetMapping("/getStuInProvince")
    @ApiOperation("统计学生各省物理分布人数")
    private Map<String,Object> StuInProvince(){
        Map<String,Object> date=studentService.getDateList(1).get(0);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("upTime").is(date.get("_id"))),
                Aggregation.group("locationProvince").count().as("总人数")
        );
        return mongoTemplate.aggregate(aggregation,"test",HashMap.class).getRawResults();
    }

    @GetMapping("/teacherLogin")
    @ApiOperation("老师登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "账号",name = "teacher_id",required = false,dataType = "String"),
                    @ApiImplicitParam(value = "密码",name = "password",required = false,dataType = "Integer")
            }
    )
    public String getStudent(@RequestParam(name = "teacher_id",required = false)String  teacher_id,@RequestParam(name = "password",required = false)String password){

        List<TbTeacher> teachers = studentService.teacherLogin(teacher_id,password);
        if(teachers == null){
            return "不存在";
        }
        return "存在";
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
            List<TbTest> rpt = mongoTemplate.find(query, TbTest.class, "test");
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
        map.put("stuInfo",studentService.getStudentBySID(sId));
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

//    @GetMapping("/test")
//    public Object test()
//    {
//        return studentService.findStudentAndTest();
//    }


}
