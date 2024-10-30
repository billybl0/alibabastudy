package com.ybl.service.impl;

import com.ybl.entity.Student;
import com.ybl.service.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author ybl
 * @create 2024-07-20 23:32
 */
@Service
public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Student student) {

    }

    @Override
    public void deleteById(long id) {

    }
}
