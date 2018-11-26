/**
 * Created by Administrator on 2018/11/26.
 */
app.controller('sellerController',function ($scope,$controller,sellerService) {
    $controller('baseController',{$scope:$scope});//继承

    //查询
    $scope.search=function(pageNum,pageSize) {
        var seller=null;
        if(typeof($scope.qrySeller) != "undefined") {
           seller = $scope.qrySeller;
        }
        sellerService.qrySellerList(pageNum,pageSize,seller).success(function(response) {
            if (response.code != 0) {
                alert(response.msg);
            } else {
                //当前页的数据
                $scope.sellerList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems = response.data.total;
            }
        })
    }

    //根据商家ID 查询
    $scope.findOneById=function(sellerId) {
        sellerService.findOneById(sellerId).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.seller = response.data;
            }
        })
    }

    //更新商家用户状态
    $scope.updateAccountStatus=function(sellerId,status) {
        alert(sellerId);
        alert(status);
    }
})
