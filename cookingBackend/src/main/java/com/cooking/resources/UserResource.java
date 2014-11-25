package com.cooking.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooking.core.DAOUser;
import com.cooking.core.T_User;
import com.cooking.helpFunction.HashPassword;
import com.cooking.helpFunction.StringToJson;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Created by Chris on 03.11.2014.
 */

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource extends HttpServlet {

    private final DAOUser daoUser;
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    public UserResource(DAOUser DAOUser){
        this.daoUser = DAOUser;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<T_User> listUsers() {
        return daoUser.findAll();
    }

    @GET
    @Path("/test")
    @UnitOfWork
    public List<T_User> findUser(){
        return daoUser.findByAttribute("lastname", "");
    }

    @POST
    @Path("/save")
    @UnitOfWork
    public Response saveUser(T_User TUser) throws Exception{
        logger.info("SignUp User!");
        return daoUser.saveUser(TUser);
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    public Response deleteUser(String username){
        StringToJson user = new StringToJson(username);
        username = user.getJson().get("username");
        logger.info("Deleting User: " + username);
        this.daoUser.deleteUser(username);
        return Response.status(200).build();
    }

    @POST
    @Path("/login")
    @UnitOfWork
    public Response loginUser(@Context HttpServletRequest request, String user) throws Exception{
        logger.info("LoggingIn");
        StringToJson userJson = new StringToJson(user);
        String username = userJson.getJson().get("username");
        String password = userJson.getJson().get("password");
        List<T_User> userDB = this.daoUser.findByName(username);
        if(userDB.isEmpty())
            return Response.status(404).build();
        else if(new HashPassword().validatePassword(password, userDB.get(0).getPassword())){
            return Response.status(200).entity(userDB.get(0)).build();
        }
        else
            return Response.status(403).build();
    }
}
