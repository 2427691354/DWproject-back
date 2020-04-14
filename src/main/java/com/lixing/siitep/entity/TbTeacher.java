package com.lixing.siitep.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teacher")
@Data
public class TbTeacher {

    @Id
    private String id;
    private String teacher_id;
    private String password;
}
