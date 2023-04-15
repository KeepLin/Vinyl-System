package com.adminserver.mapper;

import com.adminserver.pojo.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper extends BaseMapper<Song> {

    //歌曲分页信息展示
    @Select("<script>"+
            "select s.* from song s"+
            "<where>"+
            "<if test='name != null'>"+
            "s.name LIKE CONCAT('%',#{name},'%')"+
            "</if>"+
            "</where >"+
            "</script>")
    List<Song> selectByPage(Page<Song> page, @Param("name") String name);


    //查询歌单的歌曲集合
    @Select("<script>"+
            "select s.* from song s"+
            "<where>"+
            "<if test='id != null'>"+
            "s.song_id IN (select song_id from list_song " +
            "<where>"+
            "<if test='id != null'>"+
            "song_list_id = #{id}"+
            "</if>"+
            "</where>)"+
            "</if>"+
            "<if test='name != null'>"+
            "and s.name LIKE CONCAT('%',#{name},'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Song> selectBySongList(Page<Song> page, @Param("id") Integer id,@Param("name") String name);

    //歌单中介表索引对应歌曲
    @Select("<script>"+
            "select s.* from song s"+
            "<where>"+
            "<if test='id != null'>"+
            "s.song_id IN (select song_id from list_song " +
            "<where>"+
            "<if test='id != null'>"+
            "song_list_id = #{id}"+
            "</if>"+
            "</where>)"+
            "</if>"+
            "<if test='name != null'>"+
            "and s.name LIKE CONCAT('%',#{name},'%')"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Song> selectBySongListId(@Param("id") Integer id,@Param("name") String name);

    //索引客户的歌单歌曲
    @Select("<script>"+
            "select s.* from song s"+
            "<where>"+
            "s.song_id IN (select song_id from stamp " +
            "<where>"+
            "<if test='pid != null'>"+
            "playlist_id = #{pid}"+
            "</if>"+
            "</where>)"+
            "</where>"+
            "</script>")
    List<Song> selectByStamp(@Param("pid") Integer pid);
}
