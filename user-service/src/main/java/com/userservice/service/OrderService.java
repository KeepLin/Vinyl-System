package com.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Order;

public interface OrderService extends IService<Order> {

    Boolean AddOrder(Order order);
}
