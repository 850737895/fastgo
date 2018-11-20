/**
 * Created by Administrator on 2018/11/16.
 */
app.service('specService',function ($http) {
    var url = "http://localhost:9100";
    //条件查询列表服务
    this.search=function(pageNum,pageSize,queryCondition){
        return $http.get(url+"/spec/list?pageNum="+pageNum+"&pageSize="+pageSize+"&queryCondition="+queryCondition);
    }


    this.save = function(methodName,spec) {
        return $http.post(url+"/spec/"+methodName,spec);
    }

    this.findOne = function(specId) {
        return $http.post(url+"/spec/findOne/"+specId);
    }

    this.del = function (ids) {
        return $http.get(url+"/spec/delSpecBySpecId?specIds="+ids);
    }

    this.findOne = function(specId) {
        return $http.post(url+"/spec/findOne",specId);
    }

    this.initSpecList = function(){
        return $http.get(url+"/spec/initSpecList");
    }
})
