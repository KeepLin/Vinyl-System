package com.adminserver.service;


import com.adminserver.pojo.Consumer;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsumerService extends IService<Consumer> {
    //数据分页展示
    Page<Consumer> selectByPage(Page<Consumer> page , Integer id, Integer sex);

    //统计用户数量
    Integer countUser();

    //统计用户性别
    List<Integer> countUserSex();


}
