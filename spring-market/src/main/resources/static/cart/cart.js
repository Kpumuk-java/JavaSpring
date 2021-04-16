angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.marketCartUuid)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'POST',
            params: {
                uuid: $localStorage.marketCartUuid
            }
        }).then(function (response) {
            $scope.showCart();
            console.log("Clear OK");
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

    $scope.deleteProductInCartById = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'DELETE',
            params: {
                uuid: $localStorage.marketCartUuid,
                product_id: productId
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

    $scope.incrementQuantity = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/inc',
            method: 'POST',
            params: {
                uuid: $localStorage.marketCartUuid,
                product_id: productId
            }
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.showCart();
});