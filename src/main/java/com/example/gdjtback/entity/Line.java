package com.example.gdjtback.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="line对象",description="线路对象Line")
@Data
public class Line {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "线路id")
    private String lineid;

    @ApiModelProperty(value = "线路名称")
    private String linename;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "id")
    private Integer deleteflag;
}