(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope', 'UserService'];
    function AuthenticationService($http, $cookieStore, $rootScope, UserService) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login(email, password, callback) {
            return $http.post('/api/v1/auth', { email: email, password: password });
        }

        function SetCredentials(user) {
            $rootScope.globals = {
                currentUser: user
            };

            $http.defaults.headers.common['Authorization'] = 'Bearer ' + user.token; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Bearer';
        }
    }

})();