(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProductController', ProductController);

    ProductController.$inject = ['ProductService', '$rootScope', '$translate', '$translatePartialLoader'];
    function ProductController(ProductService, $rootScope, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('product');
        $translate.refresh();

        var vm = this;

        vm.allProducts = [];

        initController();

        function initController() {
            loadAllProducts();
        }

        function loadAllProducts() {
            ProductService.GetAll()
                .then(function (response) {
                    var products = response._embedded.products;
                    vm.allProducts = products;
                });
        }

    }

})();