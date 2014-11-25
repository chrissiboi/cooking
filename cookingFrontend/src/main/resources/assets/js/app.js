'use strict';

var cooking = angular.module('cooking', ['ngRoute', 'userProfile', 'sendForm', 'ngResource', 'ngSanitize']);

cooking.controller('ShowUserCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.users = {};

    $scope.initIndexes = function () {
        $http.get('/users').success(function (data) {
            $scope.users = data;
        });
    };
    $scope.deleteUser = function(userToDelete) {
        $http({
            method: 'POST',
            url: 'users/delete',
            data: userToDelete,
            header: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            $http.get('/users').success(function(data){
                $scope.users = data;
            });
        }).error(function (data, status, headers, config) {
            console.log("Shit, something went wrong!");
        });
    };
}]);

cooking.config(function($routeProvider) {
    $routeProvider.when('/home', {templateUrl: '/assets/partials/home.jsp'});
    $routeProvider.when('/info', {templateUrl: '/assets/partials/info.jsp', controller: 'LoginCtrl'});
    $routeProvider.when('/regestry', {templateUrl: '/assets/partials/regestry.jsp', controller: 'SignUpFormCtrl'});
    $routeProvider.when('/profile', {templateUrl: '/assets/partials/profile.jsp', controller: 'PCtrl'});
    $routeProvider.when('/tests', {templateUrl: '/assets/partials/test.jsp'});
    $routeProvider.when('/dashboard', {templateUrl: '/assets/partials/dashboard.jsp', controller: 'ShowUserCtrl'});
    $routeProvider.otherwise({redirectTo: '/home'});
});
