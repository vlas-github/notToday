(function () {
    'use strict';

    var modal = function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                ($parse(attrs.modal).assign)(scope, element);
            }
        };
    }

    angular.module('todolistApp').
        directive('modal', ['$parse', modal]);

})();