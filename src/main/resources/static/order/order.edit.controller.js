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
            console.log('$routeParams.orderId',$routeParams.orderId);
            vm.orderId = $routeParams.orderId;
            loadOrderById();
        }

        function loadOrderById() {
            OrderEditService
                .GetFromUserById(vm.orderId)
                .then(function (response) {
                console.log( 'order controller load by id', vm.orderId, response);
                    vm.order = response;
                });
        }

        vm.createBoletoPaymentFromOrder = function() {
            if (!vm.sending) {
                vm.sending = true;
                console.log('boleto',vm.orderId, vm.boletoPaymentRequest);
                PaymentService
                    .createBoletoPaymentFromOrder(vm.orderId, vm.boletoPaymentRequest)
                    .then(function (response) {
                console.log('finalize boleto response!!!!  ',response);
                        if (response.paymentId) {
                console.log('response.paymentId!!!!  ',response.paymentId);
                            FlashService.Success($translate.instant('order.createdPayment'), true);
                            $location.path('/payments/' + response.paymentId);
                        } else if (response.message) {
                            FlashService.Error($translate.instant(response.message));
                        } else {
                            FlashService.Error($translate.instant('order.cannotCreatePayment'));
                        }
                    },
                    function (response) {
                    console.log('erro',response);
                        FlashService.Error(response.message);
                    });
            }
        }

    }

})();