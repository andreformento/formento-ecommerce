(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['OrderService', 'AuthenticationService', 'FlashService', '$rootScope', '$location', '$translate', '$translatePartialLoader'];
    function OrderController(OrderService, AuthenticationService, FlashService, $rootScope, $location, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('order');
        $translate.refresh();

        var vm = this;

        vm.order = {};

        initController();

        function initController() {
            loadOrder();
        }

        function loadOrder() {
            OrderService.GetFromUser()
                .then(function (response) {
                console.log( 'order controller response',response);
                    vm.order = response;
                });
        }

        vm.finalizeCurrentFromUser = function(itemOrder) {
            console.log('finalizeCurrentFromUser',itemOrder);
            OrderService.finalizeCurrentFromUser()
                .then(function (response) {
                    if (response.quantity) {
                        FlashService.Success($translate.instant('order.createdPayment'), true);
                        $location.path('/payment');
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

})();