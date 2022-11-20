package com.userservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.userservice.pojo.PlayList;
import com.userservice.resultUtils.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface PlayListService extends IService<PlayList> {
    //用户定制歌单
    Page<PlayList> selectByPage(Page<PlayList> page,Integer uid);

    //更新唱片制作封面
    R<String> updatePlayListPic(MultipartFile multipartFile, int pid)throws FileNotFoundException;

    //删除歌单信息及图片
    R<String> deletePlayList(Integer pid);

    R<String> addPlayList(PlayList playList);



}
