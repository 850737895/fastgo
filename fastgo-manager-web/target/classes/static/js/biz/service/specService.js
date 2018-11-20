/**
 * Created by Administrator on 2018/11/16.
 */
app.service('specService',function ($http) {
    var url = "http://localhost:9100";
    //条件查询列表服务
    this.search=function(pageNum,pageSize,queryCondition){
        return $http.get(url+"/spec/list?pageNum="+pageNum+"&pageSize="+pageSize+"&queryCondition="+queryCondition);
    }


    this.save = function(spec) {
        return $http.post(url+"/spec/save",spec);
    }

    this.findOne = function(specId) {
        return $http.post(url+"/spec/findOne",specId);
    }

    this.initSpecList = function(){
        return $http.get(url+"/spec/initSpecList");
    }
})
