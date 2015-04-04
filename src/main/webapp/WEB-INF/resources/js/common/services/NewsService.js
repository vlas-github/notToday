(function () {

    'use strict';

    var NewsService = function ($resource) {

        this.list = function () {
            return $resource('api/news/list.json', {}, {
                get: { method: 'GET' }
            }).get();
        }

        this.add = function (news) {
            return $resource('api/admin/news.json', {}, {
                add: { method: 'POST', params: {}}
            }).add({ }, news)
        }

        this.update = function (news) {
            return $resource('api/admin/news.json', {}, {
                update: { method: 'PUT', params: {}}
            }).update({ }, news)
        }
    }

    angular.module('todolistApp').
        service('NewsService', ['$resource', NewsService]);
})()