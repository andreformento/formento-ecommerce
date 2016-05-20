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

    }

})();