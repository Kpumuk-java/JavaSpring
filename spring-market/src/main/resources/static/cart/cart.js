angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.marketCartUuid)
            .then(function (response) {
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
        $http.post(contextPath + '/api/v1/cart/clear', $localStorage.marketCartUuid)
            .then(function (response) {
                console.log("Clear OK")
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

    $scope.loadCart = function () {

    }

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.deleteProductInCartById = function (productID) {
        $http({
            url: contextPath + '/api/v1/cart/',
            method: 'DELETE',
            params: {
                uuid: $localStorage.marketCartUuid,
                product_id: productID
            }
        }).then(function (response) {
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