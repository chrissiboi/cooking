'use-strict'

var sendForm = angular.module('sendForm', []);

sendForm.factory('UserSaveResources', function($resource){
    return $resource('/api/users/save', {}, {});
});

sendForm.factory('UserLoginResources', function($resource){
   return $resource('/api/users/login', {}, {});
});

sendForm.controller('SignUpFormCtrl', ['$scope', 'UserSaveResources', '$location',
    function($scope, UserSaveResources){
        $scope.error = "";
        $scope.formData = {};
        $scope.registrationResult = '';
        $scope.backToHome = false;

        $scope.redirectToHome = function(){
            if($scope.backToHome)
                window.location.href = '/';
        }
        $scope.sending = function() {
            UserSaveResources.save($scope.formData).$promise.then(function(data){
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

sendForm.controller('LoginCtrl', ['$scope', 'UserLoginResources', '$location', '$route', '$http', '$sce',
    function($scope, UserLoginResources, $location, $route, $http){
        $scope.formData = {};
        $scope.errorMsg = "";
        $scope.logOut = function(){
            $http.post("/logout").success(function(){
                console.log("logout function");
                window.location.href = '/';
            }).error(function(data){

            });
        };
        $scope.login = function(){
            UserLoginResources.save($scope.formData).$promise.then(function(data){
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

