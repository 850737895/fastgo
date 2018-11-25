/**
 * Created by Administrator on 2018/11/23.
 */
app.controller('sellerController',function ($scope,sellerService) {

    var checkFormResult = true;

    $scope.checkSellerId=function(checkType,checkValue){
        sellerService.checkValidate(checkType,checkValue).success(function (response) {
            if(response.code==0){
                $scope.sellerIdRet='用户名验证通过';
                $("#sellerId").css("color","green");
                $("#sellerId").css("font-weight","bold");
                checkFormResult = true;
            }else{
                $scope.sellerIdRet='用户名已存在';
                checkFormResult = false;
            }
        })
    }

    $scope.checkNickName=function(checkType,checkValue){
        sellerService.checkValidate(checkType,checkValue).success(function (response) {
            if(response.code==0){
                $scope.nickNameRet='店铺名称验证通过';
                $("#nickName").css("color","green");
                $("#nickName").css("font-weight","bold");
                checkFormResult = true;
            }else{
                $scope.nickNameRet='店铺名称已存在';
                checkFormResult = false;
            }
        })
    }

    $scope.checkLinkmanMobile=function(checkType,checkValue){
        sellerService.checkValidate(checkType,checkValue).success(function (response) {
            if(response.code==0){
                $scope.linkmanMobileRet='联系人手机验证通过';
                $("#linkmanMobile").css("color","green");
                $("#linkmanMobile").css("font-weight","bold");
                checkFormResult = true;
            }else{
                $scope.linkmanMobileRet='联系人手机已存在';
                checkFormResult = false;
            }
        })
    }

    //用户注册
    $scope.register = function() {
        if(!checkFormResult) {
            alert("表单校验不通过，请检查您的填写数据");
            return ;
        }

        sellerService.register($scope.seller).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                alert("注册成功,请登录平台进行帐号审核");
                window.location.href="shoplogin.html"
            }
        })

    }



})
