angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.marketCartUuid)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.submitOrder = function () {
        if ($scope.order_info.address != null) {
            $http({
                url: contextPath + '/api/v1/orders',
                method: 'POST',
                params: {
                    cartUuid: $localStorage.marketCartUuid,
                    address: $scope.order_info.address
                }
            }).then(function (response) {
                $location.path('/order_result/' + response.data.id);
            });
        } else {
            window.alert("Введите адрес доставки");
        }
    }


    $scope.showCart();
});