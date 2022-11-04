package com.adminserver.controller;

import com.adminserver.pojo.Singer;
import com.adminserver.pojo.Song;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    //统计歌手数量
    @GetMapping("/count")
    public R<Integer> countSinger(){
        return R.success(singerService.countSinger(),"歌手人数");
    }

    //统计歌手性别
    @GetMapping("/count/singerSex")
    public R<List<Integer>> countSingerSex(){

       return R.success(singerService.countSingerSex(),"歌手性别比例");
    }

    //统计歌手国籍
    @GetMapping("/count/country")
    public R<List<Integer>> countCountry(){
    //data: ["中国", "韩国", "意大利", "新加坡", "美国", "马来西亚", "西班牙", "日本"]
        return R.success(singerService.countCountry(),"歌手国籍统计");

    }
    // 添加歌手
    @PostMapping
    public R<String> addSinger(@RequestBody Singer addSingerRequest) {
        boolean flag = singerService.AddSinger(addSingerRequest);
        if (flag){
            return R.success("歌手添加成功");
        }else {
            return R.error("歌手添加失败");
        }

    }

    // 删除歌手
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Integer id) throws FileNotFoundException {
        Singer singer = singerService.getById(id);
        String imageUrl = System.getProperty("user.dir") +singer.getPic();

        File imageFile = new File(imageUrl);
        if (imageFile.isFile()&&imageFile.exists()){
            imageFile.delete();
        }
        if (singerService.removeById(id)){
            return R.success("歌手删除成功");
        }else {
            return R.error("歌手删除失败");
        }
    }

    //编辑歌手查找
    @GetMapping("/{id}")
    public R<Singer> getById(@PathVariable Integer id){

        Singer finder=singerService.getById(id);
        if(finder!=null){
            return R.success(finder,"查到此歌手信息");
        }
        return R.error("没有此歌手信息");
    }

    // 歌手数据页面展示
    @GetMapping("/{currentPage}/{pageSize}")
    public R<Page> allSinger(@PathVariable Integer currentPage,@PathVariable Integer pageSize,String name,Integer sex) {
        Page<Singer> pageInfo = new Page(currentPage,pageSize);
        if (name != null && name.length()>0){
            singerService.selectByPage(pageInfo,name,null);
            return R.success(pageInfo,"查询指定歌手信息");
        }else if (sex != null){
            singerService.selectByPage(pageInfo,null,sex);
            return R.success(pageInfo,"查询指定性别歌手信息");
        }else{
            singerService.selectByPage(pageInfo, name != null ? name : null, sex != null ? sex : null);
            return R.success(pageInfo,"全部的歌手数据");
        }
    }

    // 更新歌手信息
    @PutMapping
    public R<String> updateSingerMsg(@RequestBody Singer updateSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(updateSingerRequest, singer);
        boolean flag = singerService.updateById(singer);
        if (flag){
            return R.success("歌手信息更新成功");
        }else {
            return R.error("歌手信息更新失败");
        }
    }

    // 更新歌手头像
    @PostMapping("/upload/image")
    public R<String> updateSingerPic(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request,@RequestParam("id") int id) throws FileNotFoundException {
        return singerService.updateSingerPic(multipartFile,id);
    }

    @GetMapping("/SingerBySex")
    public List<Singer> AllSinger(@RequestParam ("sex") Integer sex){
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        if (sex == 6){
            queryWrapper.last("limit 10");
        }else if (sex == 5){
            queryWrapper.clear();
        }else {
            queryWrapper.eq("sex",sex);
        }
        return singerService.list(queryWrapper);
    }

}
