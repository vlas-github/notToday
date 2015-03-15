(function () {
    'use strict';

    var i18n = function ($sce, locale, localizedTexts) {
        return function (text, params) {
            var l = locale.get();
            if (localizedTexts[l].hasOwnProperty(text)) {
                var r = localizedTexts[l][text];
                if (params && params.length) {
                    for (var i = 0; i < params.length; i++) {
                        r = r.replace(new RegExp('\\{' + i + '\\}', 'g'), params[i]);
                    }
                }
                return $sce.trustAsHtml(r);
            }
            return text;
        }
    }

    angular.module('todolistApp').
        filter('i18n', ['$sce', 'Locale', 'localizedTexts', i18n]);

})();