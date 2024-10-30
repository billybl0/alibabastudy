package com.ybl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//4、创建 ZipkinHandler，定义相关业务方法
@RestController
@RequestMapping("/zipkin")
public class ZipkinHandler {

    @Value("${server.port}")
    private String port;
    @GetMapping("/index")
    public String index() {
        return "当前端口：" + this.port;
    }

}
