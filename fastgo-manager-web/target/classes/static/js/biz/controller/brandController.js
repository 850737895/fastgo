app.controller('brandController',function ($scope,$controller,brandService) {

    $controller('baseController',{$scope:$scope});//继承

    //列表服务
    $scope.findList=function(){
        brandService.findList().success(function (result) {
            $scope.brandList=result.data;
        });
    }

    //分页查询品牌列表
    $scope.findListByPage=function (pageNum,pageSize) {
        brandService.findListByPage().success(
            function (response) {
                //当前页的数据
                $scope.brandList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems=response.data.total;
            });
    }
    //保存
    $scope.save = function() {
        var methodName = 'save';
        if($scope.brand.id!=null) {
            methodName = 'modify';
        }
        brandService.save(methodName,$scope.brand).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.loadPageList();
            }
        })
    }

    //查询
    $scope.findOneById=function (id) {
        brandService.findOneById(id).success(function (response) {
            //查找失败
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.brand=response.data;
            }
        })
    }

    //删除
    $scope.del=function(){
        if(confirm("你确定要删除吗?")) {
            brandService.del($scope.selectIds).success(function (response) {
                if (response.code != 0) {
                    alert(response.msg);
                } else {
                    $scope.loadPageList();
                    $scope.selectIds = [];
                }
            })
        }
    }

    //条件查询
    $scope.searchEntity={};
    $scope.search=function (pageNum,pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchEntity).success(function (response) {
            //当前页的数据
            $scope.brandList = response.data.result;
            //修改总条数
            $scope.paginationConf.totalItems=response.data.total;
        });
    }

})