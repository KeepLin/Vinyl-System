package com.adminserver.service.impl;

import com.adminserver.mapper.OrderMapper;
import com.adminserver.pojo.Order;
import com.adminserver.service.OrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Page<Order> selectByPage(Page<Order> page, String creatime) {
        return page.setRecords(this.orderMapper.selectByPage(page,creatime));
    }
}
