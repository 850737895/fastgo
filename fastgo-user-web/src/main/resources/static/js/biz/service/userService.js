/**
 * Created by Administrator on 2018/12/20.
 */
app.service("userService",function($http){

    var url="http://localhost:9105";

    this.checkValidate=function (checkType,checkValue) {
        return $http.get(url+"/user/checkValidate/"+checkType+"/"+checkValue);
    }

    this.genSmsCode=function(phone) {
        return $http.get(url+"/user/genSmsCode/"+phone);
    }
    this.register=function(regUserEntity){
        return $http.post(url+"/user/register",regUserEntity);
    }
    this.showLoginUser=function () {
        return $http.get(url+"/user/showLoginUser");
    }
})