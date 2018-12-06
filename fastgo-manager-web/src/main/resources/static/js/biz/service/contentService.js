/**
 * Created by Administrator on 2018/12/5.
 */
app.service('contentService',function($http) {
    var url = "http://localhost:9100";

    this.list4Page=function(pageNum,pageSize) {
        return $http.get(url+"/content/list4Page?pageNum="+pageNum+"&pageSize="+pageSize);
    }

    this.save=function(methodName,content) {
        return $http.post(url+"/content/"+methodName,content);
    }
    this.findOneById=function(id){
        return $http.get(url+"/content/findOneById/"+id);
    }
    this.del=function(ids){
        return $http.get(url+"/content/del?ids="+ids);
    }
})
