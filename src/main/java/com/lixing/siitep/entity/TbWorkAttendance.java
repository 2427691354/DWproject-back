package com.lixing.siitep.entity;

import lombok.Data;

@Data
public class TbWorkAttendance {
    private Integer id;

    private String sName;

    private String cName;

    private Integer haveClass;

    private Integer haveLate;

    private Integer haveAbsent;


}