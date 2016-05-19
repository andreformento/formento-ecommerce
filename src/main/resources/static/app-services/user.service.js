(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetCurrentUser = GetCurrentUser;
        service.GetById = GetById;
        service.GetByEmail = GetByEmail;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get('/api/v1/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetCurrentUser() {
            return $http.get('/api/v1/users').then(handleSuccess, handleError('Error getting current users'));
        }

        function GetById(id) {
            return $http.get('/api/v1/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByEmail(email) {
            return $http.get('/api/v1/users' + email).then(handleSuccess, handleError('Error getting user by email'));
        }

        function Create(user) {
            return $http.post('/api/v1/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/api/v1/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/v1/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
