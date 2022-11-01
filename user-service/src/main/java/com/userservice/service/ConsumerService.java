package com.userservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Consumer;
import com.userservice.resultUtils.R;

import javax.servlet.http.HttpServletRequest;


public interface ConsumerService extends IService<Consumer> {
    //数据分页展示
    Page<Consumer> selectByPage(Page<Consumer> page , Integer id, Integer sex);

    //用户登录
    R<Consumer> Login(HttpServletRequest request,String phoneNum,String password);


}
