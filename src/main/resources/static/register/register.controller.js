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
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {
                    if (response.token) {
                        $translate('register.success')
                            .then(function (translatedValue) {
                                FlashService.Success(translatedValue, true);
                                vm.dataLoading = false;
                            });

                        $location.path('/login');
                    } else {
                        var errorCode = response.data && response.data.message ? response.data.message : 'user.error.onRegister';
                        $translate(errorCode)
                            .then(function (translatedValue) {
                                FlashService.Error(translatedValue);
                                vm.dataLoading = false;
                            });
                    }
                }, function errorCallback(response) {
                     var errorCode = response.data && response.data.message ? response.data.message : 'user.error.onRegister';
                     $translate(errorCode)
                         .then(function (translatedValue) {
                             FlashService.Error(translatedValue);
                             vm.dataLoading = false;
                         });
                 }
               );
        }
    }

})();
