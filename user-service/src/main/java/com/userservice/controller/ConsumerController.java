package com.userservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.Consumer;
import com.userservice.resultUtils.R;
import com.userservice.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    //分页显示
    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allSinger(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Integer id, Integer sex) {
        Page<Consumer> pageInfo = new Page<>(currentPage,pageSize);
        if (id != null){
            service.selectByPage(pageInfo,id,null);
            return R.success(pageInfo,"数据库用户信息列表");
        }else if (sex != null){
            service.selectByPage(pageInfo,null,sex);
            return R.success(pageInfo,"数据库用户信息列表");
        }else {
            service.selectByPage(pageInfo,null,null);
            return R.success(pageInfo,"用户信息列表");
        }
    }

    //用户登录
    @GetMapping("/login")
    public R<Consumer> login(HttpServletRequest request, String phoneNum,String password){
        return service.Login(request,phoneNum,password);
    }





}
