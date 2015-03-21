(function () {
    'use strict';

    var TaskCtrl = function($scope, $rootScope, UserService, TaskService, CatalogService) {

        $scope.activePage = 'tasks'
        $scope.task = {}
        $scope.newTasks = []
        $scope.completedTasks = []
        $scope.deletedTasks = []
        $scope.showNewTask = true

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
                response.data.forEach(function(t) {
                    var task = angular.copy(t)
                    task.user = {} // todo сделать преобразование json to pojo
                    if (task.deleted) {
                        $scope.deletedTasks.push(task)
                    } else if (task.completed) {
                        $scope.completedTasks.push(task)
                    } else {
                        $scope.newTasks.push(task)
                    }
                })
            }
        })

        $scope.showAddTaskModal = function() {
            CatalogService.list('repeat').$promise.then(function (response) {
                if (response.status === 0) {
                    $scope.repeats = response.data
                }
            })
            $scope.openModal($scope.addTaskModal);
        }

        $scope.addTodo = function () {
            TaskService.add($scope.user, $scope.task).$promise.then(function (response) {

                if (response.status === 0) {
                    var task = angular.copy(response.data)
                    task.user = {}
                    if (task.deleted) {
                        $scope.deletedTasks.push(task)
                    } else if (task.completed) {
                        $scope.completedTasks.push(task)
                    } else {
                        $scope.newTasks.push(task)
                    }
                    $scope.task = {}
                }
            })
        }

        $scope.editTask = function (t) {
            /*var tasks = $scope.completedTasks
            if (tasks.indexOf(t) == -1) {
                tasks = $scope.newTasks
            }
            if (tasks.indexOf(t) == -1) {
                tasks = $scope.deletedTasks
            }
            var index = tasks.indexOf(t)
            if (index > -1) {
                var task = tasks[index]
                TaskService.update($scope.user, task).$promise.then(function (response) {
                    if (response.status === 0) {
                        var _task = response.data
                        tasks.splice(index, 1)
                        $scope.tasks.push(_task)
                    }
                })
            }*/
        }

        $scope.completeTask = function (t) {
            var index = $scope.newTasks.indexOf(t)
            if (index > -1) {
                var task = $scope.newTasks[index]
                task.completed = true
                TaskService.update($scope.user, task).$promise.then(function (response) {
                    if (response.status === 0) {
                        var _task = response.data
                        $scope.newTasks.splice(index, 1)
                        $scope.completedTasks.push(_task)
                    }
                })
            }
        }

        $scope.deleteTask = function (t, isNewTask) {
            var tasks = $scope.completedTasks
            if (isNewTask) {
                tasks = $scope.newTasks
            }
            var index = tasks.indexOf(t)
            if (index > -1) {
                var task = tasks[index]
                task.deleted = true
                TaskService.update($scope.user, task).$promise.then(function (response) {
                    if (response.status === 0) {
                        var _task = response.data
                        tasks.splice(index, 1)
                        $scope.deletedTasks.push(_task)
                    }
                })
            }
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
        .controller('TaskCtrl', ['$scope', '$rootScope', 'UserService', 'TaskService', 'CatalogService', TaskCtrl])
})()