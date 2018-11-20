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
        var methodName = 'save';
        if($scope.spec.id!=null) {
            methodName = 'modify'
        }
        specService.save(methodName,$scope.spec).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.loadPageList();
            }
        });
    }

    $scope.findOne=function(specId) {
        specService.findOne(specId).success(function (response) {
            //查找失败
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.spec=response.data;
            }
        })
    }

<<<<<<< HEAD
    $scope.del=function () {
        if(confirm("你确定需要删除勾选的规格信息?")) {
            specService.del($scope.selectIds).success(function (response) {
                if (response.code != 0) {
                    alert(response.msg);
                } else {
                    $scope.loadPageList();
                    $scope.selectIds = [];
                }
            })
        }
    }

=======
>>>>>>> c5a039e81ea04dd98164310a8cd028a03154db16
})
