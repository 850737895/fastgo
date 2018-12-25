/**
 * Created by Administrator on 2018/12/25.
 */
app.controller("cartController",function($scope,$controller,cartService){
    $controller('baseController', {$scope: $scope});//继承

    /**
     * 加载购物车列表
     */
    $scope.loadCartList=function() {
        cartService.loadCartList().success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.cartList=response.data;
            }
        })
    }
})
