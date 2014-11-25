package com.cooking.core;

import javax.persistence.*;

/**
 * Created by Chris on 25.11.2014.
 */
@Entity
@Table(name = "T_IngredientsVitaminsRel")
public class T_IngredientVitaminRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ingredientname", nullable = false)
    String ingredientname;

    @Column(name = "vitaminname", nullable = false)
    String vitaminname;

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

    public String getVitaminname() {
        return vitaminname;
    }

    public void setVitaminname(String vitaminname) {
        this.vitaminname = vitaminname;
    }

    public Integer getInMg() {
        return inMg;
    }

    public void setInMg(Integer inMg) {
        this.inMg = inMg;
    }
}
