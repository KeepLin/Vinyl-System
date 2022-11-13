package com.userservice.controller;

import com.userservice.pojo.Stamp;
import com.userservice.resultUtils.R;
import com.userservice.service.StampService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
