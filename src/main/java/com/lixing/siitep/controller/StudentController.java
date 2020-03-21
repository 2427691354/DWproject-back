package com.lixing.siitep.controller;

import com.lixing.siitep.entity.Tbrpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
    private MongoTemplate mongoTemplate;

@GetMapping("/list")
    public List<Tbrpt> list(){
    //指定文档
    List<Tbrpt> student = mongoTemplate.findAll(Tbrpt.class,"rpt");

    System.err.println(mongoTemplate.findAll(Tbrpt.class).size());
    return student;
}


}
