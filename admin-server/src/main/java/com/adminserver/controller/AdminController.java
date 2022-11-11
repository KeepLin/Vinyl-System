package com.adminserver.controller;

import com.adminserver.pojo.Admin;
import com.adminserver.resultUtils.R;
import com.adminserver.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员登录
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {
        return adminService.Login(request,admin);
    }
    //管理员登出
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        return R.success("退出成功");
    }




}
