package com.adminserver.controller;

import com.adminserver.resultUtils.R;
import com.adminserver.service.ListSongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ListSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    @GetMapping("/{SongId}/{SongListId}")
    public R<String> deleteSong(@PathVariable Integer SongId,@PathVariable Integer SongListId){
        if (listSongService.DeleteSong(SongId,SongListId)){
            return R.success("歌曲删除成功");
        }else {
            return R.error("歌曲删除失败");
        }
    }

}
