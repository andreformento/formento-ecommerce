(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['UserService', 'AuthenticationService', '$location', '$scope', '$rootScope', 'FlashService', '$translate', '$translatePartialLoader'];
    function UserController(UserService, AuthenticationService, $location, $scope, $rootScope, FlashService, $translate, $translatePartialLoader) {
        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translatePartialLoader.addPart('user');
        $translate.refresh();

        var vm = this;

        vm.user = user;
        $scope.authentication = AuthenticationService;

        (function initController() {
//            $scope.user = AuthenticationService.currentUser;
            console.log('AuthenticationService.currentUser',AuthenticationService.currentUser);
//            console.log('$rootScope.globals.currentUser',$rootScope.globals.currentUser);
//            vm.user = AuthenticationService.currentUser;
//            console.log('vm.user',vm.user);
////            loadCurrentUser();
        })();

        function user() {
            console.log('AuthenticationService.currentUser2',AuthenticationService.currentUser);
            return AuthenticationService.currentUser;
        }

    }

})();
