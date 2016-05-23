(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderEditController', OrderEditController);

    OrderEditController.$inject = ['OrderEditService', 'PaymentService', '$routeParams', 'AuthenticationService', 'FlashService', '$rootScope', '$location', '$translate', '$translatePartialLoader'];
    function OrderEditController(OrderEditService, PaymentService, $routeParams, AuthenticationService, FlashService, $rootScope, $location, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('order');
        $translate.refresh();

        var vm = this;

        vm.order = {};
        vm.boletoPaymentRequest = {};
        vm.creditCardPaymentRequest = {};
        vm.orderId = 0;
        vm.sending = false;

        initController();

        function initController() {
            vm.orderId = $routeParams.orderId;
            loadOrderById();
        }

        function loadOrderById() {
            OrderEditService
                .GetFromUserById(vm.orderId)
                .then(function (response) {
                    vm.order = response;
                });
        }

        vm.createBoletoPaymentFromOrder = function() {
            if (!vm.sending) {
                vm.sending = true;
                PaymentService
                    .createBoletoPaymentFromOrder(vm.orderId, vm.boletoPaymentRequest)
                    .then(function (response) {
                        if (response.paymentId) {
                            FlashService.Success($translate.instant('order.createdPayment'), true);
                            $location.path('/payments/' + response.paymentId);
                        } else if (response.message) {
                            FlashService.Error($translate.instant(response.message));
                        } else {
                            FlashService.Error($translate.instant('order.cannotCreatePayment'));
                        }
                    },
                    function (response) {
                        FlashService.Error(response.message);
                    });
            }
        }

        vm.createCreditCardPaymentFromOrder = function() {
            if (!vm.sending) {
                console.log('creditCardPaymentRequest', vm.creditCardPaymentRequest)
                console.log('data formatada1', moment(vm.creditCardPaymentRequest.creditCard.holder.birthdate).format('DD-MM-YYYY'));
//                console.log('data formatada2', $moment(creditCardPaymentRequest.creditCard.holder.birthdate).format('DD-MM-YYYY'));
                vm.creditCardPaymentRequest.creditCard.holder.birthdate = moment(vm.creditCardPaymentRequest.creditCard.holder.birthdate).format('DD-MM-YYYY');
                console.log('creditCardPaymentRequest certo ', vm.creditCardPaymentRequest)
                vm.sending = true;
                PaymentService
                    .createCreditCardPaymentFromOrder(vm.orderId, vm.creditCardPaymentRequest)
                    .then(function (response) {
                console.log('foi...', response)
                        if (response.paymentId) {
                console.log('pago', response.paymentId)
                            FlashService.Success($translate.instant('order.createdPayment'), true);
                            $location.path('/payments/' + response.paymentId);
                        } else if (response.message) {
                            FlashService.Error($translate.instant(response.message));
                        } else {
                            FlashService.Error($translate.instant('order.cannotCreatePayment'));
                        }
                    },
                    function (response) {
                        FlashService.Error(response.message);
                    });
            }
        }

    }

})();