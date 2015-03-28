(function () {

    'use strict';

    var LoginCtrl = function ($scope, $rootScope, $location, $cookieStore, AuthenticateService) {
        $scope.user = {}
        $scope.rememberMe = false
        $scope.activePage = 'login'

        $scope.login = function() {
            AuthenticateService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
                if (authenticationResult.status === "OK") {
                    var authToken = authenticationResult.message
                    $rootScope.authToken = authToken
                    if ($scope.rememberMe) {
                        $cookieStore.put('authToken', authToken)
                    }
                    $rootScope.user = authenticationResult.data
                    $scope.invalid = false
                    $location.path("/")
                } else {
                    $scope.username = ""
                    $scope.password = ""
                    $scope.invalid = true
                }
            })
        }

        $scope.logout = function() {
            $cookieStore.remove('authToken')
            $rootScope.user = {}
        }

        if ($rootScope.authToken === null) {
            $location.path("/login")
        }
    }

    angular.module('todolistApp')
        .controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'AuthenticateService', LoginCtrl])
})()