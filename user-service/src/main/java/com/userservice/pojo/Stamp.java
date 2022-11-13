package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "stamp")
@Data
public class Stamp {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer songId;

    private Integer pid;

}
