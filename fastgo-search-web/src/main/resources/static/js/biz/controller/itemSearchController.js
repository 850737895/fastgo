/**
 * Created by 85073 on 2018/12/9.
 */
app.controller('itemSearchController',function($scope,$controller,itemSearchService) {
    $controller('baseController', {$scope: $scope});//继承

    $scope.keyworkdSearch=function() {
        if(typeof ($scope.searchEntity)=='undefined' || $scope.searchEntity=="''") {
            alert('请输入搜索结果');
        }
        itemSearchService.keyworkdSearch($scope.searchEntity).success(function (response) {
            if(response.code!=0) {
                alert("搜索君开小差了，请稍后再试......");
            }else{
                $scope.itemList = response.data.rows;
            }
        })
    }

})