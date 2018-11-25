/**
 * Created by Administrator on 2018/11/23.
 */
app.service('sellerService',function($http){

    var url = "http://localhost:9101"

    this.checkValidate=function(checkType,checkValue){
        return $http.get(url+"/seller/validate/"+checkType+"/"+checkValue);
    }

    this.register=function(seller) {
        return $http.post(url+"/seller/register",seller);
    }
})