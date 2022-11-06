package com.adminserver.service.impl;

import com.adminserver.mapper.SongListMapper;
import com.adminserver.pojo.SongList;
import com.adminserver.resultUtils.R;
import com.adminserver.service.SongListService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public Page<SongList> selectByPage(Page<SongList> page, String title, String style) {

        return page.setRecords(this.baseMapper.selectByPage(page,title,style));
    }

    @Override
    public R<String> addSongList(SongList songList) {
        SongList temp = new SongList();
        BeanUtils.copyProperties(songList, temp);
        String pic = "/img/songListPic/";
        //pic=C:\fakepath\1560025319294L5.jpg,
        String[] split = temp.getPic().split("\\\\");
        temp.setPic(pic+split[2]);
        //System.out.println(temp.toString());

        if (songListMapper.insert(temp) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R<String> updateSongListPic(MultipartFile multipartFile, int id) throws FileNotFoundException {
        String filename = multipartFile.getOriginalFilename(); //获取文件名
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            return R.error("文件类型不对");
        }
        //当前项目路径
        //图片存储路径
        String filePath = ResourceUtils.getURL("/MavenProject/Vinyl-System/admin-server/src/main/resources/static/img").getPath().substring(1)+"songListPic";


        try {
            File floder = new File(filePath);
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
        //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/static/img/songPic/"+filename;
        String imageUrl = "/img/songListPic/"+filename;

        SongList songlist = songListMapper.selectById(id);
        if (songlist!=null){
            songlist.setPic(imageUrl);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id",songlist.getId());
            updateWrapper.set("pic",songlist.getPic());
            int flag = songListMapper.update(songlist,updateWrapper);
            if (flag>0){
                return R.success("此歌单图片更新成功");
            }else {
                return R.error("此歌单图片更新失败");
            }
        }else {
            return R.error("没有此歌单信息");
        }
    }

    @Override
    public List<Integer> countStyle() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("style");
        List<String> styleList = listObjs(queryWrapper, Object::toString);
        for (String item:styleList){
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

    //模糊查询歌单
    @Override
    public List<SongList> SearchByTitle(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return baseMapper.selectList(queryWrapper);
    }
}
