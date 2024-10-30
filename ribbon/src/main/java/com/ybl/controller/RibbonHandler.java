package com.ybl.controller;

import com.ybl.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * 5、使用 Ribbon 调用 Provider 服务
 * 创建 RibbonHandler，通过 RestTemplate 调用 Provider 相关接口
 * RestTemplate 访问的 URL 不需要指定 IP 和 端口，直接访问 Provider 在 Eureka Server 注册的 application name 即可。
 * 比如：http://localhost:8010/ 可替换为 http://provider/
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonHandler {
  @Autowired
  private RestTemplate restTemplate;
  @GetMapping("/index")
  public String index(){
    return restTemplate.getForObject("http://provider/student/index",String.class);
  }

  @GetMapping("/findAll")
  public Collection<Student> findAll() {
    return restTemplate.getForObject("http://provider/student/findAll", Collection.class);
  }
}
