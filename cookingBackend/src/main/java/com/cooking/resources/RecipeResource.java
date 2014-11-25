package com.cooking.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooking.core.DAORecipe;
import com.cooking.core.T_Recipe;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Chris on 24.11.2014.
 */

@Path("/recipe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource extends HttpServlet{

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
    private final DAORecipe daorecipe;

    public RecipeResource(DAORecipe daoRecipe){
        this.daorecipe = daoRecipe;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<T_Recipe> listRecipes(){
        logger.info("Get all recipes!");
        return daorecipe.findAll();
    }
    
    @GET
    @Path("/{recipeId}")
    @Timed
    @UnitOfWork
    public T_Recipe getRecipe(@PathParam("recipeId") Long id){
        return daorecipe.findById(id);
    }
    
    @POST
    @Path("/save")
    @UnitOfWork
    public Response saveRecipe(T_Recipe TRecipe) throws Exception{
        logger.info("Save recipe!");
        daorecipe.save(TRecipe);
        return Response.status(200).build();
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    public Response deleteRecipe(T_Recipe recipe){
        //StringToJson user = new StringToJson(recipeId);
        //recipename = user.getJson().get("username");
        logger.info("Deleting Recipe: " + recipe);
        this.daorecipe.delete(recipe);
        return Response.status(200).build();
    }
    
}
