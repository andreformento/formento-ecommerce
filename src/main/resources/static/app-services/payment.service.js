(function () {
    'use strict';

    angular
        .module('app')
        .factory('PaymentService', PaymentService);

    PaymentService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function PaymentService($http, $translate, $translatePartialLoader) {
        var service = {},
            _translatePartialLoader = $translatePartialLoader,
            _translate = $translate;

        _translatePartialLoader.addPart('system');
        _translatePartialLoader.addPart('exception');
        _translatePartialLoader.addPart('payment');
        _translate.refresh();

        service.createBoletoPaymentFromOrder = createBoletoPaymentFromOrder;
        service.createCreditCardPaymentFromOrder = createCreditCardPaymentFromOrder;

        return service;

        function createBoletoPaymentFromOrder(orderId, boletoPaymentRequest) {
        console.log('createBoletoPaymentFromOrder(orderId,boletoPaymentRequest) {', orderId,boletoPaymentRequest);
            return $http.post('/api/v1/boleto-payment-payments/order/' + orderId, boletoPaymentRequest).then(handleSuccess, handleError);
        }

        function createCreditCardPaymentFromOrder(orderId, creditCardPaymentRequest) {
        console.log('createCreditCardPaymentFromOrder(orderId,creditCardPaymentRequest) {', orderId,creditCardPaymentRequest);
            return $http.post('/api/v1/credit-card-payments/order/' + orderId, creditCardPaymentRequest).then(handleSuccess, handleError);
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
