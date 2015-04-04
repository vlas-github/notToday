(function () {
    'use strict';

    var AccountCtrl = function($scope, $rootScope, $location, UserService, TaskService, CatalogService, Locale) {
        $scope.activePage = 'account'
        
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
                    $scope._user = angular.copy($scope.user)

                    TaskService.list($scope.user.id).$promise.then(function (response) {
                        if (response.status === "OK") {
                            if (response.data && response.data.length > 0) {
                                response.data.forEach(function (t) {
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
                        }
                    })
                }
            })
        }

        $scope.saveProfile = function() {
            UserService.update($scope._user).$promise.then(function (response) {
                if (response.status === "OK") {
                    if (response.data && response.data.locality) {
                        Locale.set(response.data.locality)
                    }
                    $location.path('/account')
                }
            })
        }

        $scope.validate = function() {
            if (!$scope._user.name || $scope._user.name.length === 0) {
                $scope.validateNameError = true
                return false
            }
            $scope.validateNameError = false
            return true
        }
    }

    angular.module('todolistApp')
        .controller('AccountCtrl', ['$scope', '$rootScope', '$location', 'UserService', 'TaskService', 'CatalogService', 'Locale', AccountCtrl])
})()