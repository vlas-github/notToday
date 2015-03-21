(function () {
    'use strict';

    var TaskService = function ($resource) {

        this.add = function (user, task) {
            return $resource('api/user/:user/task.json', {}, {
                save: { method: 'POST', params: {user: '@user'}}
            }).save({
                user: user.id
            }, task)
        }

        this.list = function (id) {
            return $resource('api/user/:id/task/list.json').get({id: id})
        }

        this.update = function(user, task) {
            return $resource('api/user/:user/task/:id.json', {}, {
                update: { method: 'PUT', params: {user: '@user', id: '@id'}}
            }).update({
                user: user.id,
                id: task.id
            }, task)
        }
    }

angular.module('todolistApp')
    .service('TaskService', ['$resource', TaskService])
})()