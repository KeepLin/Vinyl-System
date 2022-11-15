package com.adminserver.controller;

import com.adminserver.pojo.ListSong;
import com.adminserver.resultUtils.R;
import com.adminserver.service.ListSongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public R<String> addListSong(@RequestBody ListSong temp){
        if (listSongService.AddSong(temp)){
            return R.success("歌曲添加成功");
        }else {
            return R.success("歌曲已在歌单中");
        }
    }

    @DeleteMapping("/{SongListId}")
    public R<String> deleteRecord(@PathVariable Integer SongListId){
        if (listSongService.DeleteRecord(SongListId)){
            return R.success("歌曲删除成功");
        }else {
            return R.error("歌曲删除失败");
        }
    }

}
