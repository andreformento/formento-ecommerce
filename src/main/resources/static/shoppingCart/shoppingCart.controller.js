(function () {
    'use strict';

    angular
        .module('app')
        .controller('ShoppingCartController', ShoppingCartController);

    ShoppingCartController.$inject = ['ShoppingCartService', 'OrderService', 'AuthenticationService', 'FlashService', '$rootScope', '$location', '$translate', '$translatePartialLoader'];
    function ShoppingCartController(ShoppingCartService, OrderService, AuthenticationService, FlashService, $rootScope, $location, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('shoppingCart');
        $translate.refresh();

        var vm = this;

        vm.allItemShoppingCarts = [];
        vm.shoppingCart = {};

        initController();

        function initController() {
            loadShoppingCart();
            loadAllItemShoppingCarts();
        }

        function loadAllItemShoppingCarts() {
            ShoppingCartService.GetAll()
                .then(function (response) {
                    vm.allItemShoppingCarts = response._embedded && response._embedded.itemShoppingCarts ? response._embedded.itemShoppingCarts : [];
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        function loadShoppingCart() {
            ShoppingCartService.GetFromUser()
                .then(function (response) {
                    vm.shoppingCart = response;
                });
        }

        vm.remove = function(itemShoppingCart) {

            ShoppingCartService.RemoveById(itemShoppingCart.id)
                .then(function (response) {
                    FlashService.Success($translate.instant('shoppingCart.removedItemFromCart'), true);
                    loadAllItemShoppingCarts();
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.add = function(itemShoppingCart) {
            ShoppingCartService.Add(itemShoppingCart)
                .then(function (response) {
                    if (response.quantity) {
                        FlashService.Success($translate.instant('shoppingCart.addItemToCart'), true);
                        loadAllItemShoppingCarts();
                    } else if (response.message) {
                        FlashService.Error($translate.instant('itemShoppingCart.cannotAddItem'));
                    } else {
                        FlashService.Error(response.message);
                    }
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.plus = function(itemShoppingCart) {
            ShoppingCartService.Plus(itemShoppingCart.id)
                .then(function (response) {
                    if (response.quantity) {
                        FlashService.Success($translate.instant('shoppingCart.addItemToCart'), true);
                        loadAllItemShoppingCarts();
                    } else if (itemShoppingCart.message) {
                        FlashService.Error($translate.instant('itemShoppingCart.cannotAddItem'));
                    } else {
                        FlashService.Error(response.message);
                    }
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.finalizeCurrentFromUser = function(itemShoppingCart) {
            OrderService.finalizeCurrentFromUser()
                .then(function (response) {
                    if (response.totalValue) {
                        FlashService.Success($translate.instant('shoppingCart.createdOrder'), true);

                        $location.path('/order');
                    } else if (response.message) {
                        FlashService.Error($translate.instant(response.message));
                    } else {
                        FlashService.Error($translate.instant('shoppingCart.cannotCreateOrder'));
                    }
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

    }

})();