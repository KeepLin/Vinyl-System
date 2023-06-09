package com.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userservice.mapper.StampMapper;
import com.userservice.pojo.Stamp;
import com.userservice.resultUtils.R;
import com.userservice.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StampServiceImpl extends ServiceImpl<StampMapper, Stamp> implements StampService {
    @Autowired
    private StampMapper stampMapper;

    @Override
    public R<String> AddStamp(Stamp stamp) {
        QueryWrapper<Stamp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",stamp.getSongId()).eq("playlist_id",stamp.getPlaylistId());
        if(stampMapper.selectCount(queryWrapper)>0){
            return R.error("添加失败,歌曲已存在歌单");
        }else {
            if(stampMapper.insert(stamp)>0){
                return R.success("添加成功");
            }else{
                return R.error("添加失败");
            }
        }
    }

    @Override
    public Boolean DeleteSong(Integer pid, Integer songId) {
        return this.stampMapper.DeleteSong(pid,songId);
    }

    @Override
    public R<String> DeleteList(Integer pid) {
        QueryWrapper<Stamp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playlist_id",pid);
        this.stampMapper.delete(queryWrapper);
        return R.success("歌单删除成功");
    }

    @Override
    public Boolean CountSong(Integer pid) {
        QueryWrapper<Stamp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playlist_id",pid);
        if(this.stampMapper.selectCount(queryWrapper)<12){
            return true;
        }else {
            return false;
        }

    }
}
