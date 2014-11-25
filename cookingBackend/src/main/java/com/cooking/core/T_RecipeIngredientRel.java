package com.cooking.core;

import javax.persistence.*;

/**
 * Created by Chris on 25.11.2014.
 */
@Entity
@Table(name = "T_RecipesIngredientsRel")
public class T_RecipeIngredientRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "recipeId", nullable = false)
    Long recipeId;

    @Column(name = "ingredientname", nullable = false)
    String ingredientname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String name) {
        this.ingredientname = name;
    }
}
