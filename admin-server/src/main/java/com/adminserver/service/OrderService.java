package com.adminserver.service;

import com.adminserver.pojo.Order;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    Page<Order> selectByPage(Page<Order> page, String Creatime);
}
