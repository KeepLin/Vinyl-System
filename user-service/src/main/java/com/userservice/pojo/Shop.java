package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "shop")
@Data
public class Shop {
    @TableId(type = IdType.AUTO)
    private Integer Sid;

    private Integer Uid;

    private Integer Pid;

    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private Integer score;

    @TableField(exist = false)
    private String pic;
}
