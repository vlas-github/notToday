(function () {
    'use strict';

    var TaskCtrl = function($scope, $rootScope, UserService, TaskService, CatalogService) {

        $scope.activePage = 'tasks'
        $scope.task = {}

        if (!$rootScope.user || !$rootScope.user.id) {
            $location.path("/login");
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === 0) {
                    $scope.user = response.data
                }
            })
        }

        TaskService.list($scope.user.id).$promise.then(function (response) {
            if (response.status === 0) {
                $scope.todos = response.data
            }
        })

        $scope.showModal = function() {
            CatalogService.list('repeat').$promise.then(function (response) {
                if (response.status === 0) {
                    $scope.repeats = response.data
                }
            })
            $scope.openModal($scope.addTaskModal);
        }

        $scope.addTodo = function () {
            TaskService.add($scope.user, $scope.task).$promise.then(function (response) {
                // todo
            })
        }

        $scope.completeTodo = function (todo) {

        }

        $scope.deleteTodo = function (todo) {

        }
    }

    angular.module('todolistApp')
        .controller('TaskCtrl', ['$scope', '$rootScope', 'UserService', 'TaskService', 'CatalogService', TaskCtrl])
})()