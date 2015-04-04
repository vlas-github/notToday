(function () {

    'use strict';

    var NewsCtrl = function ($scope, $rootScope, UserService, NewsService, CatalogService) {

        $scope.activePage = 'admin'
        $scope.news = []
        $scope.types = []

        if (!$rootScope.user || !$rootScope.user.id || !$rootScope.hasRole("ROLE_ADMIN")) {
            $location.path("/login");
        } else {
            UserService.get().$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.user = response.data
                }
            })
        }

        $scope.getNews = function () {
            NewsService.list().$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.news = response.data
                }
            })
        }

        $scope.getNews()

        $scope.showAddNewsModal = function() {
            $scope._news = {}
            CatalogService.list('newsType').$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.types = response.data
                }
            })
            $scope.openModal($scope.addNewsModal)
        }

        $scope.showEditNewsModal = function(news) {
            $scope._news = angular.copy(news)
            CatalogService.list('newsType').$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.types = response.data
                }
            })
            $scope.openModal($scope.editNewsModal)
        }

        $scope.addNews = function (cont) {
            $scope._news.author = { id: $scope.user.id }
            NewsService.add($scope._news).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getNews()
                }
            })
            $scope._news = {}
            if (!cont) {
                $scope.closeModal($scope.addNewsModal)
            }
        }

        $scope.updateNews = function () {
            $scope._news.author = { id: $scope.user.id }
            NewsService.update($scope._news).$promise.then(function (response) {
                if (response.status === "OK") {
                    $scope.getNews()
                }
            })
            $scope._news = {}
            $scope.closeModal($scope.editNewsModal)
        }

        $scope.validateEnglishText = function () {
            if (!$scope._news.englishText || $scope._news.englishText.length === 0) {
                $scope.validateEnglishTextError = true
                return false
            } else {
                $scope.validateEnglishTextError = false
                return true
            }
        }

        $scope.validateRussianText = function () {
            if (!$scope._news.russianText || $scope._news.russianText.length === 0) {
                $scope.validateRussianTextError = true
                return false
            } else {
                $scope.validateRussianTextError = false
                return true
            }
        }

        $scope.validateType = function () {
            if (!$scope._news.type || $scope._news.type.length === 0) {
                $scope.validateTypeError = true
                return false
            } else {
                $scope.validateTypeError = false
                return true
            }
        }

        $scope.validate = function () {
            return $scope.validateEnglishText() & $scope.validateRussianText() & $scope.validateType()
        }
    }

    angular.module('todolistApp')
        .controller('NewsCtrl', ['$scope', '$rootScope', 'UserService', 'NewsService', 'CatalogService', NewsCtrl])
})()