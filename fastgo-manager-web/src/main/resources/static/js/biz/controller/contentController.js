/**
 * Created by Administrator on 2018/12/5.
 */
app.controller('contentController',function($scope,$controller,contentService,contentCategoryService,fileUploadService) {
    $controller('baseController', {$scope: $scope});//继承

    $scope.search=function(pageNum,pageSize) {
        contentService.list4Page(pageNum,pageSize).success(function(response){
            if(response.code!=0) {
                alert(msg);
            }else{
                $scope.contentList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems = response.data.total;
            }
        })
    }

    $scope.categoryNames=[];

    $scope.contentStatus=['无效','有效'];

    $scope.initCategoryName=function () {
        contentCategoryService.findAll().success(function (response) {
            if(response.code!=0) {
                alert("初始化广告类别名称出错");
            }else {
                for(var index=0;index<response.data.length;index++) {
                    $scope.categoryNames[response.data[index].id]=response.data[index].name;
                }
            }
        })
    }

    $scope.content={};

    $scope.uploadFile=function () {
        fileUploadService.fileUpload().success(function(response){
            if(response.error!=0) {
                alert(response.message);
            }else{
                $scope.content.pic=response.url;
            }
        })
    }

    //$scope.cateGoryList=[{"id":1,"name":'test0'},{"id":1,"name":'test1'},{"id":1,"name":'test2'}];
    /**
     * 初始化广告类别下拉列表
     */
    $scope.initContentCategory=function() {
        contentCategoryService.findAll().success(function(response){
            if(response.code!=0) {
                alert('加载广告类别下拉列表异常');
            }else{
                $scope.cateGoryList=response.data;
            }
        })
    }

    $scope.save=function() {
        var methodName='save';
        if($scope.content.id!=null) {
            methodName='modify';
        }
        contentService.save(methodName,$scope.content).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.content={};
                $scope.loadPageList();
            }
        });
    }


    $scope.clearFileFrame=function() {
        $scope.imgObj={};
        $("#file").val("");
    }

    $scope.del=function(){
        contentService.del($scope.selectIds).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.loadPageList();
                $scope.selectIds=[];
            }
        })
    }

    $scope.findOneById=function(id) {
        contentService.findOneById(id).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.content=response.data;
            }
        })
    }
})