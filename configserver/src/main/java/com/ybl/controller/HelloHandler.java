package com.ybl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/config")
@RestController
public class HelloHandler {
  @Value("${server.port}")
  private int port;

  @RequestMapping(value = "/index")
  public String index() {
    return "当前端口：" + this.port;
  }
}
