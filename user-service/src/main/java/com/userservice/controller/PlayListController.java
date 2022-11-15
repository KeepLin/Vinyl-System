package com.userservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.PlayList;
import com.userservice.resultUtils.R;
import com.userservice.service.PlayListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequestMapping("/playlist")
public class PlayListController {

    @Autowired
    private PlayListService service;

    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allPlayList(@PathVariable Integer currentPage, @PathVariable Integer pageSize,Integer uid){
        Page<PlayList> pageInfo = new Page<>(currentPage,pageSize);
        service.selectByPage(pageInfo,uid);
        return R.success(pageInfo,"用户定制歌单");
    }

    @GetMapping("/{pid}")
    public R<PlayList> getPlayListByid(@PathVariable Integer pid){
        return R.success(service.getById(pid),"指定歌单信息");
    }

    //添加歌单
    @PostMapping
    public R<String> addPlayList(@RequestBody PlayList playList){
        return service.addPlayList(playList);
    }

    //更新封面
    @PostMapping("/upload/image")
    public R<String> updatePlayListPic(@RequestParam("file")MultipartFile multipartFile,@RequestParam("pid") int pid) throws FileNotFoundException {
        return service.updatePlayListPic(multipartFile,pid);
    }

    //删除定制歌单
    @DeleteMapping("/{pid}")
    public R<String> deletePlayList(@PathVariable Integer pid){
        return service.deletePlayList(pid);
    }


    //更新歌单
    @PutMapping
    public R<String> updatePlayList(@RequestBody PlayList playList) {
        if(service.updateById(playList)){
            return R.success("歌单信息更新成功");
        }else {
            return R.error("歌单信息更新失败");
        }
    }

}
