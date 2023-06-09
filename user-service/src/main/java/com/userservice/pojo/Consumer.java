package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@TableName(value = "consumer")
@Data
public class Consumer {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    private Byte sex;

    private String phoneNum;

    private String avator;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
