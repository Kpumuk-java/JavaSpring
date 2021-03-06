(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productsController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/order_result/:orderId', {
                templateUrl: 'order_result/order_result.html',
                controller: 'orderResultController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });

        // $httpProvider.interceptors.push(function ($q, $location) {
        //     return {
        //         'responseError': function (rejection, $localStorage, $http) {
        //             var defer = $q.defer();
        //             if (rejection.status == 401 || rejection.status == 403) {
        //                 console.log('error: 401-403');
        //                 $location.path('/auth');
        //                 if (!(localStorage.getItem("localUser") === null)) {
        //                     delete $localStorage.currentUser;
        //                     $http.defaults.headers.common.Authorization = '';
        //                 }
        //                 console.log(rejection.data);
        //                 var answer = JSON.parse(rejection.data);
        //                 console.log(answer);
        //                 // window.alert(answer.message);
        //             }
        //             defer.reject(rejection);
        //             return defer.promise;
        //         }
        //     };
        // });
    }

    const contextPath = 'http://localhost:8189/market';

    function run($rootScope, $http, $localStorage) {

        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }

        //if (!$localStorage.marketCartUuid) {
            $http.post(contextPath + '/api/v1/cart')
                .then(function (response) {
                    $localStorage.marketCartUuid = response.data;
                });
       // }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;
    $scope.username = null;

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.username = $scope.user.username;

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
        $scope.authorized = false;
        $scope.username = null;
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            $scope.currentUserName = $scope.user.username;
            $scope.authorized = true;
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.currentUser) {
        $scope.username = $localStorage.currentUser.username;
        $scope.authorized = true;
    }
});