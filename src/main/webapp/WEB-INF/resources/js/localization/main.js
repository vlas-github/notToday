(function () {
    'use strict';

    var localizationModuleRun = function ($filter) {
        var filter = $filter('i18n');
        $.extend($.fn.select2.defaults, {
            formatNoMatches: function () {
                return filter('select2.no.matches');
            },
            formatInputTooShort: function (input, min) {
                var n = min - input.length;
                return filter('select2.please.enter.another') + " " + n + " "
                    + filter('select2.symbols') + (n == 1 ? filter('select2.symbols.1')
                    : ((n > 1) && (n < 5) ? filter('select2.symbols.2')
                    : filter('select2.symbols.5')));
            },
            formatLoadMore: function (pageNumber) {
                return filter('select2.loading');
            },
            formatSearching: function () {
                return filter('select2.search');
            }
        });
    }

    angular.module('todolistApp').
        constant('localizedTexts', {}).
        run(['$filter',localizationModuleRun ]);

})();