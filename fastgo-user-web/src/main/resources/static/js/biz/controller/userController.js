/**
 * Created by Administrator on 2018/12/20.
 */
app.controller("userController",function($scope,$controller,userService){
    $controller('baseController', {$scope: $scope});//继承

    var checkFormResult = true;

    $scope.regUserEntity={};

    /**
     * 校验用户名
     * @param username
     */
    $scope.checkUserName=function(checkType,checkValue) {
        userService.checkValidate(checkType,checkValue).success(function(response){
            if(response.code!=0) {
                $scope.usernameRet='用户名已存在';
                $("#username").css("color","red");
                $("#username").css("font-weight","bold");
            }else{
                $scope.usernameRet='用户名验证通过';
                $("#username").css("color","green");
                $("#username").css("font-weight","bold");
                checkFormResult = true;
            }
        })
    }

    $scope.checkMobile=function(checkType,checkValue) {
        userService.checkValidate(checkType,checkValue).success(function(response){
            if(response.code!=0) {
                $scope.mobileRet='手机号已被注册';
                $("#mobile").css("color","red");
                $("#mobile").css("font-weight","bold");
            }else{
                $scope.mobileRet='手机验证通过';
                $("#mobile").css("color","green");
                $("#mobile").css("font-weight","bold");
                checkFormResult = true;
            }
        })
    }

    $scope.checkPassword=function() {
        if($scope.regUserEntity.password!=$scope.regUserEntity.repassword) {
            $scope.repasswordRet='二次密码不一致';
            $("#repassword").css("color","red");
            $("#repassword").css("font-weight","bold");
            checkFormResult = false;
        }else{
            $scope.repasswordRet='';
            checkFormResult = true;
        }
    }

    /**
     * 生成短信验证码
     */
    $scope.genSmsCode=function(){
        userService.genSmsCode($scope.regUserEntity.phone).success(function (response) {
            if(response.code==0) {
                alert("短信验证码发送到您的手机号上,请注意查收");
            }else{
                alert("获取短信验证码失败");
            }
        })
    }

    $scope.register=function() {
        userService.register($scope.regUserEntity).success(function (response) {
            if(response.code!=0) {
                alert("用户注册失败");
            }else{
                alert("注册成功");
            }
        })
    }
})
