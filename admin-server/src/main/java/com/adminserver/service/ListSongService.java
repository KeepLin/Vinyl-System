package com.adminserver.service;


import com.adminserver.pojo.ListSong;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ListSongService extends IService<ListSong> {

    //删除歌曲索引
    Boolean DeleteSong(Integer SongId,Integer SongListId);

    //添加歌曲到歌单中
    Boolean AddSong(ListSong song);

    //当歌单删除时索引表的记录都要删除
    Boolean DeleteRecord(Integer SongListId);

}
