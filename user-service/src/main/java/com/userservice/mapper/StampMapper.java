package com.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.userservice.pojo.Stamp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StampMapper extends BaseMapper<Stamp> {
    @Delete("<script>"+
            "delete from stamp s"+
            "<where>"+
            "<if test='playlistId != null'>"+
            "s.playlist_id = #{playlistId} "+
            "</if>"+
            "<if test='songId != null'>"+
            "and s.song_id = #{songId}"+
            "</if>"+
            "</where >"+
            "</script>")
    Boolean DeleteSong(@Param("playlistId") Integer playlistId, @Param("songId") Integer songId);

}
