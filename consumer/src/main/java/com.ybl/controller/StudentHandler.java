package com.ybl.controller;

import com.ybl.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Collection;

/**
 * 6、创建 StudentHandler，注入 RestTemplate 实例，业务方法中通过 RestTemplate 来调用 provider 的相关服务。
 */
@RequestMapping("/consumer")
@RestController
public class StudentHandler {
  public static final String ADDR_URL = "http://localhost:8010/student/";
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/findAll")
  public Collection<Student> findAll(){
    return restTemplate.getForObject(ADDR_URL + "findAll",Collection.class);
  }
  @GetMapping("/findById/{id}")
  public Student findById(@PathVariable("id") long id){
    return restTemplate.getForObject(ADDR_URL + "findById/{id}",Student.class,id);
  }
  @PostMapping("/save")
  public void save(@RequestBody Student student){
    restTemplate.postForObject(ADDR_URL + "save",student,Student.class);
  }
  @PutMapping("/update")
  public void update(@RequestBody Student student){
    restTemplate.put(ADDR_URL + "update",student);
  }
  @DeleteMapping("/deleteById/{id}")
  public void deleteById(@PathVariable("id") long id){
    restTemplate.delete(ADDR_URL + "deleteById/{id}",id);
  }
}
