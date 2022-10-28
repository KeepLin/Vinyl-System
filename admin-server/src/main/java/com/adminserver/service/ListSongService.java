package com.adminserver.service;


import com.adminserver.pojo.ListSong;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ListSongService extends IService<ListSong> {

    //删除歌曲索引
    Boolean DeleteSong(Integer SongId,Integer SongListId);

}
