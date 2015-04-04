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
                save: { method: 'POST', params: {}}
            }).save({ }, news)
        }
    }

    angular.module('todolistApp').
        service('NewsService', ['$resource', NewsService]);
})()