package com.adminserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "admin")
@Data
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    private String adminName;

    private String adminPassword;
}