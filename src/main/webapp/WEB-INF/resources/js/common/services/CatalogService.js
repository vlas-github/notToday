(function () {

    'use strict';

    var CatalogService = function ($resource) {
        this.list = function (name) {
            return $resource('api/catalog/:name/list.json', {}, {
                get: { method: 'GET', cache: true, params: {  }}
            }).get({
                name: name
            });
        }
    }

    angular.module('todolistApp').
        service('CatalogService', ['$resource', CatalogService]);
})()