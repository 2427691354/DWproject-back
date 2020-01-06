package com.example.gdjtback.entity;

import lombok.Data;

@Data
public class Line {
    private Integer id;

    private String lineid;

    private String linename;

    private String remarks;

    private Integer deleteflag;
}