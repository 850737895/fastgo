/**
 * 规格管理controller
 * Created by Administrator on 2018/11/16.
 */
app.controller('specController',function ($scope,$controller,specService) {
    $controller('baseController',{$scope:$scope});//继承

    $scope.addTableRows=function(){
        $scope.spec.specOps.push({});
    }
    //列表服务
    $scope.delTableRows=function(index){
        $scope.spec.specOps.splice(index,1);
    }

    //列表服务
    $scope.search = function(pageNum,pageSize) {
        //alert($scope.specName);
        var queryCondition = '';
        if (typeof($scope.specName) != "undefined") {
            queryCondition = $scope.specName;
        }
        specService.search(pageNum,pageSize,queryCondition).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.specList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems=response.data.total;
            }
        });
    }

    $scope.save= function(){
        specService.save($scope.spec).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.loadPageList();
            }
        });
    }

})
