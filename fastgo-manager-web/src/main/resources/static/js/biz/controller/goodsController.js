/**
 * Created by Administrator on 2018/11/29.
 */
app.controller('goodsController',function ($scope,$controller,goodsService,itemCatService) {

    $controller('baseController',{$scope:$scope});//继承

    $scope.searchGoods={};

    $scope.itemCatListValue=[];

    $scope.goodsStatus=['未申请','申请中','审核通过','已驳回'];

    $scope.findAllItemCatInit=function () {
        itemCatService.findAll().success(function (resposne) {
            if(resposne.code!=0) {
                alert(resposne.msg);
            }else{
                for(var index=0;index<resposne.data.length;index++) {
                    $scope.itemCatListValue[resposne.data[index].id]=resposne.data[index].name;
                }
            }
        })
    }

    //分页以及查找
    $scope.search=function(pageNum,pageSize){

        goodsService.findList4Page(pageNum,pageSize,$scope.searchGoods).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                //当前页的数据
                $scope.goodsList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems=response.data.total;
            }
        })

    }

    //审核通过/不通过
    $scope.aduitPass=function(status) {
        goodsService.aduitPass($scope.selectIds,status).success(function(response) {

            var sumbitFlag = true;
            for(var index=0;index<$scope.selectIds.length;index++) {
                sumbitFlag=checkSumbitAduitStatus($scope.selectIds[index],$scope.goodsList);
                if(!sumbitFlag) {
                    break;
                }
            }
            if(!sumbitFlag){
                alert("商品审核或者的数据只能是【申请中】状态的数据");
                return ;
            }

            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.loadPageList();
                $scope.selectIds=[];
            }
        });
    }

    //检测提交审核的状态只能为为（未申请的状态）
    checkSumbitAduitStatus=function(goodsId,goodsList){

        for(var index=0;index<goodsList.length;index++) {
            if(goodsId==goodsList[index]['id']){
                if(goodsList[index]['auditStatus']=='1'){
                    return true;
                }
            }else{
                continue;
            }
        }
        return false;

    }


})