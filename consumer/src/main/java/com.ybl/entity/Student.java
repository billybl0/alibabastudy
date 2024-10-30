package com.ybl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 4、现在让 consumer 调用 provider 提供的服务，首先创建实体类 Student。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private long id;
  private String name;
  private char gender;
}
