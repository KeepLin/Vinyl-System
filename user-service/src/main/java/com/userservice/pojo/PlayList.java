package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "playlist")
@Data
public class PlayList {

    @TableId(type = IdType.AUTO)
    private Integer pid;

    private String title;

    private Integer uid;

    private Integer score;

    private String pic;
}
