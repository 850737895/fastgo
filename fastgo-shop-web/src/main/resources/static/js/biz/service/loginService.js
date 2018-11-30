/**
 * Created by Administrator on 2018/11/22.
 */
app.service('loginService',function($http){
    var url = "http://localhost:9101";

    this.showLoginName=function() {
        return $http.get(url+"/login/getLoginName");
    }
})
