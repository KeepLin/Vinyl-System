package com.adminserver.controller;

import com.adminserver.pojo.Song;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SongService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    //统计歌曲数量
    @GetMapping("/count")
    public R<Integer> countSong(){
        Integer songCount = 0;
        return R.success(songCount,"redis歌曲总数");
    }


    //根据歌曲ID查询歌曲信息
    @GetMapping("/{id}")
    public R<Song> getById(@PathVariable Integer id){

        Song finder=songService.getById(id);
        if(finder!=null){
            return R.success(finder,"查到此歌曲信息");
        }
        return R.error("没有此歌曲信息");
    }

    //分页显示
    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allSinger(@PathVariable Integer currentPage, @PathVariable Integer pageSize, String name,Integer singerid){
        Page<Song> pageInfo = new Page(currentPage,pageSize);
        if(name != null && name.length()>0){
            songService.selectByPage(pageInfo,name,null);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }else if (singerid!=null){
            songService.selectByPage(pageInfo,null,singerid);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }else {
            songService.selectByPage(pageInfo,null,null);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }

    }

    //添加歌曲
    @PostMapping
    public R<String> addSong(@RequestBody Song song){
        if(songService.AddSong(song)){
            return R.success("歌曲添加成功");
        }else {
            return R.success("歌曲添加失败");
        }

    }

    //删除歌曲
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Integer id) throws FileNotFoundException {
        if (songService.removeById(id)){
            return R.success("歌曲删除成功");
        }else {
            return R.error("歌曲删除失败");
        }
    }


    // 更新歌曲信息
    @PutMapping
    public R<String> updateSingerMsg(@RequestBody Song updateSongRequest) {
        if (songService.UpdateMsg(updateSongRequest)){
            return R.success("歌曲信息更新成功");
        }else {
            return R.error("歌曲信息更新失败");
        }
    }


    //模糊查询歌手歌曲
    @GetMapping("/SongByName")
    public R<List<Song>> SongByName(String name){
        List<Song> songList = songService.SearchByName(name);
        if (songList!=null){
            return R.success(songList,"查询此歌曲");
        }else {
            return R.error("系统暂无符合条件的歌曲");
        }

    }
    //根据歌手id查找歌曲
    @GetMapping("/SongById/{singerId}")
    public R<List<Song>> SongByName(@PathVariable Integer singerId){
        List<Song> songList = songService.SearchById(singerId);
        if (songList!=null){
            return R.success(songList,"查询此歌曲");
        }else {
            return R.error("系统暂无符合此歌手的歌曲");
        }
    }


}
