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
    }

angular.module('todolistApp')
    .service('TaskService', ['$resource', TaskService])
})()