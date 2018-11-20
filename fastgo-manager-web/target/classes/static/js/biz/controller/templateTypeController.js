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
        if(typeof($scope.specName) != "undefined") {
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

    $scope.save = function() {
        alert($scope.tempType);
        console.log($scope.tempType);
    }

    //列表服务
    $scope.delTableRows=function(index){
        $scope.tempType.customAttributeItems.splice(index,1);
    }


})
