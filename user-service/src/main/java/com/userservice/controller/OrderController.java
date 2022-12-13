package com.userservice.controller;

import com.userservice.pojo.Order;
import com.userservice.resultUtils.R;
import com.userservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //添加订单信息
    @PostMapping
    public R<String> AddOrder(@RequestBody Order order){
        if(orderService.AddOrder(order)){
            return R.success("下单成功");
        }else {
            return R.error("下单失败，请重新尝试");
        }
    }

}
