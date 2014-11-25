'user-strict'

var userProfile = angular.module('userProfile', []);

userProfile.factory('RecipeSaveResources', function($resource){
    return $resource('/api/recipe/save', {}, {});
});

userProfile.controller('PCtrl', ['RecipeSaveResources', '$scope', 'RecipeSaveResources', function($scope){
    $scope.recipe = {};

    $scope.saveRecipe = function(){
        RecipeSaveResources.save($scope.recipe).$promise.then(function(success){
                console.log("abjesichert!");
            },
            function(error){
                console.log(error);
            });
    };

}]);
