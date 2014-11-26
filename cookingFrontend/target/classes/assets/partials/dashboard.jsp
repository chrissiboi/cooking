

<%
    Long priv = (Long) session.getAttribute("privileges");
    if(priv != null && priv == 1){
%>

<p>Underneath a list of indexes:</p>
<table ng-init="initIndexes()" class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Username</th>
            <th>Firstname</th>
            <th>LastName</th>
            <th>Email</th>
            <th>delete</th>
        </tr>
    </thead>
    <tbody>
    <tr ng-repeat="user in users | orderBy: 'ID'">
        <td>{{user.username}}</td>
        <td>{{user.firstname}}</td>
        <td>{{user.lastname}}</td>
        <td>{{user.email}}</td>
        <td><button class="btn-xs btn-danger" ng-click="deleteUser(user)">x</button></td>
    </tr>
    </tbody>
</table>


<ul>
    <li ng-repeat="index in indexes">
        {{index.firstname + index.lastname}}
        <button value="delete" class="btn-xs btn-danger">delete</button>
    </li>
</ul>
<%
}
else{
    out.print("<script>"
                + "window.location.href = '/'"
            + "</script>");
}
%>