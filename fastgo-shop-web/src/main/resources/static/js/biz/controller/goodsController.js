/**
 * Created by Administrator on 2018/11/29.
 */
app.controller('goodsController',function ($scope,goodsService,fileUploadService) {

    //新增商品
    $scope.save=function(){
        $scope.goodsVo.goodsDesc.introduction=$("#introduction").val();
        goodsService.save($scope.goodsVo).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                alert(response.msg);
                $scope.goodsVo={};
                KindEditor.html("#introduction","");
            }
        })
    }
    $scope.uploadFile=function () {
        fileUploadService.fileUpload().success(function(response){
            if(response.error!=0) {
                alert(response.message);
            }else{
                $scope.imgObj.url=response.url;
            }
        })
    }

    $scope.clearFileFrame=function() {
        $scope.imgObj={};
        $("#file").val("");
    }

    $scope.goodsVo={goods:{},goodsDesc:{itemImages:[]}};

    $scope.add_image_entity=function() {
        $scope.goodsVo.goodsDesc.itemImages.push($scope.imgObj);
    }

    //删除文件
    $scope.delFile=function(index) {
        var itemImages = $scope.goodsVo.goodsDesc.itemImages[index];
        //先删除列表
        $scope.goodsVo.goodsDesc.itemImages.splice(index,1);


    }
})