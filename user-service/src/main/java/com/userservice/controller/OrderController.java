package com.userservice.controller;

import com.userservice.pojo.Order;
import com.userservice.resultUtils.R;
import com.userservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //个人订单列表
    @GetMapping("/{Uid}")
    public R<List<Order>> GetOrderByUid(@PathVariable Integer Uid){
        return R.success(orderService.getOrderByUid(Uid),"个人订单信息");
    }

    //个人详细订单
    @GetMapping("/details/{Oid}")
    public R<Order> GetOrderByOid(@PathVariable Integer Oid){
        return R.success(orderService.getById(Oid),"指定订单号信息");
    }

    @PutMapping
    public R<String> UpdateOrder(@RequestBody Order order){
        if (orderService.updateById(order)){
           return R.success("修改订单信息成功");
        }else {
           return R.error("修改订单信息失败");
        }
    }

}
