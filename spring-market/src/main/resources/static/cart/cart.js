angular.module('app').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.createOrder = function () {
        $http.get(contextPath + '/api/v1/orders/create')
            .then(function (response) {
                $scope.showMyOrders();
                $scope.showCart();
            });
    }

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.deleteProductInCartById = function (productID) {
        $http.delete(contextPath + '/api/v1/cart/' + productID).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.decrementQuantity = function (Cart) {
        if (Cart.quantity == 1) {
            $scope.deleteProductInCartById(Cart.id);
        } else {
            $http.get(contextPath + '/api/v1/cart/dec/' + Cart.id)
                .then(function (response) {
                    $scope.showCart();
                });
        }
    };

    $scope.incrementQuantity = function (productID) {
        $http.get(contextPath + '/api/v1/cart/inc/' + productID)
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.showCart();
});