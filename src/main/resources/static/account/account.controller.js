(function () {
    'use strict';

    angular
        .module('app')
        .controller('AccountController', AccountController);

    AccountController.$inject = ['UserService', '$rootScope', '$translate', '$translatePartialLoader'];
    function AccountController(UserService, $rootScope, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('account');
        $translate.refresh();

        var vm = this;

        vm.user = {};

        initController();

        function initController() {
            loadCurrentUser();
        }

        function loadCurrentUser() {
            UserService.GetCurrentUser()
                .then(function (user) {
                    vm.user = user;
                });
        }

    }

})();