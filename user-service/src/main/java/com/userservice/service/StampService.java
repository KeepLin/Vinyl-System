package com.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.Stamp;
import com.userservice.resultUtils.R;

public interface StampService extends IService<Stamp> {
    //添加歌曲到指定歌单
    R<String> AddStamp(Stamp stamp);

    //删除歌单单曲索引
    Boolean DeleteSong(Integer pid ,Integer songId);

    //删除歌单歌曲所有索引
    R<String> DeleteList(Integer pid);

    //统计定制歌单歌曲数量限制在12首
    Boolean CountSong(Integer pid);
}
