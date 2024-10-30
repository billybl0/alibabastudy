package com.ybl.controller;

import com.ybl.entity.Student;
import com.ybl.service.FeignProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RequestMapping("/hystrix")
@RestController
public class HystrixHandler {
  @Autowired
  private FeignProviderClient feignProviderClient;

  @GetMapping("/index")
  public String index(){
    return feignProviderClient.index();
  }
  @GetMapping("/findAll")
  public Collection<Student> findAll(){
    return feignProviderClient.findAll();
  } 
}
