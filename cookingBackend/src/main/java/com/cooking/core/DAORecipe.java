package com.cooking.core;

import com.cooking.resources.UserResource;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chris on 24.11.2014.
 */
public class DAORecipe extends AbstractDAO<T_Recipe> implements TableEntity<T_Recipe>{


    private static final Logger logger = LoggerFactory.getLogger(DAORecipe.class);
    private String className;
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public DAORecipe(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.className = T_Recipe.class.getName();
    }


    public List<T_Recipe> findAll() {
        return super.currentSession().createQuery("from " + this.className).list();
    }

    public void test(){
        Query query = currentSession().createQuery("select name from " + this.className + " where id= 1");
        System.out.println(super.uniqueResult(query));
    }

    @Override
    public T_Recipe findById(Long id) {
        return get(id);
    }

    @Override
    public List<T_Recipe> findByAttribute(String attributename, Object attributeValue) {
        Query query = currentSession().createQuery("from " + this.className + " where " + attributename + "= :value");
        query.setParameter("value", attributeValue);
        return query.list();
    }

    @Override
    public Response save(T_Recipe recipe) {
        persist(recipe);
        logger.info("Recipe saved");
        return Response.status(200).build();
    }

    @Override
    public Response delete(T_Recipe t_recipe) {
        Long id = t_recipe.getId();
        Integer delete = currentSession().createQuery("delete " + T_Recipe.class.getName() + " where id= :recipeId")
                .setParameter("recipeId", id)
                .executeUpdate();
        return Response.status(200).build();
    }
}
