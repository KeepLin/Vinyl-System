package com.adminserver.mapper;

import com.adminserver.pojo.SongList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface SongListMapper extends BaseMapper<SongList> {

    @Select("<script>"+
            "select s.* from song_list s"+
            "<where>"+
            "<if test='title != null'>"+
            "s.title LIKE CONCAT(#{title},'%')"+
            "</if>"+
            "<if test='style != null'>"+
            "and s.style LIKE CONCAT('%',#{style},'%')"+
            "</if>"+
            "</where >"+
            "</script>")
    List<SongList> selectByPage(Page<SongList> page , @Param("title") String title, @Param("style") String style);
}
