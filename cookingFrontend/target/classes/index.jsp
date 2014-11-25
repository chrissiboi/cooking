<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html>

    <html lang="en" ng-app="cooking">
    <head>
        <meta charset="utf-8" />
        <title>DWAS</title>
        <link href="/assets/style/main.css" rel="stylesheet">
        <link href="/assets/style/bootstrap.min.css" rel="stylesheet">
        <link href="/assets/style/mfglabs_iconset.css" rel="stylesheet">

    </head>
    <body>

    <header>
        <nav class="navbar navbar-inverse navbar-fixed-top row " role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a href="#/home">Home</a>
                    <a href="#/tests"> TESTS</a>
                </div>
                <%
                   String loginForm = "<form method=\"post\" novalidate class=\"navbar-form navbar-right\" ng-submit=\"login()\">\n" +
                           "                        <input type=\"text\" placeholder=\"username\" name=\"username\" ng-model=\"formData.username\" required>\n" +
                           "                        <input type=\"password\" placeholder=\"password\" name=\"password\" ng-model=\"formData.password\" required>\n" +
                           "                        <button class=\"btn-xs\"><a href=\"#/regestry\">Sign Up</a></button>\n" +
                           "                        <button type=\"submit\" class=\"btn btn-success btn-xs\">Login</button>\n" +
                           "                    </form>" ;

                    String logout = "  <form method='post' ng-submit='logOut()'>\" " +
                            " <a href=\"#/info\"> Info</a>" +
                            "   <a href=\"#/profile\">Profile</a>" +
                            "        <div class=\"navbar-right\">\n" +
                            "            <button class=\"btn-xs\" type='submit'>LogOut</button>\n" +
                            "        </div>" +
                            "   </form>";
                    String output;
                    String userName = null;
                    Cookie[] cookies = request.getCookies();
                    if(cookies !=null){
                        for(Cookie cookie : cookies){
                            if(cookie.getName().equals("user")) {
                                userName = cookie.getValue();
                            }
                        }
                    }
                    if(session.getAttribute("user") != null)
                        output = logout;
                    else
                        output = loginForm;
                %>
                <div id="navbar" class="collapse navbar-collapse" ng-controller="LoginCtrl">
                    <%=output%>
                </div>
            </div>
        </nav>
    </header>

    <div class="container spaceToNavbar" ng-view>

    </div>
    <script src="/assets/js/libs/jquery-2.1.1.min.js"></script>
    <script src="/assets/js/libs/angular-1.2.16.min.js"></script>
    <script src="/assets/js/libs/angular-route-1.2.16.min.js"></script>
    <script src="/assets/js/libs/angular-resource-1.1.5.js"></script>
    <script src="/assets/js/libs/angular-resource-1.2.0rc1.js"></script>
    <script src="/assets/js/libs/bootstrap.min.js"></script>
    <script src="/assets/js/userProfile.js"></script>
    <script src="/assets/js/sendForm.js"></script>
    <script src="/assets/js/app.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.3/angular-sanitize.min.js"></script>
    </body>
    </html>