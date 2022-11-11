package com.adminserver.service;


import com.adminserver.pojo.Admin;
import com.adminserver.resultUtils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Admin> {
    //管理员登录
    R<Admin> Login(HttpServletRequest request, Admin admin);
}
