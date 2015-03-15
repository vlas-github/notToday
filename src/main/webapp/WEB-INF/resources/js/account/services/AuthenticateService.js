(function () {

    'use strict';

    var AuthenticateService = function($resource, $rootScope) {
        return $resource('/:action', {}, {
                authenticate: {
                    method: 'POST',
                    params: {'action': 'authenticate'},
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }
        )
    }

    angular.module('todolistApp')
        .service('AuthenticateService', ['$resource', '$rootScope', AuthenticateService])
})()