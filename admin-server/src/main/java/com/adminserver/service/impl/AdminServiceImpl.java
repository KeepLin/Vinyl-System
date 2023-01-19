package com.adminserver.service.impl;

import com.adminserver.mapper.AdminMapper;
import com.adminserver.pojo.Admin;
import com.adminserver.resultUtils.R;
import com.adminserver.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public R<Admin> Login(HttpServletRequest request, Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name",admin.getAdminName());
        queryWrapper.eq("admin_password",admin.getAdminPassword());
        if (count(queryWrapper)>0){
            request.getSession().setAttribute("name",admin.getAdminName());
            return R.success(admin,"欢迎回来");
        }else {
            return R.error("用户名或密码错误");
        }
    }
}
