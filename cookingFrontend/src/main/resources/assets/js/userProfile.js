'user-strict';

var userProfile = angular.module('userProfile', []);

userProfile.controller('RecipeCtrl', ['Resources', '$scope', function($scope, res){
    
    
    $scope.test = function(){
        console.log("asd");
    };
}]);
