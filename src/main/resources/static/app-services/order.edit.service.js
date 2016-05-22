(function () {
    'use strict';

    angular
        .module('app')
        .factory('OrderEditService', OrderEditService);

    OrderEditService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function OrderEditService($http, $translate, $translatePartialLoader) {
        var service = {},
            _translatePartialLoader = $translatePartialLoader,
            _translate = $translate;

        _translatePartialLoader.addPart('system');
        _translatePartialLoader.addPart('exception');
        _translatePartialLoader.addPart('order');
        _translate.refresh();

        service.GetFromUserById = GetFromUserById;

        return service;

        function GetFromUserById(orderId) {
            return $http.get('/api/v1/orders/' + orderId).then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            var errorCode = error.data && error.data.message ? error.data.message : 'system.operationError',
                errorMessage = _translate.instant(errorCode);

            return { success: false, message: errorMessage };
        }
    }

})();
