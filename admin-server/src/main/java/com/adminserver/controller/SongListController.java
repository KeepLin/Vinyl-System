package com.adminserver.controller;


import com.adminserver.pojo.SongList;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SongListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/SongMenu")
public class SongListController {

    @Autowired
    private SongListService service;

    //统计歌单数量
    @GetMapping("/count")
    public R<Integer> countSongList(){

        return R.success(service.count(), "歌单总数");
    }

    //统计歌单风格
    @GetMapping("/count/style")
    public R<List<Integer>> countStyle(){
        List<Integer> array = service.countStyle();
        return R.success(array, "歌单风格");
    }

    @GetMapping("/{id}")
    public R<SongList> getById(@PathVariable Integer id){
        SongList list = service.getById(id);
        if(list!=null){
            return R.success(list,"查到此歌单信息");
        }
        return R.error("没有此歌单信息");
    }
    //分页查询
    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allSinger(@PathVariable Integer currentPage, @PathVariable Integer pageSize, String title, String style){
        Page<SongList> pageInfo = new Page(currentPage,pageSize);
        if (title != null && title.length()>0){
            service.selectByPage(pageInfo,title,null);
            return R.success(pageInfo,"歌单列表");
        } else if (style != null && style.length()>0){
            service.selectByPage(pageInfo,null,style);
            return R.success(pageInfo,"歌单列表");
        } else {
            service.selectByPage(pageInfo,null,null);
            return R.success(pageInfo,"歌单列表");
        }

    }

    //添加歌单
    @PostMapping
    public R<String> addSong(@RequestBody SongList songList){
        return service.addSongList(songList);
    }

    //更新歌单
    @PutMapping
    public R<String> updateSongListMsg(@RequestBody SongList updateMsg){
        SongList temp = new SongList();
        BeanUtils.copyProperties(updateMsg,temp);
        if(service.updateById(temp)){
            return R.success("歌单信息更新成功");
        }else {
            return R.error("歌单信息更新失败");
        }
    }

    //更新歌曲图片
    @PostMapping("/upload/image")
    public R<String> updateSongListPic(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") int id) throws FileNotFoundException {
        return service.updateSongListPic(multipartFile,id);
    }

    //删除歌单
    @DeleteMapping("/{id}")
    public R<String> deleteSongList(@PathVariable Integer id){
        return service.deleteSongList(id);
    }

    //获取指定风格歌单前十的数据
    @GetMapping("/SongListByStyle")
    public List<SongList> AllSongList(@RequestParam ("style") String style){
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        if(style != null && style.length()>0){
            queryWrapper.eq("style",style);
        }else {
            queryWrapper.last("limit 10");
        }
        return service.list(queryWrapper);
    }

    //模糊查询歌单
    @GetMapping("/SearchByTitle")
    public List<SongList> SearchByTitle(@RequestParam ("title") String title){
        return service.SearchByTitle(title);
    }

}
