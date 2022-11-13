package com.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.userservice.mapper.PlayListMapper;
import com.userservice.pojo.PlayList;
import com.userservice.resultUtils.R;
import com.userservice.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class PlayListServiceImpl extends ServiceImpl<PlayListMapper, PlayList> implements PlayListService {

    @Autowired
    private PlayListMapper playListMapper;

    @Override
    public Page<PlayList> selectByPage(Page<PlayList> page, Integer uid) {
        return page.setRecords(this.baseMapper.selectByPage(page,uid));
    }

    @Override
    public R<String> updatePlayListPic(MultipartFile multipartFile, int pid) throws FileNotFoundException {
        //获取上传图片文件名
        String filename = multipartFile.getOriginalFilename();
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            return R.error("文件类型不对");
        }
        //图片存储路径
        String filePath ="E:/static/img/playListPic";
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
        String imageUrl = "/img/playListPic/"+filename;
        PlayList playList = playListMapper.selectById(pid);
        if (playList != null){
            playList.setPic(imageUrl);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("pid",playList.getPid());
            updateWrapper.set("pic",playList.getPic());
            int flag = playListMapper.update(playList, updateWrapper);
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
    public R<String> deletePlayList(Integer pid) {
        PlayList playList = playListMapper.selectById(pid);
        String imageUrl = "E:/static"+playList.getPic();
        //删除本地图片
        File imageFile = new File(imageUrl);
        if (imageFile.isFile()&&imageFile.exists()){
            imageFile.delete();
        }
        if(playListMapper.deleteById(pid)>0){
            return R.success("歌单删除成功");
        }else {
            return R.error("歌单删除失败");
        }
    }

    @Override
    public R<String> addPlayList(PlayList playList) {
        String pic = "/img/playListPic/tubiao.jpg";
        playList.setPic(pic);
        if (playListMapper.insert(playList)>0){
            return R.success("定制歌单创建成功");
        }else {
            return R.error("定制歌单创建失败");
        }
    }

}
