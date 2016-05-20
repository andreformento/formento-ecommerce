(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$translate', '$translatePartialLoader'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('register');
        $translate.refresh();

        var vm = this;

        vm.register = register;

        function register() {
            UserService.Create(vm.user)
                .then(function (response) {
                    vm.dataLoading = false;
                    if (response.token) {
                        FlashService.Success($translate.instant('register.success'), true);
                        $location.path('/login');
                    } else {
                         FlashService.Error(response.message);
                    }
                    vm.dataLoading = false;
                },
                function (response) {
                    vm.dataLoading = false;
                    FlashService.Error(response.message);
                });
        }
    }

})();
