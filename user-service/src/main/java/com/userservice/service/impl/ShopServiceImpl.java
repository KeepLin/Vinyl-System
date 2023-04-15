package com.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Page<Shop> selectShopByPage(Page<Shop> page, Integer Uid) {
        return page.setRecords(this.shopMapper.selectByPage(page,Uid));
    }

    //判断购物车是否已存在定制商品
    @Override
    public Boolean FindShop(Shop shop) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playlist_id",shop.getPlaylistId()).eq("user_id",shop.getUserId());
        Integer record = shopMapper.selectCount(queryWrapper);
        System.out.println(record);
        if (record>0){
            return false;
        }else {
            return true;
        }
    }
}
