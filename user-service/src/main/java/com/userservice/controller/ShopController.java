package com.userservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.Shop;
import com.userservice.resultUtils.R;
import com.userservice.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/{currentPage}/{pageSize}/{Uid}")
    public R<Page> selectShopMessageByUid(@PathVariable Integer currentPage,@PathVariable Integer pageSize,@PathVariable Integer Uid){
        Page<Shop> pageInfo = new Page<>(currentPage, pageSize);
        shopService.selectShopByPage(pageInfo,Uid);
        return R.success(pageInfo,"查询指定用户购物车列表");
    }

    //将定制歌单添加购物车
    @PostMapping
    public R<String> addShop(@RequestBody Shop shop){
        if(shopService.FindShop(shop)){
            shopService.save(shop);
            return R.success("添加至购物车");
        }else {
            return R.error("购物车已存在此商品！");
        }
    }

    //删除商品
    @DeleteMapping("/{Sid}")
    public R<String> DeleteShop(@PathVariable Integer Sid){
        if(shopService.removeById(Sid)){
            return R.success("购物清单已移除");
        }else {
            return R.error("清单移除失败");
        }
    }




}
