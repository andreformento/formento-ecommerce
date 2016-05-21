(function () {
    'use strict';

    angular
        .module('app')
        .controller('ShoppingCartController', ShoppingCartController);

    ShoppingCartController.$inject = ['ShoppingCartService', 'AuthenticationService', 'FlashService', '$rootScope', '$translate', '$translatePartialLoader'];
    function ShoppingCartController(ShoppingCartService, AuthenticationService, FlashService, $rootScope, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('shoppingCart');
        $translate.refresh();

        var vm = this;

        vm.allItemShoppingCarts = [];

        initController();

        function initController() {
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

        vm.remove = function(itemShoppingCart) {
            console.log('itemShoppingCart',itemShoppingCart);

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
            console.log('add',itemShoppingCart);

            ShoppingCartService.Add(itemShoppingCart.id)
                .then(function (response) {
                    FlashService.Success($translate.instant('shoppingCart.addItemToCart'), true);
                    loadAllItemShoppingCarts();
                },
                function (response) {
                    FlashService.Error(response.message);
                });
        }

        vm.plus = function(itemShoppingCart) {
            console.log('plus',itemShoppingCart);

            ShoppingCartService.Plus(itemShoppingCart.id)
                .then(function (response) {
                console.log('ok',response);
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
                console.log('erro',response);
                    FlashService.Error(response.message);
                });
        }

    }

})();