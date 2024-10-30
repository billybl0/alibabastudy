package com.ybl.service;

import com.ybl.entity.Student;
import org.springframework.stereotype.Component;
import java.util.Collection;

/**
 * 创建 FeignProviderClient 接口的实现类 FeignError ，定义容错处理逻辑，
 * 通过 @Component 将 FeignError 实例注入 IoC 容器
 */
@Component
public class FeignError implements FeignProviderClient {
  @Override
  public String index() { return "服务器维护中...."; }
  @Override
  public Collection<Student> findAll() { return null; }

  @Override
  public Student findById(long id) {
    return null;
  }

  @Override
  public void save(Student student) {

  }

  @Override
  public void update(Student student) {

  }

  @Override
  public void deleteById(long id) {

  }
}
