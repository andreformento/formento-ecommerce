(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService', '$translate', '$translatePartialLoader', '$window'];
    function LoginController($location, AuthenticationService, FlashService, $translate, $translatePartialLoader, $window) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('login');
        $translate.refresh();

        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {

            vm.dataLoading = true;
            AuthenticationService
                .Login(vm.email, vm.password)
                .then(
                    function successCallback(response) {
                        var user = response.data;

                        if (user.token) {
                            AuthenticationService.SetCredentials(user);
                            $window.location.href = '/';
                        } else {
                            var errorCode = response.data && response.data.message ? response.data.message : 'user.error.onLogin';
                            $translate(errorCode)
                                .then(function (translatedValue) {
                                    FlashService.Error(translatedValue);
                                });
                        }

                        vm.dataLoading = false;
                    }, function errorCallback(response) {
                        var errorCode = response.data && response.data.message ? response.data.message : 'user.error.onLogin';
                        $translate(errorCode)
                            .then(function (translatedValue) {
                                FlashService.Error(translatedValue);
                                vm.dataLoading = false;
                            });
                    }
                );

        };
    }

})();
