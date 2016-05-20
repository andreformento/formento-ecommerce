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
            console.log('SetCredentials(user)',user);
            if (user && user.token) {
                console.log('user && user.token',user);
                var token = user.token;
                $rootScope.globals = {
                    currentUser: user,
                    token: token
                };

                $http.defaults.headers.common['Authorization'] = 'Bearer ' + token; // jshint ignore:line
                console.log('Authorization',$http.defaults.headers.common['Authorization']);
                $cookieStore.put('globals', $rootScope.globals);
            } else {
                ClearCredentials();
            }
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common['Authorization'] = '';
        }
    }

})();