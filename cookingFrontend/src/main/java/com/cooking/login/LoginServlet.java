package com.cooking.login;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Chris on 13.11.2014.
 */

@WebServlet(value = "/LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        //get request parameters fpr userID and password
        StringBuffer sb = new StringBuffer();

        try{
            BufferedReader reader = request.getReader();
            String line;
            while((line = reader.readLine()) != null)
                sb.append(line);
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try{
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        JSONObject user = (JSONObject) joUser.get("user");
        String username = (String) user.get("username");
        Long privileges = (Long) user.get("privileges");
        Long userID     = (Long) user.get("id");
        HttpSession session = request.getSession();
        session.setAttribute("user", username);
        session.setAttribute("privileges", privileges);
        session.setAttribute("id", userID);
        //setting session to expiry in 30min
        session.setMaxInactiveInterval(30*60);
        Cookie userName = new Cookie("user", username);
        userName.setMaxAge(30 * 60);
        response.addCookie(userName);
        PrintWriter out = response.getWriter();
        out.print(user);
        return;
    }
}
