package com.adminserver.controller;

import com.adminserver.pojo.Song;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SongService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return R.success(songService.countSong(), "歌曲总数");
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
    public R<Page> allSong(@PathVariable Integer currentPage, @PathVariable Integer pageSize, String name){
        Page<Song> pageInfo = new Page(currentPage,pageSize);
        if(name != null && name.length()>0){
            songService.selectByPage(pageInfo,name);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }else {
            songService.selectByPage(pageInfo,null);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }
    }

    //通过歌单ID获取歌曲信息
    @GetMapping("/SongList/{currentPage}/{pageSize}")
    public R<Page> selectBySongList(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Integer id,String name){
        Page<Song> pageInfo = new Page(currentPage,pageSize);
        if(name != null && name.length()>0){
            songService.selectBySongList(pageInfo,id,name);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }else {
            songService.selectBySongList(pageInfo,id,null);
            return R.success(pageInfo,"数据库歌曲消息列表");
        }
    }

    //添加歌曲
    @PostMapping
    public R<String> addSong(@RequestBody Song song){
        if(songService.AddSong(song)){
            return R.success("歌曲添加成功");
        }else {
            return R.error("歌曲添加失败");
        }

    }

    //删除歌曲
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Integer id){
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

    //提供用户使用查询歌曲
    @GetMapping("/Search")
    public List<Song> SearchName(@RequestParam("name") String name){
        return songService.SearchByName(name);
    }

    //根据歌手id查找歌曲
    @GetMapping("/SingerId/{singerId}")
    public R<List<Song>> SongByName(@PathVariable Integer singerId){
        List<Song> songList = songService.SearchById(singerId);
        if (songList!=null){
            return R.success(songList,"查询此歌手歌曲");
        }else {
            return R.error("系统暂无符合此歌手的歌曲");
        }
    }

    //返回歌手的作品
    @GetMapping("/SongBySingerId/{id}")
    public List<Song> SongBySingerId(@PathVariable Integer id){
        return songService.SearchById(id);
    }

    //搜索歌单中的曲库
    @GetMapping("/SongBySongListId/{id}")
    public List<Song> SongBySongListId(@PathVariable Integer id,@RequestParam("name") String name){
        if(name != null && name.length()>0) {
            return songService.selectBySongListId(id,name);
        }else {
            return songService.selectBySongListId(id,null);
        }
    }

    //索引客户歌单
    @GetMapping("/SongByStamp/{pid}")
    public List<Song> SongByStamp(@PathVariable Integer pid){
        return songService.selectByStamp(pid);
    }

    //删除歌手作品
    @DeleteMapping("/SingerById/{singerId}")
    public R<String> DeleteSongBySingerId(@PathVariable Integer singerId){
        if(songService.DeleteSingerBySong(singerId)){
            return R.success("删除歌手作品成功");
        }else {
            return R.error("删除歌手作品失败");
        }
    }

}
