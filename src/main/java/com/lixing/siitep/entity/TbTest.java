package com.lixing.siitep.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("test")
@Data
public class TbTest {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date upTime;

    @Id
    private String id;
    private String sId;
    private String localContact;
    private String localPass;
    private String locationProvince;
    private String locationCity;
    private String codeColor;
    private Integer isReport;
    private String physicalCondition;
    private String temperature;

}
