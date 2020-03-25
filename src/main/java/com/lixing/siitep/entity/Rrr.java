package com.lixing.siitep.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author cc
 * @date 2020/03/25
 **/
public class Rrr {
    private Integer count;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date upTime;
}
