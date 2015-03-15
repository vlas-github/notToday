(function () {
    'use strict';

    var localizedTextsEn = function (localizedTexts) {
        localizedTexts['en'] = {

        }
    }

    angular.module('todolistApp').
        config(['localizedTexts', localizedTextsEn]);

})();