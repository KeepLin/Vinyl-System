package com.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Stamp;
import com.userservice.resultUtils.R;

public interface StampService extends IService<Stamp> {
    R<String> AddStamp(Stamp stamp);
}
