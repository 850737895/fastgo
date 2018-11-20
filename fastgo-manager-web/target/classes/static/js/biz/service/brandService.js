//品牌管理服务层
//定义服务层
app.service('brandService',function($http){

    var url = "http://localhost:9100";

    //列表服务
    this.findList = function() {
        return $http.get(url+"/brand/list");
    }

    this.initBrandList = function() {
        return $http.get(url+"/brand/initBrandList");
    }

    //分页列表服务
    this.findListByPage = function (pageNum,pageSize) {
        return $http.get(url+"/brand/pageList?pageNum="+pageNum+"&pageSize="+pageSize);
    }

    //保存服务
    this.save = function (methodName,brand) {
        return $http.post(url+"/brand/"+methodName,brand)
    }

    //根据id查询单个对象
    this.findOneById =function (id) {
        return $http.get(url+"/brand/findOne/"+id);
    }

    //删除服务
    this.del =function (ids) {
        return $http.get(url+"/brand/del?ids=" + ids);
    }

    //搜索服务
    this.search = function (pageNum,pageSize,searchBrand) {
        return $http.post(url+"/brand/search?pageNum="+pageNum+"&pageSize="+pageSize,searchBrand);
    }

});
