package com.lixing.siitep.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("student")
@Data
public class TbStudent {
    @Id
    private String id;
    private String sName;
    private String sId;
    private String cName;
    private String deptName;
    private Integer isReturn;

}
