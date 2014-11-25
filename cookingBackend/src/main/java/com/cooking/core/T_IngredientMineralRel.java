package com.cooking.core;

import javax.persistence.*;

/**
 * Created by Chris on 25.11.2014.
 */

@Entity
@Table(name = "T_IngredientsMineralsRel")
public class T_IngredientMineralRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ingredientname", nullable = false)
    String ingredientname;

    @Column(name = "mineralname", nullable = false)
    String mineralname;

    @Column(name = "inMg", nullable = false)
    Integer inMg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public String getMineralname() {
        return mineralname;
    }

    public void setMineralname(String mineralname) {
        this.mineralname = mineralname;
    }

    public Integer getInMg() {
        return inMg;
    }

    public void setInMg(Integer inMg) {
        this.inMg = inMg;
    }
}
