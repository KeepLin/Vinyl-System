package com.adminserver.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@Configuration
public class AutoBrowser {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        //System.out.println("应用已经准备就绪 ... 启动浏览器");
        // 这里需要注url:端口号+测试类方法名
        String url = "http://localhost:8080/static/Admin/Login.html";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
