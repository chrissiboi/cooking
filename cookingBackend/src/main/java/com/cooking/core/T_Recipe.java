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
