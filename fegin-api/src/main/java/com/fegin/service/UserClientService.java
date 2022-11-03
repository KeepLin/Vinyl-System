package com.fegin.service;

import com.fegin.pojo.Singer;
import com.fegin.pojo.Song;
import com.fegin.pojo.SongList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "adminservice")
public interface UserClientService {
    @GetMapping("/singer/allSinger")
    List<Singer> FindAllSinger();

    @GetMapping("/SongMenu/allSongList")
    List<SongList> FindAllSongList();

    @GetMapping("/song/SongBySingerId/{id}")
    List<Song> SongBySingerId(@PathVariable Integer id);

    @GetMapping("/song/SongBySongListId/{id}")
    List<Song> SongBySongListId(@PathVariable Integer id,@RequestParam("name") String name);

}
