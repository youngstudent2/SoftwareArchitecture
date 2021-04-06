package cn.nju.edu.youngstudent2.StudentManager.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nju.edu.youngstudent2.StudentManager.model.Student;
import cn.nju.edu.youngstudent2.StudentManager.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "student", key = "#id")
    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "students", key = "#name")
    public Collection<Student> findStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    @Transactional
    @CacheEvict(value = "students", allEntries = true)
    public void saveStudent(Student stu) {
        this.studentRepository.save(stu);
        
    }

    @Override
    @Transactional
    @CacheEvict(value = "students", allEntries = true)
    public void removeStudent(int id) {
        this.studentRepository.removeById(id);;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("students")
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    
}
