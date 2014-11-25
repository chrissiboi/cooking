package com.cooking.core;

import javax.persistence.*;

/**
 * Created by Chris on 25.11.2014.
 */
@Entity
@Table(name = "T_Ingredients")
public class T_Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "description", nullable = true)
    String description;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="Ingredientpicture", nullable = true)
    byte[] picture;
}
