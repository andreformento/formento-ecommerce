(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function UserService($http, $translate, $translatePartialLoader) {
        var service = {},
               _translatePartialLoader = $translatePartialLoader,
               _translate = $translate;

        $translatePartialLoader.addPart('system');
        $translatePartialLoader.addPart('exception');
        $translate.refresh();

        service.GetCurrentUser = GetCurrentUser;
        service.GetById = GetById;
        service.GetByEmail = GetByEmail;
        service.Create = Create;

        return service;

        function GetCurrentUser() {
            return $http.get('/api/v1/users').then(handleSuccess, handleError);
        }

        function GetById(id) {
            return $http.get('/api/v1/users/' + id).then(handleSuccess, handleError);
        }

        function GetByEmail(email) {
            return $http.get('/api/v1/users' + email).then(handleSuccess, handleError);
        }

        function Create(user) {
            return $http.post('/api/v1/users', user).then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            var errorCode = error.data && error.data.message ? error.data.message : 'user.error.onRegister',
                errorMessage = _translate.instant(errorCode);

            return { success: false, message: errorMessage };
        }
    }

})();
