(function () {
    'use strict';

    angular
        .module('app')
        .factory('OrderService', OrderService);

    OrderService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function OrderService($http, $translate, $translatePartialLoader) {
        var service = {},
            _translatePartialLoader = $translatePartialLoader,
            _translate = $translate;

        _translatePartialLoader.addPart('system');
        _translatePartialLoader.addPart('exception');
        _translatePartialLoader.addPart('order');
        _translate.refresh();

        service.GetAll = GetAll;
        service.RemoveById = RemoveById;
        service.Add = Add;
        service.Plus = Plus;

        return service;

        function GetAll() {
            return $http.get('/api/v1/orders').then(handleSuccess, handleError);
        }

        function Add(itemOrder) {
            console.log('plus service',itemOrder);
            return $http.post('/api/v1/orders/', itemOrder).then(handleSuccess, handleError);
        }

        function Plus(id) {
            console.log('plus service id',id);
            return $http.patch('/api/v1/orders/' + id).then(handleSuccess, handleError);
        }

        function RemoveById(id) {
            return $http.delete('/api/v1/orders/' + id).then(handleSuccess, handleError);
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
