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
        vm.boletoPaymentRequest = {"installmentCount": 1};
        vm.creditCardPaymentRequest = {"installmentCount": 1};
        vm.orderId = 0;
        vm.coupon = {"code": ""};
        vm.sending = false;
        vm.sendingDiscountCoupon = false;

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
                    console.log('load',vm.order);
                    vm.coupon = vm.order.coupon;
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
                vm.sending = true;
                vm.creditCardPaymentRequest.creditCard.holder.birthdate = moment(vm.creditCardPaymentRequest.creditCard.holder.birthdate).format('DD-MM-YYYY');
                PaymentService
                    .createCreditCardPaymentFromOrder(vm.orderId, vm.creditCardPaymentRequest)
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

        vm.applyDiscount = function() {
        console.log('applydiscount',vm.orderId, vm.coupon);
            if (!vm.sendingDiscountCoupon) {
                vm.sendingDiscountCoupon = true;
                OrderEditService
                    .applyDiscount(vm.orderId, vm.coupon)
                    .then(function (response) {
                        console.log('fez request', response);
                        if (response.orderId) {
                            FlashService.Success($translate.instant('order.discountDone'), true);
                            loadOrderById();
                        } else if (response.message) {
                            FlashService.Error($translate.instant(response.message));
                        } else {
                            FlashService.Error($translate.instant('order.cannotDoDiscount'));
                        }

                        vm.sendingDiscountCoupon = false;
                    },
                    function (response) {
                        FlashService.Error(response.message);

                        vm.sendingDiscountCoupon = false;
                    });
            }
        }

    }

})();