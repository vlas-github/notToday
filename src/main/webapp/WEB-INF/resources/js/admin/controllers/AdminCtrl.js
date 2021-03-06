(function () {
    'use strict';

    var AdminCtrl = function ($scope, $rootScope, UserService) {
        $scope.activePage = 'admin'

        if (!$rootScope.user || !$rootScope.user.id) {
            $location.path("/login");
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.user = response.data

                    $scope.user.tasks = []
                    $scope.user.newTasks = []
                    $scope.user.completedTasks = []
                    $scope.user.deletedTasks = []
                }
            })
        }
    }

    angular.module('todolistApp')
        .controller('AdminCtrl', ['$scope', '$rootScope', 'UserService', AdminCtrl])
})()