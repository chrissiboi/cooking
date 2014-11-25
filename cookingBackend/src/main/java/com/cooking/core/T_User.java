package com.cooking.core;

import com.cooking.helpFunction.HashPassword;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Chris on 30.10.2014.
 */
@Entity
@Table(name="T_Users")
public class T_User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name = "firstname", nullable = true)
    private String firstname;

    @Column(name = "lastname", nullable = true)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture", nullable = true)
    private byte[] picture;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = true)
    private Date birthday;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        if(firstname == null)
            this.firstname = "N/A";
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        if(lastname == null)
            this.lastname = "N/A";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPicture() {

        return new byte[0];
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
        if(picture == null)
            this.picture = new byte[0];
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        HashPassword hp = new HashPassword();
        String pw = hp.createHash(password);
        this.password = pw;
    }

    public Date getBirthday() {
        if (birthday == null)
            return new Date(1000);
        else
            return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        if(birthday == null)
            this.birthday = new Date(100);
    }
}
