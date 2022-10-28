package com.adminserver.service.impl;

import com.adminserver.mapper.ListSongMapper;
import com.adminserver.pojo.ListSong;
import com.adminserver.service.ListSongService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;
    @Override
    public Boolean DeleteSong(Integer SongId, Integer SongListId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").eq("song_id",SongId).eq("song_list_id",SongListId);

        int delete = listSongMapper.delete(queryWrapper);
        if (delete>0){
            return true;
        }else{
            return false;
        }


    }


}
