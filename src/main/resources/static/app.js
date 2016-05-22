(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'pascalprecht.translate'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider', '$translateProvider', '$translatePartialLoaderProvider'];
    function config($routeProvider, $locationProvider, $translateProvider, $translatePartialLoaderProvider) {

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
        if ($rootScope.globals.connected) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.token; // jshint ignore:line
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

})();
