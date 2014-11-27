'use-strict';

var sendForm = angular.module('sendForm', []);

sendForm.controller('SignUpFormCtrl', ['$scope', 'Resources',
    function($scope, res){
        $scope.error = "";
        $scope.formData = {};
        $scope.registrationResult = '';
        $scope.backToHome = false;

        $scope.redirectToHome = function(){
            if($scope.backToHome)
                window.location.href = '/';
        };
        $scope.sending = function() {
            res.saveUser().save($scope.formData).$promise.then(function(data){
                    $scope.registrationResult = '<i class="icon-check iconColorGreen icon2x"></i> Your registration was successful!';
                    $scope.backToHome = true;
                },
                function (data) {
                    if(data.status === 403)
                        $scope.registrationResult = "Username or email already exists!";
                    else
                        $scope.registrationResult = "Unknown Error!";
                });
        };
    }]);

sendForm.controller('LoginCtrl', ['$scope', 'Resources', '$location', '$route', '$http', '$sce',
    function($scope, res, $location, $route, $http){
        $scope.formData = {};
        $scope.errorMsg = "";
        $scope.logOut = function(){
            $http.post("/logout").success(function(){
                console.log("logout function");
                window.location.href = '/';
            }).error(function(data){

            });
        };
        
        $scope.test = function(){
            console.log("test");
        };
        $scope.login = function(){
            res.loginUser().save($scope.formData).$promise.then(function(data){
                console.log(data);
                $http.post('/login', {user: data}).
                    success(function(userData){
                        window.location.href = '/';
                    }).error(function(data){
                        console.log(data);
                    });
            }, function(error){
                if(error.status !== 200)
                    $scope.errorMsg = "Username or Password wrong!";
            });
        };

    }]);

