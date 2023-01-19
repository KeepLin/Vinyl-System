package com.userservice.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.Consumer;
import com.userservice.resultUtils.R;
import com.userservice.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


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
    @PostMapping("/login")
    public R<Consumer> login(HttpServletRequest request,@RequestBody Consumer consumer){
        return service.Login(request,consumer);
    }

    //用户登出
    @GetMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return R.success("退出成功");
    }

    //用户注册
    @PostMapping
    public R<String> addUser(@RequestBody Consumer consumer){
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        boolean flag = service.save(consumer);
        if (flag){
            return R.success("注册成功");
        }else {
            return R.error("注册失败");
        }
    }

    //修改密码
    @PutMapping
    public R<String> ChangePassword(@RequestBody Consumer consumer){
        UpdateWrapper<Consumer> wrapper = new UpdateWrapper<>();
        wrapper.set("user_password",consumer.getUserPassword()).set("update_time",new Date()).eq("phone_num",consumer.getPhoneNum());
        boolean flag = service.update(wrapper);
        if (flag){
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }





}
