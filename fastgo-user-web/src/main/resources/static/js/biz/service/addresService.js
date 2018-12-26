/**
 * Created by 85073 on 2018/12/26.
 */
app.service("addresService",function($http){

    var url = "http://localhost:9105";

    this.initProvinceList=function() {
        return $http.get(url+"/address/initProvince");
    }
})