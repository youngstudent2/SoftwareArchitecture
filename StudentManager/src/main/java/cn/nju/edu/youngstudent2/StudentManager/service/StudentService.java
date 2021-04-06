package cn.nju.edu.youngstudent2.StudentManager.service;

import java.util.Collection;

import cn.nju.edu.youngstudent2.StudentManager.model.Student;

public interface StudentService {
    
    Student findStudentById(int id);

    Collection<Student> findStudentsByName(String name);
    
    Collection<Student> findAll();
    
    void saveStudent(Student stu);

    void removeStudent(int id);
}
