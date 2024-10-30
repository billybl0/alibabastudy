package com.ybl.controller;

import com.ybl.entity.Student;
import com.ybl.service.FeignProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

/**
 * 创建 FeignHandler，通过 @Autowired 注入 FeignProviderClient 实例，完成相关业务
 */
@RequestMapping("/feign")
@RestController
public class FeignHandler {
  @Autowired
  private FeignProviderClient feignProviderClient;

  /**
   * 8、Feign 也提供了负载均衡功能。访问请求服务方法，端口为 8010 和 8011 的微服务交替被访问
   * @return
   */
  @GetMapping("/index")
  public String index(){
    return feignProviderClient.index();
  }
  @GetMapping("/findAll")
  public Collection<Student> findAll(){
    return feignProviderClient.findAll();
  }
}
