package com.userservice.controller;

import com.fegin.pojo.Singer;
import com.fegin.pojo.Song;
import com.fegin.pojo.SongList;
import com.fegin.service.UserClientService;
import com.userservice.resultUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private UserClientService service;

    @GetMapping("/singer")
    R<List<Singer>> FindAllSinger(@RequestParam ("sex") Integer sex){
        return R.success(service.FindAllSinger(sex),"歌手列表");
    }

    @GetMapping("/songList")
    R<List<SongList>> FindAllSongList(@RequestParam ("style") String style){
        return R.success(service.FindAllSongList(style),"歌单列表");
    }

    @GetMapping("/SongBySingerId/{id}")
    R<List<Song>> SongBySingerId(@PathVariable Integer id){
        return R.success(service.SongBySingerId(id),"歌手作品");
    }

    @GetMapping("/SongBySongListId/{id}")
    R<List<Song>> SongBySongListId(@PathVariable Integer id, @RequestParam("name") String name){
        return R.success(service.SongBySongListId(id,name),"歌单作品");
    }

    @GetMapping("/SongByName")
    R<List<Song>> SongBySearch(@RequestParam("name") String name){
        return R.success(service.SearchName(name),"查询歌曲成功");
    }

    @GetMapping("/SongBySinger")
    R<List<Singer>> SongBySingerName(@RequestParam("name") String name) {
        return R.success(service.SearchBySinger(name),"查询歌手成功");
    }

    @GetMapping("/SongByTitle")
    R<List<SongList>> SongByTitle(@RequestParam("title") String title){
        return R.success(service.SearchByTitle(title),"查询歌单");
    }





}
