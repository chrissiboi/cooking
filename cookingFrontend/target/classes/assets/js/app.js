'use strict';

var cooking = angular.module('cooking', ['ngRoute', 'userProfile', 'sendForm', 'ngResource', 'ngSanitize']);

cooking.directive('fileUpload', function () {
    return {
        scope: true,        //create a new scope
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
                var files = event.target.files;
                //iterate files since 'multiple' may be specified on the element
                for (var i = 0;i<files.length;i++) {
                    //emit event upward
                    scope.$emit("fileSelected", { file: files[i] });
                }                                       
            });
        }
    };
});

cooking.controller('Ctrl', ['$scope', '$http', 'Resources', function($scope, $http, res){
    $scope.model = {
        name: "test", 
        description: "supertest"
    };

    $scope.files = [];
    
    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {            
            //add the file object to the scope's files collection
            $scope.files.push(args.file);
        });
    });
    
    $scope.save = function(){
        var testData = {model: $scope.model, files: $scope.files};
        console.log("saving data");
        res.saveUserRecipe().sendPictureAndData(testData).$promise.then(function(data){
            console.log("worked");
            console.log(data);
        }, function(error){
            console.log(error);
        });
    };
}]);
    
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
            return $resource('/api/recipe/save', {}, {
                sendPictureAndData: {method: 'post', headers: {'Content-Type': undefined}, isArray: false, 
                    transformRequest: function(data){
                        var formData = new FormData();
                        formData.append("model", angular.toJson(data.model));
                        for(var i = 0; i < data.files.length; i++){
                            formData.append("file", data.files[i]);
                        }
                        return formData;
                    }
                }
            });
        }
    };
});

cooking.controller('ShowUserCtrl', ['$scope', '$http', 'Resources', function ($scope, res) {
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
    $routeProvider.when('/uploadRecipe', {templateUrl: '/assets/partials/userUploadRecipeForm.jsp', controller: 'RecipeCtrl'});
    $routeProvider.when('/tests', {templateUrl: '/assets/partials/test.jsp', controller: 'Ctrl'});
    $routeProvider.when('/dashboard', {templateUrl: '/assets/partials/dashboard.jsp', controller: 'ShowUserCtrl'});
    $routeProvider.otherwise({redirectTo: '/home'});
});
