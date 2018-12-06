/**
 * Created by Administrator on 2018/12/5.
 */
app.controller('contentCategoryController',function($scope,$controller,contentCategoryService){
    $controller('baseController',{$scope:$scope});//继承

    $scope.categoryStatus=['无效','有效'];

    /**
     * 分页条件搜索
     */
    $scope.search=function(pageNum,pageSize){
        var categoryName =$scope.categoryName;
        if(typeof(categoryName)=='undefined'){
            categoryName='';
        }
        contentCategoryService.list4PageByCondition(pageNum,pageSize,categoryName).success(function(response){
            if(response.code!=0) {
                alert(msg);
            }else{
                $scope.contentCategoryList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems = response.data.total;
            }
        })
    }

    /**
     * 保存
     */
    $scope.save=function() {
        var methodName = 'save';
        if($scope.contentCategory.id!=null) {//修改
            methodName = 'modify';
        }
        contentCategoryService.saveOrModify(methodName,$scope.contentCategory).success(function(response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.loadPageList();
                $scope.contentCategory={};
            }
        });
    }

    $scope.del=function () {
        if($scope.selectIds.length==0) {
            alert("请勾选需要删除的广告类型");
            return null;
        }
        contentCategoryService.del($scope.selectIds).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);

            }else {
                $scope.loadPageList();
                $scope.selectIds=[];
            }
        })
    }

    $scope.findOneById=function(id) {
        contentCategoryService.findOneById(id).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.contentCategory=response.data;
            }
        })
    }

})
