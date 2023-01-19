package com.userservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@TableName(value = "orders")
@Data
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer orderId;

    private Integer shopId;

    private Integer userId;

    private String Inch;

    private Integer Number;

    private Integer Price;

    private String consignee;

    private String Phone;

    private String Address;

    private String Creatime;

    private String Endtime;


}
