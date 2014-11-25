package com.cooking.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    @GET
    public HomeView goHome(@Context HttpServletRequest request) {

        javax.servlet.http.Cookie[] cookies = request.getCookies();
        System.out.println("asd");
        for(int i  = 0; i < cookies.length; i++){
            System.out.println(cookies[i].getName());
            if(cookies[i].getName().equals("user")) {
                return new HomeView("loggedInHome.ftl");
            }
        }
        return new HomeView();
    }
}
