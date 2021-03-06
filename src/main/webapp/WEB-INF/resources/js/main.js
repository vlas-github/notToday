(function () {
    'use strict';

    angular.module('todolistApp', [
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ui.bootstrap',
        'ui.bootstrap.datepicker',
        'ui.bootstrap.timepicker'
    ]).config(['$routeProvider', '$locationProvider', '$httpProvider',
        function ($routeProvider, $locationProvider, $httpProvider) {
            $routeProvider.when('/', {
                templateUrl: 'content/index/index.html',
                controller: 'IndexCtrl',
                activePage: 'home'
            })
            .when('/about', {
                templateUrl: 'content/about/about.html',
                controller: 'AboutCtrl',
                activePage: 'about'
            })
            .when('/about/api', {
                templateUrl: 'content/about/api.html',
                controller: 'ApiCtrl',
                activePage: 'api'
            })
            .when('/tasks', {
                templateUrl: 'content/task/index.html',
                controller: 'TaskCtrl',
                activePage: 'tasks'
            })
            .when('/account', {
                templateUrl: 'content/account/index.html',
                controller: 'AccountCtrl',
                activePage: 'account'
            })
            .when('/account/edit', {
                templateUrl: 'content/account/edit.html',
                controller: 'AccountCtrl',
                activePage: 'account'
            })
            .when('/login', {
                templateUrl: 'content/account/login.html',
                controller: 'LoginCtrl',
                activePage: 'login'
            })
            .when('/registration', {
                templateUrl: 'content/account/registration.html',
                controller: 'LoginCtrl',
                activePage: 'login'
            })
            .when('/logout', {
                templateUrl: 'content/account/logout.html',
                controller: 'LoginCtrl',
                activePage: 'login'
            })
            .when('/admin', {
                templateUrl: 'content/admin/index.html',
                controller: 'AdminCtrl',
                activePage: 'admin'
            })
            .when('/admin/news', {
                templateUrl: 'content/admin/news.html',
                controller: 'NewsCtrl',
                activePage: 'admin'
            })
            .otherwise({
                redirectTo: '/'
            });

            $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'responseError': function (rejection) {
                        var status = rejection.status;
                        var config = rejection.config;
                        var method = config.method;
                        var url = config.url;

                        if (status == 401) {
                            $location.path("/login");
                        } else {
                            $rootScope.error = method + " on " + url + " failed with status " + status;
                        }

                        return $q.reject(rejection);
                    }
                };
            });

            $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'request': function (config) {
                        if (angular.isDefined($rootScope.authToken)) {
                            var authToken = $rootScope.authToken;
                            if (appConfig.useAuthTokenHeader) {
                                config.headers['X-Auth-Token'] = authToken;
                            } else {
                                config.url = config.url + "?token=" + authToken;
                            }
                        }
                        return config || $q.when(config);
                    }
                };
            });

        }]).run(function ($rootScope, $location, $cookieStore, UserService) {

        $rootScope.$on('$viewContentLoaded', function () {
            delete $rootScope.error;
        });

        $rootScope.hasRole = function (role) {

            if ($rootScope.user === undefined) {
                return false
            }

            if (!$rootScope.user.authorities || $rootScope.user.authorities.length === 0) {
                return false
            }

            for (var i = 0; i < $rootScope.user.authorities.length; i++) {
                if ($rootScope.user.authorities[i].value === role) {
                    return true
                }
            }

            return false
        };

        $rootScope.logout = function () {
            delete $rootScope.user;
            delete $rootScope.authToken;
            $cookieStore.remove('authToken');
            $location.path("/logout");
        };

        var originalPath = $location.path();
        $location.path("/login");
        var authToken = $cookieStore.get('authToken');
        if (authToken !== undefined) {
            $rootScope.authToken = authToken;
            UserService.get(function (user) {
                $rootScope.user = user;
                $location.path(originalPath);
            });
        }

        $rootScope.openModal = function (modal) {
            modal.modal('show');
        }

        $rootScope.closeModal = function (modal) {
            modal.modal('hide');
        }

        $rootScope.initialized = true;
    });
})()