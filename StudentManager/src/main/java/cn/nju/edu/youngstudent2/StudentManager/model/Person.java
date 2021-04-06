package cn.nju.edu.youngstudent2.StudentManager.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Person extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = -7975146055806762690L;

    @Column(name = "name")
    @NotEmpty
    protected String name;
    
    @Column(name = "sex")
    @NotEmpty
    protected String sex;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthday;

    @Column(name = "hometown")
    protected String hometown;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getHometown() {
        return this.hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }  

    
}
