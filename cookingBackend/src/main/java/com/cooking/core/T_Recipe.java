package com.cooking.core;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chris on 24.11.2014.
 */

@Entity
@Table(name="T_Recipes")
public class T_Recipe implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;
    
    @Column(name="f_user_id")
    private long f_user_id;

    public long getF_user_id() {
        return f_user_id;
    }

    public void setF_user_id(long f_user_id) {
        this.f_user_id = f_user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipename() {
        return name;
    }

    public void setRecipename(String recipename) {
        this.name = recipename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
