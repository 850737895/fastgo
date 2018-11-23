/**
 * Created by Administrator on 2018/11/23.
 */
app.controller('sellerController',function ($scope,sellerService) {

    $scope.checkSellerId=function(checkType,checkValue){
        sellerService.checkValidate(checkType,checkValue).success(function (response) {
            if(response.code==0){
                $scope.sellerIdRet='用户名验证通过';
                $("#sellerId").css("color","green");
                $("#sellerId").css("font-weight","bold");
            }else{
                $scope.sellerIdRet='用户名已存在';
            }
        })
    }

    $scope.checkNickName=function(checkType,checkValue){
        sellerService.checkValidate(checkType,checkValue).success(function (response) {
            if(response.code==0){
                $scope.nickNameRet='店铺名称验证通过';
                $("#nickName").css("color","green");
                $("#nickName").css("font-weight","bold");
            }else{
                $scope.nickNameRet='店铺名称已存在';
            }
        })
    }
})
