package com.adminserver.service.impl;

import com.adminserver.mapper.SongMapper;
import com.adminserver.pojo.Song;
import com.adminserver.service.SongService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {



    @Override
    public Page<Song> selectByPage(Page<Song> page, String name, Integer singerid) {
        return page.setRecords(this.baseMapper.selectByPage(page,name,singerid));
    }

    @Override
    public Page<Song> selectBySongList(Page<Song> page, Integer id, String name) {
        return page.setRecords(this.baseMapper.selectBySongList(page,id,name));
    }

    @Override
    public Boolean AddSong(Song song) {
        Song copy = new Song();
        BeanUtils.copyProperties(song,copy);
        //字符串拼接
        String s = copy.getSingername() + "-" + copy.getName();
        copy.setName(s);

        return save(copy);
    }

    @Override
    public Boolean UpdateMsg(Song SongRequest) {
        Song song  = new Song();
        BeanUtils.copyProperties(SongRequest, song);
        return updateById(song);
    }

    @Override
    public List<Song> SearchByName(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<Song> songList = list(queryWrapper);
        return songList;
    }

    @Override
    public List<Song> SearchById(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",id);
        List<Song> songList = list(queryWrapper);
        return songList;
    }

    @Override
    public Integer countSong() {
        return count();
    }


}
