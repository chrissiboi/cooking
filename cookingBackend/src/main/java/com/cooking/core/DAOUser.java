package com.cooking.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Chris on 03.11.2014.
 */

public class DAOUser extends AbstractDAO<T_User> {

    public DAOUser(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public T_User findById(Long id){
        return get(id);
    }

    public List<T_User> findAll(){
        return super.currentSession().createQuery("from " + T_User.class.getName()).list();
    }

    public List<T_User> findByEmail(String email){
        Query query = currentSession().createQuery("from " + T_User.class.getName() + " where email= :email");
        query.setParameter("email", email);
        return query.list();
    }

    public List<T_User> findByName(String username) {
        Query query = currentSession().createQuery("from " + T_User.class.getName() + " where username= :username");
        query.setParameter("username", username);
        return query.list();
    }

    public List<T_User> findByAttribute (String attribute, Object attributevalue){
        Query query = currentSession().createQuery("from " + T_User.class.getName() + " where " + attribute + "= :value");
        query.setParameter("value", attributevalue);
        return query.list();
    }

    public Response saveUser(T_User user) throws Exception{
        if(!userExists(user.getUsername(), user.getEmail())) {
            user.setPrivileges(0);
            persist(user);
            return Response.status(200).build();
        }
        else
            return Response.status(403).build();
    }

    public void deleteUser(String username){
        Query query = currentSession().createQuery("delete " + T_User.class.getName() + " where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
    }

    public boolean userExists(String name){
        return !(findByName(name).isEmpty());
    }

    public boolean userExists(String name, String email){
        return !(findByName(name).isEmpty());
    }
}


