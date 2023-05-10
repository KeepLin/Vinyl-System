package com.adminserver.controller;

import com.adminserver.pojo.Admin;
import com.adminserver.resultUtils.R;
import com.adminserver.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "管理登录功能模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员登录
    @ApiOperation("登录请求")
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {
        return adminService.Login(request,admin);
    }
    //管理员登出
    @ApiOperation("退出登录请求")
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        return R.success("退出成功");
    }




}
