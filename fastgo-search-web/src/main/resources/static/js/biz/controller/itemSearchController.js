/**
 * Created by 85073 on 2018/12/9.
 */
app.controller('itemSearchController',function($scope,$controller,itemSearchService) {
    $controller('baseController', {$scope: $scope});//继承

    $scope.searchEntity={"keywords":'',"categoryName":'',"brand":'','spec':{}};

    $scope.keyworkdSearch=function() {
        if(typeof ($scope.searchEntity)=='undefined' || $scope.searchEntity=="''") {
            alert('请输入搜索结果');
        }
        itemSearchService.keyworkdSearch($scope.searchEntity).success(function (response) {
            if(response.code!=0) {
                alert("搜索君开小差了，请稍后再试......");
            }else{
                $scope.itemList = response.data.rows;
                $scope.categoryNameList = response.data.groupNames;
                $scope.brandList = response.data.brandList;
                $scope.specList = response.data.specList;
            }
        })
    }

    /**
     * 增加搜索条件
     */
    $scope.addSearchEntity=function(key,value) {
        if(key=='categoryName' || key =='brand') {
            $scope.searchEntity[key] = value;
        }else{
            $scope.searchEntity.spec[key]=value;
        }
    }

    /**
     * 移除收索条件
     * @param key
     */
    $scope.removeSearchEntity = function(key) {
        if(key=='categoryName' || key =='brand') {
            $scope.searchEntity[key] = '';
        }else{
            delete $scope.searchEntity.spec[key];//移除此属性
        }
    }

})