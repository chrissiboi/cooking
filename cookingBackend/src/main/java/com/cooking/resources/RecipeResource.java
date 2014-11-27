package com.cooking.resources;

import com.codahale.metrics.annotation.Timed;
import com.cooking.core.DAORecipe;
import com.cooking.core.T_Recipe;
import com.cooking.helpFunction.StringToJson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import io.dropwizard.hibernate.UnitOfWork;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;

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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRecipe(@FormDataParam("model") String test,
                               @FormDataParam("file") InputStream file,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception{
        
        byte[] bytes = IOUtils.toByteArray(file);
        T_Recipe recipe = new T_Recipe();
        recipe.setPicture(bytes);
        
        Map<String, String> json;
        json = new StringToJson(test).getJson();
        logger.info(test);
        recipe.setName(json.get("name"));
        recipe.setDescription(json.get("description"));
        recipe.setF_user_id(39);
        
        logger.info("File uploaded to");    
        daorecipe.save(recipe);
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

    private void writeToFile(InputStream file, String uploadedFileLocation) throws FileNotFoundException, IOException {
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        int read;
        byte[] bytes = new byte[1024];
        while((read = file.read(bytes)) != -1){
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }
    
}
