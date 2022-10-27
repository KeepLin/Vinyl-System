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

    @Select("<script>"+
            "select s.* from song s"+
            "<where>"+
            "<if test='name != null'>"+
            "s.name LIKE CONCAT('%',#{name},'%')"+
            "</if>"+
            "<if test='singerid != null'>"+
            "and s.singer_id =#{singerid}"+
            "</if>"+
            "</where >"+
            "</script>")
    List<Song> selectByPage(Page<Song> page, @Param("name") String name,@Param("singerid") Integer singerid);
}
