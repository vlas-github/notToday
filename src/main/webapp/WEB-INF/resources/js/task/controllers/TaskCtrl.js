(function () {
    'use strict';

    var TaskCtrl = function($scope, $rootScope, $location, UserService, TaskService, CatalogService) {

        $scope.activePage = 'tasks'
        $scope.task = {}
        $scope.showNewTask = true

        if (!$rootScope.user || !$rootScope.user.id) {
            $location.path("/login")
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.user = response.data

                    $scope.getListTasks()
                }
            })
        }

        $scope.getListTasks = function () {
            TaskService.list($scope.user.id).$promise.then(function (response) {
                if (response.status === "OK") {
                    if (response.data && response.data.length > 0) {
                        $scope.newTasks = []
                        $scope.completedTasks = []
                        $scope.deletedTasks = []
                        response.data.forEach(function (t) {
                            var task = angular.copy(t)
                            if (task.deleted) {
                                $scope.deletedTasks.push(task)
                            } else if (task.completed) {
                                $scope.completedTasks.push(task)
                            } else {
                                $scope.newTasks.push(task)
                            }
                        })
                    }
                }
            })
        }

        $scope.showAddTaskModal = function() {
            $scope._task = {}
            CatalogService.list('repeat').$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.repeats = response.data
                }
            })
            $scope.openModal($scope.addTaskModal)
        }

        $scope.showEditTaskModal = function (t) {
            $scope._task = angular.copy(t)
            CatalogService.list('repeat').$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.repeats = response.data
                }
            })
            $scope.openModal($scope.editTaskModal)
        }

        $scope.showShowTaskModal = function (t) {
            $scope._task = angular.copy(t)
            $scope.openModal($scope.showTaskModal)
        }

        $scope.addTask = function () {
            TaskService.add($scope.user, $scope._task).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                    $scope._task = {}
                }
            })
        }

        $scope.editTask = function () {
            TaskService.update($scope.user, $scope._task).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                    $scope._task = {}
                }
            })
        }

        $scope.completeTask = function (t) {
            t.completed = true
            TaskService.update($scope.user, t).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                }
            })
        }

        $scope.deleteTask = function (t, isNewTask) {
            t.deleted = true
            TaskService.update($scope.user, t).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                }
            })

        }

        $scope.showDeleted = function () {
            $scope.showDeletedTask = !$scope.showDeletedTask
        }

        $scope.showNew = function () {
            $scope.showNewTask = !$scope.showNewTask
        }

        $scope.showCompleted = function () {
            $scope.showCompletedTask = !$scope.showCompletedTask
        }
    }

    angular.module('todolistApp')
        .controller('TaskCtrl', ['$scope', '$rootScope', '$location', 'UserService', 'TaskService', 'CatalogService', TaskCtrl])
})()