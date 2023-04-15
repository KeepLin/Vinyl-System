package com.userservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Shop;

public interface ShopService extends IService<Shop> {
    Page<Shop> selectShopByPage(Page<Shop> page,Integer Uid);

    Boolean FindShop(Shop shop);
}
