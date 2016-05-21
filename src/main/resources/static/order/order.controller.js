(function () {
    'use strict';

    angular
        .module('app')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['OrderService', 'AuthenticationService', 'FlashService', '$rootScope', '$translate', '$translatePartialLoader'];
    function OrderController(OrderService, AuthenticationService, FlashService, $rootScope, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('order');
        $translate.refresh();

        var vm = this;

        vm.allItemOrders = [];

        initController();

        function initController() {
            loadAllItemOrders();
        }

        function loadAllItemOrders() {
            OrderService.GetAll()
                .then(function (response) {
                    vm.allItemOrders = response._embedded && response._embedded.itemOrders ? response._embedded.itemOrders : [];
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.remove = function(itemOrder) {
            console.log('itemOrder',itemOrder);

            OrderService.RemoveById(itemOrder.id)
                .then(function (response) {
                    FlashService.Success($translate.instant('order.removedItemFromCart'), true);
                    loadAllItemOrders();
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.add = function(itemOrder) {
            console.log('add',itemOrder);

            OrderService.Add(itemOrder.id)
                .then(function (response) {
                    FlashService.Success($translate.instant('order.addItemToCart'), true);
                    loadAllItemOrders();
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.plus = function(itemOrder) {
            console.log('plus',itemOrder);

            OrderService.Plus(itemOrder.id)
                .then(function (response) {
                console.log('ok',response);
                    if (response.quantity) {
                        FlashService.Success($translate.instant('order.addItemToCart'), true);
                        loadAllItemOrders();
                    } else if (itemOrder.message) {
                        FlashService.Error($translate.instant('itemOrder.cannotAddItem'));
                    } else {
                        FlashService.Error(response.message);
                    }
                },
                function (response) {
                console.log('erro',response);
                    FlashService.Error(response.message);
                });
        }

        vm.finalizeCurrentFromUser = function(itemOrder) {
            OrderService.finalizeCurrentFromUser()
                .then(function (response) {
                    if (response.quantity) {
                        FlashService.Success($translate.instant('order.createdOrder'), true);
                        $location.path('/order');
                    } else if (itemOrder.message) {
                        FlashService.Error($translate.instant('order.cannotCreateOrder'));
                    } else {
                        FlashService.Error(response.message);
                    }
                },
                function (response) {
                console.log('erro',response);
                    FlashService.Error(response.message);
                });
        }

    }

})();