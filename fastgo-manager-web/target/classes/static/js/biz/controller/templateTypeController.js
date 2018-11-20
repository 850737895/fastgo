/**
 * Created by Administrator on 2018/11/20.
 */
app.controller('tempTypeController',function($scope,$controller,tempTypeService,brandService,specService){
    $controller('baseController',{$scope:$scope});//继承

    $scope.initBrandList=function() {
        brandService.initBrandList().success(function(response){
            $scope.brandList={data:response.data};
        })
    }

    $scope.initSpecList=function() {
        specService.initSpecList().success(function(response){
            $scope.specList={data:response.data};
        })
    }

    //分页
    $scope.search=function(pageNum,pageSize) {
        var tempTypeName='';
        if(typeof($scope.tempTypeName) != "undefined") {
            tempTypeName = $scope.tempTypeName
        }
        tempTypeService.listPage(pageNum,pageSize,tempTypeName).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.tempTypeList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems=response.data.total;
            }
        })
    }

    $scope.addTableRows=function(){
       $scope.tempType.customAttributeItems.push({});
    }

    $scope.findOne=function(id) {
        tempTypeService.findOne(id).success(function (response) {
            //查找失败
            if(response.code!=0) {
                alert(response.msg);
            }else {
                //转换字符串为json对象（集合）
                $scope.tempType= response.data;
                $scope.tempType.brandIds=  JSON.parse( $scope.tempType.brandIds);
                $scope.tempType.specIds= JSON.parse($scope.tempType.specIds);
                $scope.tempType.customAttributeItems = JSON.parse($scope.tempType.customAttributeItems);
            }
        })
    }


    //列表服务
    $scope.delTableRows=function(index){
        $scope.tempType.customAttributeItems.splice(index,1);
    }

    $scope.save = function() {

        var methodName = 'save';
        if($scope.tempType.id!=null) {
            methodName = 'modify'
        }
        tempTypeService.save(methodName,$scope.tempType).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.loadPageList();
            }
        })
    }

    $scope.del=function () {
        if(confirm("你确定需要删除勾选的模版信息?")) {
            tempTypeService.del($scope.selectIds).success(function (response) {
                if (response.code != 0) {
                    alert(response.msg);
                } else {
                    $scope.loadPageList();
                    $scope.selectIds = [];
                }
            })
        }
    }


})
