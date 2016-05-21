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
        service.GetFromUser = GetFromUser;
        service.RemoveById = RemoveById;
        service.Add = Add;
        service.Plus = Plus;
        service.finalizeCurrentFromUser = finalizeCurrentFromUser;

        return service;

        function GetAll() {
            return $http.get('/api/v1/item-shopping-carts').then(handleSuccess, handleError);
        }

        function GetFromUser() {
            return $http.get('/api/v1/shopping-carts').then(handleSuccess, handleError);
        }

        function finalizeCurrentFromUser() {
            return $http.post('/api/v1/shopping-carts').then(handleSuccess, handleError);
        }

        function Add(itemShoppingCart) {
            console.log('Add service',itemShoppingCart);
            return $http.post('/api/v1/item-shopping-carts/', itemShoppingCart).then(handleSuccess, handleError);
        }

        function Plus(id) {
            console.log('plus service id',id);
            return $http.patch('/api/v1/item-shopping-carts/' + id).then(handleSuccess, handleError);
        }

        function RemoveById(id) {
            return $http.delete('/api/v1/item-shopping-carts/' + id).then(handleSuccess, handleError);
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
