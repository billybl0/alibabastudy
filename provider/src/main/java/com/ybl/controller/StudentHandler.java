package com.ybl.controller;

import com.ybl.entity.Student;
import com.ybl.service.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequestMapping("/student")
@RestController
public class StudentHandler {
    @Autowired
    private StudentRepository studentRepository;
    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        return "当前端口：" + this.port;
    }
    @GetMapping("/findAll")
    public Collection<Student> findAll() {
        log.debug("查询所有-------------------");
        return new ArrayList<Student>();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id) {
        Student student = new Student();
        student.setId(id);
        student.setName("提供者yyds");
        return student;
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student) {
        log.debug("保存-------------------");
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student) {
        log.debug("修改-------------------");
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        log.debug("删除-------------------");
    }
}
