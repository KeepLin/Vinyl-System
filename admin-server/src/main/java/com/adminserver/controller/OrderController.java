package com.adminserver.controller;

import com.adminserver.pojo.Order;
import com.adminserver.resultUtils.R;
import com.adminserver.service.OrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //获取全部订单数据
    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allOrder(@PathVariable Integer currentPage,@PathVariable Integer pageSize,String creatime){
        Page<Order> pageInfo = new Page<>(currentPage,pageSize);
        if (creatime != null && creatime.length()>0 && creatime != "null"){
            orderService.selectByPage(pageInfo,creatime);
            return R.success(pageInfo,"查询订单信息");
        }else {
            orderService.selectByPage(pageInfo,null);
            return R.success(pageInfo,"查询订单信息");
        }
    }

    //获取详细订单
    @GetMapping("/details/{Oid}")
    public R<Order> GetOrderByOid(@PathVariable Integer Oid){
        return R.success(orderService.getById(Oid),"指定订单号信息");
    }

    @DeleteMapping("/{Oid}")
    public R<String> delete(@PathVariable Integer Oid){
        if(orderService.removeById(Oid)){
            return R.success("删除订单信息成功");
        }else {
            return R.error("删除订单信息失败");
        }
    }

}
