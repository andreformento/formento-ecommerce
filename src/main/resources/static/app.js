(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'pascalprecht.translate'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$httpProvider', '$locationProvider', '$translateProvider', '$translatePartialLoaderProvider'];
    function config($routeProvider, $httpProvider, $locationProvider, $translateProvider, $translatePartialLoaderProvider) {

        $httpProvider.interceptors.push('responseObserver');

        $translateProvider
            .useLoader('$translatePartialLoader', {
                urlTemplate: '/i18n/{lang}/{part}.json'
            })
            .preferredLanguage('pt-BR');

        $routeProvider
            .when('/account', {
                controller: 'UserController',
                templateUrl: 'account/account.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })

            .when('/products', {
                controller: 'ProductController',
                templateUrl: 'product/product.view.html',
                controllerAs: 'vm'
            })

            .when('/shoppingCarts', {
                controller: 'ShoppingCartController',
                templateUrl: 'shoppingCart/shoppingCart.view.html',
                controllerAs: 'vm'
            })

            .when('/orders/:orderId', {
                controller: 'OrderEditController',
                templateUrl: 'order/order.edit.html',
                controllerAs: 'vm'
            })

            .when('/order', {
                controller: 'OrderController',
                templateUrl: 'order/order.view.html',
                controllerAs: 'vm'
            })

            .when('/payments/:paymentId', {
                controller: 'PaymentViewController',
                templateUrl: 'payment/payment.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/products' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals && $rootScope.globals.connected) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.token; // jshint ignore:line
        } else {
            $http.defaults.headers.common['Authorization'] = '';
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/products']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/products');
            }
        });
    }

    angular.module('app').factory('responseObserver', function responseObserver($q, $window, $cookieStore) {
        return {
            'responseError': function(errorResponse) {

                switch (errorResponse.status) {
                case 500:
                    if (errorResponse.data) {
                        var ecommerceError = JSON.parse(errorResponse.data),
                            messageError = ecommerceError.message,
                            revalidate = messageError == 'user.invalidToken';
                        if (revalidate) {
                            $cookieStore.remove('globals');
                            $http.defaults.headers.common['Authorization'] = '';

                            $window.location = '/#/login';
                        }
                    }
                    break;
                }
                return $q.reject(errorResponse);
            }
        };
    });

})();
