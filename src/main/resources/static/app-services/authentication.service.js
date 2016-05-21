(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope', 'UserService', '$route'];
    function AuthenticationService($http, $cookieStore, $rootScope, UserService, $route) {
        var service = {};

        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;

        return service;

        function Login(email, password, callback) {
            return $http.post('/api/v1/auth', { email: email, password: password });
        }

        function SetCredentials(user) {
            if (user && user.token) {
                var token = user.token;
                $rootScope.globals = {
                    currentUser: user,
                    connected: true
                };

                $http.defaults.headers.common['Authorization'] = 'Bearer ' + token; // jshint ignore:line
                $cookieStore.put('globals', $rootScope.globals);
            } else {
                ClearCredentials();
            }
        }

        function ClearCredentials() {
            $rootScope.globals.connected = false;
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common['Authorization'] = '';
        }
    }

})();