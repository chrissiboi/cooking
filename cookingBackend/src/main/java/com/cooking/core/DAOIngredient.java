package com.cooking.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Chris on 25.11.2014.
 */
public class DAOIngredient extends AbstractDAO<T_Ingredient>{


    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public DAOIngredient(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<T_Ingredient> findAll(){
        return super.currentSession().createQuery("from " + T_Ingredient.class.getName()).list();
    }

    public T_Ingredient findByName(String name){
        List<T_Ingredient> list = super.currentSession().createQuery("from " + T_Ingredient.class.getName() + "where ingredientname = :name").list();
        return list.get(0);
    }
    public Response saveIngredient(T_Ingredient ingredient){
        persist(ingredient);
        //TO DO catch errors
        return Response.status(200).build();
    }

    public Response deleteIngredient(String name){
        Query query = currentSession().createQuery("delete T_Ingredients where ingredientname= :name");
        query.setParameter("name", name);
        query.executeUpdate();
        return Response.status(200).build();
    }
}
