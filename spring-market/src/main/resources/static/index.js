angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title: $scope.filter ? $scope.filter.title : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1 ) {
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
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.deleteProductById = function (productID) {
        $http.delete(contextPath + '/products/' + productID).then(function (response) {
            $scope.fillTable();
        });
    }

    $scope.addProductInCartById = function(productID) {
        $http({
            url: contextPath + '/cart/add',
            method: 'GET',
            params: {
                id: productID,
                count: "1"
            }
        }).then(function (response) {
            $scope.fillTableCart();
        });
    }

    $scope.fillTableCart = function () {
        $http.get(contextPath + '/cart/').then(function (response) {
            $scope.ProductsPageCart = response.data;
        });
    }

    $scope.deleteProductInCartById = function (productId) {
        $http.get(contextPath + '/cart/delete/' + productId).then(function (response) {
            $scope.fillTableCart();
        });
    }


    $scope.fillTable();
});