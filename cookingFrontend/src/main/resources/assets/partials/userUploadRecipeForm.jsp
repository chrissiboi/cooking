<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
        }
    }
    Long userID = (Long) session.getAttribute("id");
    if(false /*userName == null*/){

%>
<script>
    window.location.href = '/';
</script>
<%
}
else{
%>
<div class="col-md-6">
    <form method="post" novalidate name="recipeForm" ng-submit="saveRecipe()">
        <div class="form-group">
            <lable>Title</lable>
            <input type="text" name="name" class='form-control' ng-model="recipe.title">
        </div>
        <div class="form-group">
            <textarea name="description" class='form-control' ng-model="recipe.description" rows="30">
                
            </textarea>
        </div>
        <div class='form-group'>
            <label>Select Pictures</label>
            <input type='file' onchange="angular.element(this).scope.setPictures()"
                   ng-model-instant name="pictureUpload">
        </div>
        <button value='save' type='submit' class="btn">Save Recipe</button>
    </form>
</div>
<div class="col-md-3">
    <h4>Your recipes</h4>
    <table ng-init="getUserRecipes()" class="table table-striped table-bordered">
        <tr ng-repeat="recipe in recipes">
            <td>{{recipe.name}}</td>
            <td>{{recipe.description}}</td>
        </tr>
    </table>
</div>
<%
    }
%>