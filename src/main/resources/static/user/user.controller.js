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
        $translatePartialLoader.addPart('account');
        $translate.refresh();

        var vm = this;

        $scope.authentication = $rootScope.globals;
    }

})();
