package com.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.PlayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlayListMapper extends BaseMapper<PlayList> {
    @Select("<script>"+
            "select p.* from playlist p"+
            "<where>"+
            "<if test='uid != null'>"+
            "p.uid = #{uid}"+
            "</if>"+
            "</where >"+
            "</script>")
    List<PlayList> selectByPage(Page<PlayList> page, @Param("uid") Integer uid);
}
