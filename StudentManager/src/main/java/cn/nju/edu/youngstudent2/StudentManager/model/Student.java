package cn.nju.edu.youngstudent2.StudentManager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person{
    /**
     *
     */
    private static final long serialVersionUID = 5339168563748924159L;
    @Column(name = "faculty") 
    @NotEmpty
    protected String faculty;


    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

}
