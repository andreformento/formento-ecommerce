(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProductController', ProductController);

    ProductController.$inject = ['ProductService', 'ShoppingCartService', 'FlashService', '$rootScope', '$translate', '$translatePartialLoader'];
    function ProductController(ProductService, ShoppingCartService, FlashService, $rootScope, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('product');
        $translate.refresh();

        var vm = this;

        initController();

        function initController() {
            vm.allProducts = [];

            loadAllProducts();
        }

        function loadAllProducts() {
            ProductService.GetAll()
                .then(function (response) {
                    var products = response._embedded.products;
                    vm.allProducts = products;
                });
        }

        vm.add = function(product) {
            if ($rootScope.globals.connected) {
                var itemShoppingCart = {
                        "product" : product,
                        "quantity" : 1
                    }
                ShoppingCartService.Add(itemShoppingCart)
                    .then(function (response) {
                        if (response.quantity) {
                            FlashService.Success($translate.instant('shoppingItemCart.addWithSuccess'), true);
                            loadAllProducts();
                        } else if (itemShoppingCart.message) {
                            FlashService.Error($translate.instant('itemShoppingCart.cannotAddItem'));
                        } else {
                            FlashService.Error(response.message);
                        }
                    },
                    function (response) {
                        FlashService.Error(response.message);
                    });
            } else {
                FlashService.Info($translate.instant('shoppingItemCart.enterOnAccountToAddAnItem'), true);
            }
        }

    }

})();