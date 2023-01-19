package com.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.userservice.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    @Select("<script>"+
            "select s.*,p.title,p.pic,p.score from playlist p,shop s"+
            "<where>"+
            "<if test='Uid != null'>"+
            "s.user_id = #{Uid} "+
            "</if>"+
            "and s.playlist_id = p.playlist_id"+
            "</where >"+
            "</script>")
    List<Shop> selectByPage(Page<Shop> page, @Param("Uid") Integer Uid);
}
