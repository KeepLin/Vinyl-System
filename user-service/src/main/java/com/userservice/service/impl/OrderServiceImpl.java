package com.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userservice.mapper.OrderMapper;
import com.userservice.pojo.Order;
import com.userservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean AddOrder(Order temp) {
        //普通时间转化
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //日历类的时间操作
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,30);
        temp.setCreatime(df.format(date));
        temp.setEndtime(df.format(calendar.getTime()));
        System.out.println(temp.toString());
        if(orderMapper.insert(temp)>0){
            return true;
        }else {
            return false;
        }


    }
}
