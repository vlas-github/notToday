(function () {
    'use strict'

    var IndexCtrl = function ($scope, $rootScope, $location, $cookieStore, UserService, TaskService, NewsService, Locale) {
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

        NewsService.list().$promise.then(function (response) {
            if (response.status === "OK") {
                $scope.news = response.data
                var locale = Locale.get()
                if ($scope.news && $scope.news.length > 0) {
                    $scope.news.forEach(function (news) {
                        news.title = locale === "ru" ? news.russianText : news.englishText
                    })
                }
            }
        })
    }

    angular.module('todolistApp')
        .controller('IndexCtrl', ['$scope', '$rootScope', '$location', '$cookieStore', 'UserService', 'TaskService', 'NewsService', 'Locale', IndexCtrl])
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