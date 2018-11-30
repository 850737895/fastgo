/**
 * Created by Administrator on 2018/11/22.
 */
app.controller('indexController',function($scope,$controller,loginService){
    $scope.showLoginName=function(){
        loginService.showLoginName().success(function (response) {
            $scope.loginName=response.data;
        })
    }

})