package com.ybl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//4、创建 HelloHandler，定义相关业务方法。
@RequestMapping("/config")
@RestController
public class HelloHandler {
//  @Value("${server.port}")
//  private int port;
  private int port =8070;

  @RequestMapping(value = "/index")
  public String index() {
    return "当前端口：" + this.port;
  }
}
