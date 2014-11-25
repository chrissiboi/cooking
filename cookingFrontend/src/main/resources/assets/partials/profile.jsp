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
<div class="col-md-6">
    <h2>Welcome to your Profilepage</h2>
    <form method="post" ng-submit="saveRecipe">
        <div class="form-group">
            <label>Title</label>
            <input type="text" class="form-control" ng-model="recipe.title">
        </div>
        <div class="form-group">
            <label>Description</label>
            <textarea ng-model="recipe.description" class="form-control" cols="10" rows="20">
            </textarea>
        </div>
        <button type="submit" class="btn">Save Recipe</button>
    </form>
    {{recipe}}
</div>
<%
    }
%>