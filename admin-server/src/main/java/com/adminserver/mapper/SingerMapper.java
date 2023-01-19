package com.adminserver.mapper;

import com.adminserver.pojo.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SingerMapper extends BaseMapper<Singer> {
    @Select("<script>"+
            "select s.* from singer s"+
            "<where>"+
            "<if test='name != null'>"+
            "s.singer_name LIKE CONCAT('%',#{name},'%')"+
            "</if>"+
            "<if test='sex != null'>"+
            "and s.sex = #{sex}"+
            "</if>"+
            "</where >"+
            "</script>")
    List<Singer> selectByPage(Page<Singer> page, @Param("name") String name, @Param("sex") Integer sex);


}
