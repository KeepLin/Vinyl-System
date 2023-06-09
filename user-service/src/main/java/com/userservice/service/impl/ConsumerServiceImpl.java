package com.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userservice.mapper.ConsumerMapper;
import com.userservice.pojo.Consumer;
import com.userservice.resultUtils.R;
import com.userservice.service.ConsumerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Override
    public Page<Consumer> selectByPage(Page<Consumer> page, Integer id, Integer sex) {
        return page.setRecords(this.baseMapper.selectByPage(page,id,sex));
    }

    @Override
    public R<Consumer> Login(HttpServletRequest request, Consumer consumer) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_num",consumer.getPhoneNum());
        queryWrapper.eq("user_password",consumer.getUserPassword());
        Consumer user = new Consumer();
        user = baseMapper.selectOne(queryWrapper);
        if(user != null){
            request.getSession().setAttribute("username",user.getUserName());
            return R.success(user,"欢迎回来");
        }else {
            return R.error("用户名或密码错误");
        }
    }


}
