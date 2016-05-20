(function () {
    'use strict';

    angular
        .module('app')
        .factory('ShoppingCartService', ShoppingCartService);

    ShoppingCartService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function ShoppingCartService($http, $translate, $translatePartialLoader) {
        var service = {},
            _translatePartialLoader = $translatePartialLoader,
            _translate = $translate;

        _translatePartialLoader.addPart('system');
        _translatePartialLoader.addPart('exception');
        _translatePartialLoader.addPart('shoppingCart');
        _translate.refresh();

        service.GetAll = GetAll;
        service.DeleteById = DeleteById;

        return service;

        function GetAll() {
            return $http.get('/api/v1/item-shopping-carts').then(handleSuccess, handleError);
        }

        function DeleteById(id) {
            return $http.delete('/api/v1/item-shopping-carts/' + id).then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            var errorCode = error.data && error.data.message ? error.data.message : 'system.operationError',
                errorMessage = _translate.instant(errorCode);

            return function () {
               return { success: false, message: errorMessage };
            };
        }
    }

})();
