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
    @GetMapping("/singer/SingerBySex")
    List<Singer> FindAllSinger(@RequestParam ("sex") Integer sex);

    @GetMapping("/SongMenu/SongListByStyle")
    List<SongList> FindAllSongList(@RequestParam ("style") String style);

    @GetMapping("/song/SongBySingerId/{id}")
    List<Song> SongBySingerId(@PathVariable Integer id);

    @GetMapping("/song/SongBySongListId/{id}")
    List<Song> SongBySongListId(@PathVariable Integer id,@RequestParam("name") String name);

    @GetMapping("/song/Search")
    List<Song> SearchName(@RequestParam("name") String name);

    @GetMapping("/singer/SearchBySinger")
    List<Singer> SearchBySinger(@RequestParam("name") String name);

    @GetMapping("/SongMenu/SearchByTitle")
    List<SongList> SearchByTitle(@RequestParam("title") String title);
}
