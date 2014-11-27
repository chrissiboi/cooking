'use-strict';

var userProfile = angular.module('userProfile', []);

userProfile.controller('RecipeCtrl', ['$scope', 'Resources', function($scope, res){
        
        $scope.recipes = {};
        $scope.recipe = {};
        $scope.file;
        
        $scope.getUserRecipes = function(){
            console.log("Fetching User Recipes");
            res.getUserRecipes().query().$promise.then(function(data){
              console.log(data);
              $scope.recipes = data;
            },function(error){
                
            });
        };
        
        $scope.setFile = function(element){
            $scope.$apply(function(scope){
                console.log('files: ', element.files);
                $scope.recipe.picture = element.files[0].name;
                console.log($scope.recipe.picture);
                $scope.progressVisible = false;
            });
        };
        
        $scope.uploadFile = function(){
           var fd = $scope.file;
           for(var i in $scope.files){
               fd.append('uploadedFile', scope.files[i]);
           }
           var xhr = new XMLHttpRequest();
           xhr.upload.addEventListener("progress", uploadProgress, false);
           xhr.upload.addEventListener("load", uploadComplete, false);
           xhr.upload.addEventListener("error", uploadFail, false);
           xhr.upload.addEventListener("abort", uploadCanceled, false);
           $scope.progressVisible = true;
           xhr.send(fd);
        };
        
        function uploadProgress(evt) {
            scope.$apply(function(){
                if (evt.lengthComputable) {
                    scope.progress = Math.round(evt.loaded * 100 / evt.total)
                } else {
                    scope.progress = 'unable to compute';
                }
            });
        }

        function uploadComplete(evt) {
            /* This event is raised when the server send back a response */
            alert(evt.target.responseText);
        }

        function uploadFailed(evt) {
            alert("There was an error attempting to upload the file.");
        }

        function uploadCanceled(evt) {
            scope.$apply(function(){
                scope.progressVisible = false;
            });
            alert("The upload has been canceled by the user or the browser dropped the connection.");
        }
        
        $scope.saveRecipe = function(){
            console.log("uploading Recipe!");
            console.log('recipe: ', $scope.recipe);
            res.saveUserRecipe().save($scope.recipe).$promise.then(function(data){
                console.log("uploaded Data!");
                console.log(data);
            }, function(error){
                console.log("some errors: " + error);
            });
        };
}]);
