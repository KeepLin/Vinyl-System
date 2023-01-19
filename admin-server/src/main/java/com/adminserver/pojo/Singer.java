package com.adminserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "singer")
@Data
public class Singer {

    @TableId(type = IdType.AUTO)
    private Integer singerId;

    private String singerName;

    private Byte sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;

}
