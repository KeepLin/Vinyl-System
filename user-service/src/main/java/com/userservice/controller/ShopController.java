package com.userservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.Shop;
import com.userservice.resultUtils.R;
import com.userservice.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/{currentPage}/{pageSize}/{Uid}")
    public R<Page> SelectShopMessageByUid(@PathVariable Integer currentPage,@PathVariable Integer pageSize,@PathVariable Integer Uid){
        Page<Shop> pageInfo = new Page<>(currentPage, pageSize);
        shopService.selectByPage(pageInfo,Uid);
        return R.success(pageInfo,"查询指定用户购物车列表");
    }


}
