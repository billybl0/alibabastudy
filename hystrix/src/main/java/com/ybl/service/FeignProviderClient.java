package com.ybl.service;

import com.ybl.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * 创建 FeignProviderClient 接口
 * @FeignClient 指定 Feign 要调用的微服务，直接指定服务提供者 Provider 在注册中心的 application name 即可
 */
@FeignClient(value = "provider")
public interface FeignProviderClient {
  @GetMapping("/student/index")
  public String index();
  @GetMapping("/student/findAll")
  public Collection<Student> findAll();
  @GetMapping("/student/findById/{id}")
  public Student findById(@PathVariable("id") long id);
  @PostMapping("/student/save")
  public void save(@RequestBody Student student);
  @PutMapping("/student/update")
  public void update(@RequestBody Student student);
  @DeleteMapping("/student/deleteById/{id}")
  public void deleteById(@PathVariable("id") long id);
}
