<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("user")) {
            userName = cookie.getValue();
        }
    }
}

if(userName == null){

%>
    <script>
        window.location.href = '/';
    </script>
<%
}
else{
%>
<h3>Hi <%=userName %>, Login successful.</h3>
<br>
<form name="logout" method="post" ng-submit="logOut()">
    <button type="submit">Logout</button>
</form>

<%
    }
%>