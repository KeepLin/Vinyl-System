package com.userservice.controller;

import com.userservice.pojo.Stamp;
import com.userservice.resultUtils.R;
import com.userservice.service.StampService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/stamp")
public class StampController {

    @Autowired
    private StampService stampService;

    @PostMapping
    public R<String> addStamp(@RequestBody Stamp stamp){
        return stampService.AddStamp(stamp);
    }

    @DeleteMapping("/{pid}/{songId}")
    public R<String> DeleteSongByID(@PathVariable Integer pid, @PathVariable Integer songId){
        if(stampService.DeleteSong(pid,songId)){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @DeleteMapping("/{pid}")
    public R<String> DeleteListByPid(@PathVariable Integer pid){
        return stampService.DeleteList(pid);
    }

}
