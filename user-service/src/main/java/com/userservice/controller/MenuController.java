package com.userservice.controller;

import com.fegin.pojo.Singer;
import com.fegin.pojo.SongList;
import com.fegin.service.UserClientService;
import com.userservice.resultUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private UserClientService service;

    @GetMapping("/singer")
    R<List<Singer>> FindAllSinger(){
        return R.success(service.FindAllSinger(),"歌手列表");
    }

    @GetMapping("/songList")
    R<List<SongList>> FindAllSongList(){
        return R.success(service.FindAllSongList(),"歌当列表");
    }






}
