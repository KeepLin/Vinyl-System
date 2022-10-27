package com.adminserver.service.impl;

import com.adminserver.mapper.SingerMapper;
import com.adminserver.pojo.Singer;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    //数据分页展示
    @Override
    public Page<Singer> selectByPage(Page<Singer> page, String name, Integer sex) {
        return page.setRecords(this.baseMapper.selectByPage(page,name,sex));
    }

    //歌手图片上传
    @Override
    public R<String> updateSingerPic(MultipartFile multipartFile, int id) throws FileNotFoundException {
        String filename = multipartFile.getOriginalFilename(); //获取文件名
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            return R.error("文件类型不对");
        }
        //当前项目路径
        String f = ResourceUtils.getURL("/MavenProject/Vinyl-System/admin-server/src/main/resources/static/img").getPath().substring(1);
        //图片存储路径
        String realPath = f+"singerPic";
        //String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"+"\\"+"main"+"\\"+"resources"+"\\"+"static"+"\\"+"img" + System.getProperty("file.separator") + "singerPic";
        System.out.println(realPath);

        try {
            File floder = new File(realPath);
            if (!floder.exists()){
                floder.mkdirs();
            }
            //将上传的图片保存到指定文件夹
            multipartFile.transferTo(new File(floder,filename));
        }catch (IOException e){
            e.printStackTrace();
            return R.error("保存失败");
        }

        //静态资源访问路径
        //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/static/img/singerPic/"+filename;

        String imageUrl = "/img/singerPic/"+filename;
        //System.out.println("数据库存储路径"+imageUrl);
        //数据库存储路径
        Singer singer = singerMapper.selectById(id);
        if (singer!=null){
            singer.setPic(imageUrl);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id",singer.getId());
            updateWrapper.set("pic",singer.getPic());
            int flag = singerMapper.update(singer,updateWrapper);
            if (flag>0){
                return R.success("歌手图片更新成功");
            }else {
                return R.error("歌手图片更新失败");
            }
        }else {
            return R.error("没有此歌手");
        }
    }

    //统计歌手数量
    @Override
    public Integer countSinger() {
        return count();
    }

    //统计歌手性别
    @Override
    public List<Integer> countSingerSex() {
        QueryWrapper<Singer> SexWrapper = new QueryWrapper<>();
        SexWrapper.eq("sex",0);
        Integer Girl = count(SexWrapper);
        SexWrapper.clear();

        SexWrapper.eq("sex",1);
        Integer Boy = count(SexWrapper);
        List<Integer> SexArray= new ArrayList<>();
        SexArray.add(Girl);
        SexArray.add(Boy);
        return SexArray;
    }

    //统计歌手国籍
    @Override
    public List<Integer> countCountry() {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        QueryWrapper<Singer> countryWrapper=new QueryWrapper<>();
        countryWrapper.select("location");
        List<String> countryList = listObjs(countryWrapper, Object::toString);
        for (String item:countryList){
            if(map.containsKey(item)){
                Integer count=map.get(item)+1;
                map.put(item,count);
            }else {
                map.put(item,1);
            }
        }
        Collection<Integer> collection = map.values();
        List<Integer> integerList = new ArrayList<>(collection);

        return integerList;
    }

    // 添加歌手
    @Override
    public Boolean AddSinger(Singer addSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(addSingerRequest, singer);
        String imgPath = "/img/singerPic/"+singer.getPic();
        singer.setPic(imgPath);
        boolean flag = save(singer);
        return flag;
    }

}
