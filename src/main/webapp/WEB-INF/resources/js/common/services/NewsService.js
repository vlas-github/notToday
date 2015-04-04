(function () {

    'use strict';

    var NewsService = function ($resource) {

        this.list = function () {
            return $resource('api/news/list.json', {}, {
                get: { method: 'GET' }
            }).get();
        }
    }

    angular.module('todolistApp').
        service('NewsService', ['$resource', NewsService]);
})()