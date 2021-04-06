package cn.nju.edu.youngstudent2.StudentManager.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import cn.nju.edu.youngstudent2.StudentManager.model.Student;

@org.springframework.stereotype.Repository
public interface StudentRepository extends Repository<Student, Integer>{
    
    @Query("SELECT stu FROM Student stu WHERE stu.id =:id")
    Student findById(@Param("id") Integer id);

    @Query("SELECT stu FROM Student stu WHERE stu.name LIKE :name%")
    Collection<Student> findByName(@Param("name") String name);

    @Query("SELECT stu FROM Student stu")
    Collection<Student> findAll();
    
    void save(Student stu);

    @Modifying
    @Query("DELETE FROM Student WHERE id =:id")
    void removeById(@Param("id") Integer id);
}
