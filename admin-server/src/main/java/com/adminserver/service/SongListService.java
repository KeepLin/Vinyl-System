package com.adminserver.service;


import com.adminserver.pojo.SongList;
import com.adminserver.resultUtils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface SongListService extends IService<SongList> {
    Page<SongList> selectByPage(Page<SongList> page, String title, String style);

    R<String> addSongList(SongList songList);

    //更新歌单图片
    R<String> updateSongListPic(MultipartFile multipartFile, int id) throws FileNotFoundException;
}
