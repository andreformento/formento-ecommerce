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

    }

})();