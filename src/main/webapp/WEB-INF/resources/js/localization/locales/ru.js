(function () {
    'use strict';

    var localizedTextsRu = function (localizedTexts) {
        localizedTexts['ru'] = {

        }
    }

    angular.module('todolistApp').
        config(['localizedTexts', localizedTextsRu]);

})();