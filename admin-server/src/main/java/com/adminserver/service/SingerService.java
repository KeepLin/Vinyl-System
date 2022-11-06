package com.adminserver.service;


import com.adminserver.pojo.Singer;
import com.adminserver.resultUtils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;


public interface SingerService extends IService<Singer> {

    //数据分页展示
    Page<Singer> selectByPage(Page<Singer> page, String name, Integer sex);

    //歌手图片上传
    R<String> updateSingerPic(MultipartFile multipartFile, int id) throws FileNotFoundException;

    //统计歌手数量
    Integer countSinger();

    //统计歌手性别
    List<Integer> countSingerSex();

    //统计歌手国籍
    List<Integer> countCountry();

    //添加歌手
    Boolean AddSinger(Singer addSingerRequest);

    //模糊查询歌手
    List<Singer> SearchBySinger(String name);
}
