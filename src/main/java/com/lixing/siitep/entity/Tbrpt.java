package com.lixing.siitep.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document("rpt")
@Data
public class Tbrpt {
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date upTime;

    private Set<Tbrpt> students;

    private String sName;
    private String sId;
    private String cName;
    private String deptName;
    private String localContact;
    private String localPass;
    private String locationProvince;
    private String locationCity;
    private String codeColor;
    private Integer isReport;
    private String physicalCondition;

}
