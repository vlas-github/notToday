(function () {

    'use strict';

    var LoginCtrl = function ($scope, $rootScope, $location, $cookieStore, AuthenticateService, UserService, Locale) {
        $scope.user = {}
        $scope.rememberMe = false
        $scope.activePage = 'login'

        $scope.login = function () {
            AuthenticateService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
                if (authenticationResult.status === "OK") {
                    var authToken = authenticationResult.message
                    $rootScope.authToken = authToken
                    if ($scope.rememberMe) {
                        $cookieStore.put('authToken', authToken)
                    }
                    $rootScope.user = authenticationResult.data
                    if ($rootScope.user && $rootScope.user.locality) {
                        Locale.set($rootScope.user.locality)
                    }
                    $scope.invalid = false
                    $location.path("/")
                } else {
                    $scope.username = ""
                    $scope.password = ""
                    $scope.invalid = true
                }
            })
        }

        $scope.registration = function () {
            UserService.create($scope.user).$promise.then(function (response) {
                if (response.status === "OK") {
                    $location.path("/login")
                }
            })
        }

        $scope.logout = function () {
            $rootScope.logout()
        }

        if ($rootScope.authToken === null) {
            $location.path("/login")
        }

        $scope.validateEmail = function () {
            var result = true

            if (!$scope.user.email || $scope.user.email.length === 0) {
                $scope.validateEmailError = true
                result = false
            } else {
                $scope.validateEmailError = false
            }

            if ($scope.user.email && $scope.user.email.length > 0 && !$scope._validateEmail($scope.user.email)) {
                $scope.validateEmailFormatError = true
                result = false
            } else {
                $scope.validateEmailFormatError = false
            }

            return result
        }

        $scope.validateName = function () {
            var result = true
            if (!$scope.user.name || $scope.user.name.length === 0) {
                $scope.validateNameError = true
                result = false
            } else {
                $scope.validateNameError = false
            }
            return result
        }

        $scope.validatePassword = function () {
            var result = true
            if (!$scope.user.password || $scope.user.password.length === 0) {
                $scope.validatePasswordError = true
                result = false
            } else {
                $scope.validatePasswordError = false
            }
            return result
        }

        $scope.validate = function () {
            return $scope.validateEmail() & $scope.validateName() & $scope.validatePassword()
        }

        $scope._validateEmail = function (email) {
            var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            return re.test(email);
        }
    }

    angular.module('todolistApp')
        .controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'AuthenticateService', 'UserService', 'Locale', LoginCtrl])
})()