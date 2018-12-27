/**
 * Created by Administrator on 2018/12/20.
 */
app.controller("userController",function($scope,$controller,userService,addresService){
    $controller('baseController', {$scope: $scope});//继承

    var checkFormResult = true;

    $scope.regUserEntity={};

    $scope.address={'id':'','contact':'','provinceId':'','cityId':'','townId':'','mobile':'','address':'','alias':'','isDefault':''};

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
        if(checkFormResult) {
            userService.register($scope.regUserEntity).success(function (response) {
                if (response.code != 0) {
                    alert("用户注册失败");
                } else {
                    alert("注册成功");
                }
            })
        }else{
            alert("表单校验不通过");
            return;
        }
    }

    $scope.loginUserName='';

    $scope.showLoginUser=function(){
        userService.showLoginUser().success(function (response) {
            if(response.code!=0) {
                alert("获取登录用户名失败");
            }else{
                $scope.loginUserName = response.data;
            }
        })
    }

    /**
     * 初始化加載省份列表
     */
    $scope.initProvinceList=function(){
        addresService.initProvinceList().success(function (response) {
            if(response.code!=0) {
                alert("初始化省份列表出错");
            }else{
                $scope.provinceList = response.data;
            }
        })
    }

    $scope.$watch('address.provinceId',function(newValue,oldValue){
        if(typeof(newValue) == "undefined"  ||newValue=="") {
            return;
        }else{
            addresService.initCityList(newValue).success(function (response) {
                if(response.code!=0) {
                    alert(response.msg);
                }else{
                    $scope.cityList = response.data;
                }
            })
        }
    })

    $scope.$watch('address.cityId',function(newValue,oldValue){
        if(typeof(newValue) == "undefined"  ||newValue=="") {
            return;
        }else{
            addresService.initAreaList(newValue).success(function (response) {
                if(response.code!=0) {
                    alert(response.msg);
                }else{
                    $scope.areaList = response.data;
                }
            })
        }
    })

    $scope.saveAddress=function() {
        var methodName = 'saveAddress'
        if($scope.address.id!=null) {
            methodName = 'modifyAddress';
        }
        addresService.saveAddress(methodName,$scope.address).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.selectAddressList();
            }
        })
    }
    
    $scope.selectAddressList=function(){
        addresService.selectAddressList().success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.addressList = response.data;
                $scope.address={};
            }
        })
    }
    
    $scope.deleteAddressById=function (id) {
        addresService.deleteAddressById(id).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.selectAddressList();
            }
        })
    }
    
    $scope.findOne=function(id) {
        addresService.findOne(id).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.address=response.data;
            }
        })
    }
})
