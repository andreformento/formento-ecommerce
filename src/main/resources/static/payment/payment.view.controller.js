(function () {
    'use strict';

    angular
        .module('app')
        .controller('PaymentViewController', PaymentViewController);

    PaymentViewController.$inject = ['PaymentViewService', 'PaymentService', '$routeParams', 'AuthenticationService', 'FlashService', '$rootScope', '$location', '$translate', '$translatePartialLoader'];
    function PaymentViewController(PaymentViewService, PaymentService, $routeParams, AuthenticationService, FlashService, $rootScope, $location, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('payment');
        $translate.refresh();

        var vm = this;

        vm.payment = {};
        vm.paymentId = 0;
        vm.sending = false;

        initController();

        function initController() {
            console.log('$routeParams.paymentId',$routeParams.paymentId);
            vm.paymentId = $routeParams.paymentId;
            loadPaymentById();
        }

        function loadPaymentById() {
            PaymentViewService
                .GetFromUserById(vm.paymentId)
                .then(function (response) {
                console.log( 'payment controller load by id', vm.paymentId, response);
                    vm.payment = response;
                });
        }

    }

})();