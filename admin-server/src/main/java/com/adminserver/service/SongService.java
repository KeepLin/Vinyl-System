package com.adminserver.service;


import com.adminserver.pojo.Song;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;


public interface SongService extends IService<Song> {
    //分页显示
    Page<Song> selectByPage(Page<Song> page, String name, Integer singerid);

    //歌单分页显示
    Page<Song> selectBySongList(Page<Song> page, Integer id,String name );

    //添加歌曲
    Boolean AddSong(Song song);

    //更新歌曲信息
    Boolean UpdateMsg(Song SongRequest);

    //模糊查询歌手歌曲
    List<Song> SearchByName(String name);

    //根据歌手id查找歌曲
    List<Song> SearchById(Integer id);

    //统计歌曲数量
    Integer countSong();

    //歌单歌曲
    List<Song> selectBySongListId(Integer id,String name);

    //索引客户歌单
    List<Song> selectByStamp(Integer pid);
}
