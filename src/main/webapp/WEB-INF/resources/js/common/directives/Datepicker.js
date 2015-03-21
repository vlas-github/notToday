(function () {
    'use strict';

    var datepicker = function ($timeout, $parse, $log, locale) {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModelCtrl) {
                element.datepicker({
                    language: locale.get(),
                    date: attrs.value,
                    weekStart: 1,
                    autoclose: true,
                    format: 'dd.mm.yyyy'
                });
                if (attrs.ngModel) {
                    scope.$watch(attrs.ngModel, function (newValue, oldValue) {
                        if (typeof newValue === 'string') {
                            var dateParts = newValue.split(".");
                            if (dateParts.length === 3) {
                                var date = new Date(dateParts[2], (dateParts[1] - 1), dateParts[0]);
                                element.datepicker('setDate', date);
                                $parse(attrs.ngModel).assign(scope, date.getTime());
                            }
                        } else if (newValue) {
                            element.datepicker('setDate', new Date(newValue));
                        }
                    });
                }
            }
        };
    }

    angular.module('todolistApp').
        directive('datepicker', ['$timeout', '$parse', '$log', 'Locale', datepicker]);

})();