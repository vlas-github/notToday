(function () {

    'use strict';

    var AdvertService = function ($resource) {

        this.list = function () {
            return $resource('api/news/list.json', {}, {
                get: { method: 'GET' }
            }).get();
        }
    }

    angular.module('todolistApp').
        service('AdvertService', ['$resource', AdvertService]);
})()