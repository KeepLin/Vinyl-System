package com.fegin.service;

import com.fegin.pojo.Singer;
import com.fegin.pojo.SongList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "adminservice")
public interface UserClientService {
    @GetMapping("/singer/allSinger")
    List<Singer> FindAllSinger();

    @GetMapping("/SongMenu/allSongList")
    List<SongList> FindAllSongList();
}
