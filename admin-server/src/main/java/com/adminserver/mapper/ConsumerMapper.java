package com.adminserver.mapper;

import com.adminserver.pojo.Consumer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {
    @Select("<script>"+
            "select u.* from consumer u"+
            "<where>"+
            "<if test='id != null'>"+
            "u.user_id = #{id}"+
            "</if>"+
            "<if test='sex != null'>"+
            "and u.sex = #{sex}"+
            "</if>"+
            "</where >"+
            "</script>")
    List<Consumer> selectByPage(Page<Consumer> page, @Param("id") Integer id, @Param("sex") Integer sex);
}
