package com.example.gdjtback.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="User",description="用户对象User")
@Data
public class User {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "物理标记")
    private Integer deleteflag;

    @ApiModelProperty(value = "备注")
    private String remarks;


}