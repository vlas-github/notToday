(function () {
    'use strict'

    var IndexCtrl = function ($scope, $rootScope, $location, $cookieStore, UserService, TaskService, AdvertService) {
        $scope.todos = []
        $scope.showModal = false
        $scope.authToken = $cookieStore.get('authToken')
        $scope.activePage = 'home'

        if (!$rootScope.user || !$rootScope.user.id) {
            //$location.path("/login"); todo try get user by cookie
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.user = response.data
                }
            })
        }

        AdvertService.list().$promise.then(function (response) {
            if (response.status === "OK") {
                $scope.adverts = response.data
            }
        })
    }

    angular.module('todolistApp')
        .controller('IndexCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'UserService', 'TaskService', 'AdvertService', IndexCtrl])
})()

function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0
        d = Math.floor(d/16)
        return (c=='x' ? r : (r&0x3|0x8)).toString(16)
    });
    return uuid
}