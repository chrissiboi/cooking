'use strict';

var cooking = angular.module('cooking', ['ngRoute', 'userProfile', 'sendForm', 'ngResource', 'ngSanitize']);

cooking.factory('Resources', function($resource){
    
    return {
        saveUser:   function(){
            return $resource('/api/users/save');
        },
        loginUser:  function(){
            return $resource('/api/users/login');
        },
        getUsers:   function(){
            return $resource('/api/users');
        },
        deleteUser: function(){
            return $resource('/api/users/delete');
        },
        getUserRecipes: function(){
            return $resource('/api/recipe/:userId', {userId: '@id'});
        },
        saveUserRecipe: function(){
            return $resource('/api/recipe/save');
        }
    };
});

cooking.controller('ShowUserCtrl', ['$scope', '$http', 'Resources', function ($scope, $http, res) {
    $scope.users = {};

    $scope.initIndexes = function () {
        res.getUsers().query().$promise.then(function(data){
            $scope.users = data;
        }, function(error){
            console.log(error);
        });
    };
    
    $scope.deleteUser = function(userToDelete) {
        res.deleteUser().save(userToDelete).$promise.then(function(success){
            res.getUsers().query().$promise.then(function(data){
               $scope.users = data; 
            },function(error){
                console.log(error);
            });
        },function(error){
            console.log(error);
        });
    };
}]);

cooking.config(function($routeProvider) {
    $routeProvider.when('/home', {templateUrl: '/assets/partials/home.jsp'});
    $routeProvider.when('/info', {templateUrl: '/assets/partials/info.jsp', controller: 'LoginCtrl'});
    $routeProvider.when('/regestry', {templateUrl: '/assets/partials/regestry.jsp', controller: 'SignUpFormCtrl'});
    $routeProvider.when('/profile', {templateUrl: '/assets/partials/profile.jsp', controller: 'RecipeCtrl'});
    $routeProvider.when('/tests', {templateUrl: '/assets/partials/test.jsp'});
    $routeProvider.when('/dashboard', {templateUrl: '/assets/partials/dashboard.jsp', controller: 'ShowUserCtrl'});
    $routeProvider.otherwise({redirectTo: '/home'});
});
