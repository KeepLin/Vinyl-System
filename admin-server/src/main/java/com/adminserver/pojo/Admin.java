package com.adminserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName(value = "admin")
@ApiModel("管理员信息实体")
@Data
public class Admin {
    @ApiModelProperty("编号")
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    @ApiModelProperty("姓名")
    private String adminName;

    @ApiModelProperty("密码")
    private String adminPassword;
}