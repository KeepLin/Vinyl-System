package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer Oid;

    private Integer Sid;

    private Integer Uid;

    private Integer Number;

    private Integer Price;

    private String Name;

    private String Phone;
}
