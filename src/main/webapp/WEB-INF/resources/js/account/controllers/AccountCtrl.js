(function () {
    'use strict';

    var AccountCtrl = function($scope, $rootScope, $location, UserService, TaskService, CatalogService) {
        $scope.activePage = 'account'
        
        if (!$rootScope.user || !$rootScope.user.id) {
            $location.path("/login");
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === 0) {
                    $scope.user = response.data

                    $scope.user.tasks = []
                    $scope.user.newTasks = []
                    $scope.user.completedTasks = []
                    $scope.user.deletedTasks = []

                    TaskService.list($scope.user.id).$promise.then(function (response) {
                        if (response.status === 0) {
                            response.data.forEach(function(t) {
                                var task = angular.copy(t)
                                task.user = {}
                                if (task.deleted) {
                                    $scope.user.deletedTasks.push(task)
                                } else if (task.completed) {
                                    $scope.user.completedTasks.push(task)
                                } else {
                                    $scope.user.newTasks.push(task)
                                }
                                $scope.user.tasks.push(task)
                            })
                        }
                    })
                }
            })
        }
    }

    angular.module('todolistApp')
        .controller('AccountCtrl', ['$scope', '$rootScope', '$location', 'UserService', 'TaskService', 'CatalogService', AccountCtrl])
})()