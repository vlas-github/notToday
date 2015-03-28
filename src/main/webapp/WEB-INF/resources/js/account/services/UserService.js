'use strict';

angular.module('todolistApp')
    .service('UserService', ['$resource', '$rootScope', function ($resource, $rootScope) {

        this.get = function () {
            var user = $rootScope.user
            if (user && user.id) {
                return $resource('api/user/:id.json').get({id: user.id})
            }
        }

        this.update = function (user) {
            return $resource('api/user/:id.json', {} , {
                update: {method: 'PUT', params: {id: '@id'}}
            }).update({
                id: user.id
            }, user)
        }

        this.create = function(user) {
            return $resource('api/user.json', {} , {
                create: {method: 'POST'}
            }).create({}, user)
        }
}])
