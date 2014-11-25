package com.cooking.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Chris on 25.11.2014.
 */
@Entity
@Table(name = "T_Minerals")
public class T_Mineral {

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    //in mg
    @Column(name = "dose", nullable = false)
    Integer dose;

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
