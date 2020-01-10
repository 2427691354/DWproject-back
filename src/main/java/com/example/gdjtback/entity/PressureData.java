package com.example.gdjtback.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "PressureData",description = "气瓶数据PressureData")
@Data
public class PressureData {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "设备ID")
    private String deviceID;
    @ApiModelProperty(value = "传感器气瓶id")
    private String sensorID;
    @ApiModelProperty(value = "气瓶类型")
    private Integer ptype;
    @ApiModelProperty(value = "气压值")
    private Float parValue;
    @ApiModelProperty(value = "采集时间")
    private String sTime;
    @ApiModelProperty(value = "气瓶状态")
    private Integer wtype;
}
