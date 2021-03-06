﻿(function () {
    'use strict';

    angular
        .module('app')
        .factory('ProductService', ProductService);

    ProductService.$inject = ['$http'];
    function ProductService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;

        return service;

        function GetAll() {
            return $http.get('/api/v1/products').then(handleSuccess, handleError);
        }

        function GetById(id) {
            return $http.get('/api/v1/products/' + id).then(handleSuccess, handleError);
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
