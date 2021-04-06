package cn.nju.edu.youngstudent2.StudentManager.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -7912920711069387536L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    protected Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
