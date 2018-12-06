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
})
