<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>

<!DOCTYPE html>

<html lang="en" ng-app="cooking">
<head>
    <meta charset="utf-8" />
    <title>DWAS</title>
    <link href="/assets/style/main.css" rel="stylesheet">
    <link href="/assets/style/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<%
   String userName = "test";
%>

<header>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="col-md-8">
            <a href="#/dashboard">Home</a>
            <a href="#/info">Info</a>
            <a href="#/regestry">Sign Up</a>
        </div>
        <div class="col-md-4">
            <button class="btn-xs" ng-click="logOut()">LogOut</button>
        </div>
    </nav>
</header>
<div class="container spaceToNavbar" ng-view>
 <h3>Hi, <%=userName %>. Your session id = </h3>
</div>
<script src="/assets/js/libs/jquery-2.1.1.min.js"></script>
<script src="/assets/js/libs/angular-1.2.16.min.js"></script>
<script src="/assets/js/libs/angular-route-1.2.16.min.js"></script>
<script src="/assets/js/libs/bootstrap.min.js"></script>
<script src="/assets/js/app.js"></script>
</body>
</html>