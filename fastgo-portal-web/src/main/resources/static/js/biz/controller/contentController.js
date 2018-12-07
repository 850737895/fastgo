/**
 * Created by Administrator on 2018/12/7.
 */
app.controller('contentController',function($scope,$controller,contentService){
    $controller('baseController', {$scope: $scope});//继承

    $scope.contentList=[];

    $scope.findListByCategoryId=function(categoryId){
        contentService.findListByCategoryId(categoryId).success(function (response) {
            if(response.code!=0) {
                alert("初始化首页轮播图失败")
            }else{
                $scope.contentList[categoryId]=response.data;
            }
        })
    }
})
