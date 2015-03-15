'use strict';
angular.module('todolistApp')
    .controller('LoginCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'AuthenticateService',
        function ($scope, $rootScope, $location, $cookieStore, AuthenticateService) {

        $scope.user = {}
        $scope.rememberMe = false
        $scope.activePage = 'login'

        $scope.login = function() {
            AuthenticateService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
                if (authenticationResult.status === 0) {
                    var authToken = authenticationResult.message;
                    $rootScope.authToken = authToken;
                    if ($scope.rememberMe) {
                        $cookieStore.put('authToken', authToken);
                    }
                    $rootScope.user = authenticationResult.data;
                    $location.path("/");
                }
            });
        };

        if ($rootScope.authToken === null) {
            $location.path("/login");
        }
}]);