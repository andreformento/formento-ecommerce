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
        console.log('$translate',$translate);
        console.log('account.welcome',$translate.instant('account.welcome'));
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