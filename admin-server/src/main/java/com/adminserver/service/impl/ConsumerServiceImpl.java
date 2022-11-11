package com.adminserver.service.impl;

import com.adminserver.mapper.ConsumerMapper;
import com.adminserver.pojo.Consumer;
import com.adminserver.service.ConsumerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {
    @Override
    public Page<Consumer> selectByPage(Page<Consumer> page, Integer id, Integer sex) {
        return page.setRecords(this.baseMapper.selectByPage(page,id,sex));
    }

    @Override
    public Integer countUser() {
        return count();
    }

    @Override
    public List<Integer> countUserSex() {
        QueryWrapper<Consumer> SexWrapper = new QueryWrapper<>();
        SexWrapper.eq("sex",0);
        Integer Girl = count(SexWrapper);
        SexWrapper.clear();

        SexWrapper.eq("sex",1);
        Integer Boy = count(SexWrapper);
        List<Integer> SexArray= new ArrayList<>();
        SexArray.add(Girl);
        SexArray.add(Boy);
        return SexArray;
    }


}
