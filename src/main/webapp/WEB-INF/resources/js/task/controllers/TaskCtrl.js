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

        CatalogService.list('repeat').$promise.then(function (response) {
            if (response.status === "OK") {
                $scope.repeats = response.data
            }
        })

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
            $scope.validateTaskError = false;
            $scope._task = {}
            CatalogService.list('repeat').$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.repeats = response.data
                }
            })
            $scope.openModal($scope.addTaskModal)
        }

        $scope.showEditTaskModal = function (t) {
            $scope.validateTaskError = false;
            $scope._task = angular.copy(t)
            if ($scope._task.timeIsSet) {
                $scope._setTime = $scope._task.timeIsSet
                if (!$scope._task.executionDate) {
                    $scope._task.executionTime = new Date()
                }
            }
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

        $scope.addTask = function (cont) {
            var date = new Date()
            if ($scope._task.executionDate && $scope._task.executionDate.valueOf() > 0) {
                date = new Date($scope._task.executionDate.valueOf())
                $scope._task.dateIsSet = true;
            } else {
                $scope._task.dateIsSet = false;
            }

            if ($scope._setTime) {
                var time = new Date($scope._task.executionTime.valueOf())
                $scope._task.timeIsSet = true
                date.setHours(time.getHours())
                date.setMinutes(time.getMinutes())
                $scope._task.executionDate = date;
                delete $scope._task.executionTime
            } else {
                $scope._task.timeIsSet = false;
            }
            $scope._setTime = false
            TaskService.add($scope.user, $scope._task).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                    $scope._task = {}
                }
            })
            if (!cont) {
                $scope.closeModal($scope.addTaskModal)
            }
        }

        $scope.editTask = function () {
            var date = new Date()
            if ($scope._task.executionDate && $scope._task.executionDate.valueOf() > 0) {
                date = new Date($scope._task.executionDate.valueOf())
                $scope._task.dateIsSet = true;
            } else {
                $scope._task.dateIsSet = false;
            }

            if ($scope._setTime) {
                var time = new Date($scope._task.executionTime.valueOf())
                $scope._task.timeIsSet = true
                date.setHours(time.getHours())
                date.setMinutes(time.getMinutes())
                $scope._task.executionDate = date;
                delete $scope._task.executionTime
            } else {
                $scope._task.timeIsSet = false;
            }
            $scope._setTime = false
            TaskService.update($scope.user, $scope._task).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getListTasks()
                    $scope._task = {}
                }
            })
            $scope.closeModal($scope.editTaskModal)
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

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.opened = true;
        };

        $scope.validate = function() {
            if (!$scope._task.text || $scope._task.text.length === 0) {
                $scope.validateTaskError = true
                return false
            }
            $scope.validateTaskError = false
            return true
        }

        $scope.setTime = function () {
            if ($scope._setTime) {
                $scope._task.executionTime = new Date()
            }
        }
    }

    angular.module('todolistApp')
        .controller('TaskCtrl', ['$scope', '$rootScope', '$location', 'UserService', 'TaskService', 'CatalogService', TaskCtrl])
})()