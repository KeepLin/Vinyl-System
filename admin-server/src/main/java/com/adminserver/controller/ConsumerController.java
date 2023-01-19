package com.adminserver.controller;

import com.adminserver.pojo.Consumer;
import com.adminserver.resultUtils.R;
import com.adminserver.service.ConsumerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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


    @DeleteMapping("/{userId}")
    public R<String> deleteUser(@PathVariable Integer userId){
         if (service.removeById(userId)) {
            return R.success("用户删除成功");
        }else {
             return R.error("用户删除失败");
         }
    }

    //统计用户数量
    @GetMapping("/count")
    public R<Integer> UserNumber(){
        return R.success(service.countUser(),"数库用户总数");
    }

    //统计用户性别
    @GetMapping("/count/UserSex")
    public R<List<Integer>> countUserSex(){
        List<Integer>  array= new ArrayList<>();
        array= service.countUserSex();
        return R.success(array,"用户男女个数比例");
    }

}
