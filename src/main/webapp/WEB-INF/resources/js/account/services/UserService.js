'use strict';

angular.module('todolistApp')
    .service('UserService', ['$resource', '$rootScope', function ($resource, $rootScope) {

        this.get = function() {
            var user = $rootScope.user;
            if (user && user.id) {
                return $resource('api/user/:id.json').get({id: user.id});
            }
        }
}])
