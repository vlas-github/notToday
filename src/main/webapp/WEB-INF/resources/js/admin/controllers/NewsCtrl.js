(function () {
    'use strict';

    var NewsCtrl = function ($scope, $rootScope, UserService, NewsService) {
        $scope.activePage = 'admin'
        $scope.news = []

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

        NewsService.list().$promise.then(function (response) {
            if (response.status === "OK") {
                $scope.news = response.data
            }
        })
    }

    angular.module('todolistApp')
        .controller('NewsCtrl', ['$scope', '$rootScope', 'UserService', 'NewsService', NewsCtrl])
})()