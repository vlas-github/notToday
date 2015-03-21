(function () {
    'use strict';

    var locale = function (localizedTexts) {
        var l = null;
        return {
            get: function () {
                if (!l) {
                    l = navigator.language || navigator.userLanguage;
                    if (l && l.length > 2) l = l.substring(0, 2);
                    if (!localizedTexts.hasOwnProperty(l)) l = 'en';
                }
                return l;
            },
            set: function (newLocale) {
                l = newLocale;
            }
        };
    }

    angular.module('todolistApp').
        factory('Locale', [ 'localizedTexts', locale]);

})();