package com.userservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userservice.mapper.ShopMapper;
import com.userservice.pojo.Shop;
import com.userservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Page<Shop> selectByPage(Page<Shop> page, Integer Uid) {
        return page.setRecords(this.shopMapper.selectByPage(page,Uid));
    }
}
