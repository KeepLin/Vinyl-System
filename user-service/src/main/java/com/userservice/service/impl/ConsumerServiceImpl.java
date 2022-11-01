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
    public R<Consumer> Login(HttpServletRequest request, String phoneNum,String password) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_num",phoneNum);
        queryWrapper.eq("password",password);
        Consumer user = new Consumer();
        user = baseMapper.selectOne(queryWrapper);
        if(user != null){
            request.getSession().setAttribute("name",user.getUsername());
            return R.success(user,"欢迎回来");
        }else {
            return R.error("用户名或密码错误");
        }
    }


}
