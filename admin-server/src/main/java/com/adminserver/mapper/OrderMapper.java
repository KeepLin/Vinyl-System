package com.adminserver.mapper;

import com.adminserver.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("<script>"+
            "select o.* from orders o"+
            "<where>"+
            "<if test='creatime != null'>"+
            "and o.Creatime = #{creatime}"+
            "</if>"+
            "</where>"+
            "</script>")
    List<Order> selectByPage(Page<Order> page, @Param("creatime") String creatime);
}
