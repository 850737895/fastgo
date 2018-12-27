/**
 * Created by 85073 on 2018/12/26.
 */
app.service("addresService",function($http){

    var url = "http://localhost:9105";

    this.initProvinceList=function() {
        return $http.get(url+"/address/initProvince");
    }

    this.initCityList=function (provinceId) {
        return $http.get(url+"/address/selectCityListByProvinceId?provinceId="+provinceId)
    }

    this.initAreaList=function (cityId) {
        return $http.get(url+"/address/selectAreaListByCityId?cityId="+cityId);
    }

    this.saveAddress=function(address) {
        return $http.post(url+"/address/saveAddress",address);
    }
    this.selectAddressList=function() {
        return $http.get(url+"/address/selectAddressList");
    }
})