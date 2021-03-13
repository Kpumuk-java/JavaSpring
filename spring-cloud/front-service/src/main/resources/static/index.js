angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;
    $scope.cartNotNull = false;
    $scope.username = null;

    $scope.showProductPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages
            }
            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.showProductPage();
            });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.deleteProductById = function (productID) {
        $http.delete(contextPath + '/api/v1/products/' + productID).then(function (response) {
            $scope.showProductPage();
        });
    };

    $scope.addProductInCartById = function (productID) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productID,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.deleteProductInCartById = function (productID) {
        $http.delete(contextPath + '/api/v1/cart/' + productID).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            if (response.data.items.length > 0) {
                $scope.cartNotNull = true;
                $scope.ProductsPageCart = response.data;
            } else {
                $scope.cartNotNull = false;
            }

        });
    };

    $scope.decrementQuantity = function (ProductsPageCartItems) {
        if (ProductsPageCartItems.quantity == 1) {
            $scope.deleteProductInCartById(ProductsPageCartItems.id);
        } else {
            $http.get(contextPath + '/api/v1/cart/dec/' + ProductsPageCartItems.id)
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

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;

                    $localStorage.marketCurrentUser = $scope.user.username;
                    $localStorage.marketTokenWithBearerPrefix = 'Bearer ' + response.data.token;

                    $scope.username = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.showProductPage();
                    $scope.showCart();
                    $scope.showMyOrders();
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };



    $scope.createOrder = function () {
        if ($scope.address != null) {
            $http.get(contextPath + '/api/v1/orders/create/' + $scope.address)
                .then(function (response) {
                    $scope.showMyOrders();
                    $scope.showCart();
                });
        } else {
            window.alert("Введите адрес доставки");
        }
    };

    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.logout = function () {
        $http.defaults.headers.common.Authorization = null;
        delete $localStorage.marketCurrentUser;
        delete $localStorage.marketTokenWithBearerPrefix;
        $scope.username = null;
        $scope.authorized = false;
        $scope.showCart();
        $scope.showMyOrders();
    }

    $scope.showProductPage();

    if ($localStorage.marketCurrentUser) {
        $http.defaults.headers.common.Authorization = $localStorage.marketTokenWithBearerPrefix;
        $scope.authorized = true;
        $scope.username = $localStorage.marketCurrentUser;
        $scope.showCart();
        $scope.showMyOrders();
    }
});