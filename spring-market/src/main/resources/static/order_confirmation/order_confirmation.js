angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.submitOrder = function () {
        if ($scope.order_info.address != null) {
            $http({
                url: contextPath + '/api/v1/orders',
                method: 'POST',
                params: {
                    address: $scope.order_info.address
                }
            }).then(function (response) {
                $location.path('/order_result/' + response.data.id);
            });
        } else {
            window.alert("Введите адрес доставки");
        }
    }


    $scope.cartContentRequest();
});