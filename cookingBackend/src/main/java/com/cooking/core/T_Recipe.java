package com.cooking.core;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.persistence.*;
import java.io.Serializable;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chris on 24.11.2014.
 */

@Entity
@Table(name="T_Recipes")
public class T_Recipe implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(T_Recipe.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;
    
    @Column(name="picture", nullable = true, columnDefinition="mediumblob")
    private byte[] picture;
    
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

    public String getName() {
        return name;
    }

    public void setName(String recipename) {
        this.name = recipename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture){
        this.picture = picture;
    }
}
