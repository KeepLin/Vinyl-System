package com.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    Boolean AddOrder(Order order);

    List<Order> getOrderByUid(Integer Uid);
}
