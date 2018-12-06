/**
 * Created by Administrator on 2018/12/5.
 */
app.service('contentCategoryService',function($http){
    var url="http://localhost:9100";

    this.list4PageByCondition=function(pageNum,pageSize,categoryName){
        return $http.get(url+"/contentCategory/list4Page?pageNum="+pageNum+"&pageSize="+pageSize+"&contentCategoryName="+categoryName);
    }

    this.findAll=function() {
        return $http.get(url+"/contentCategory/findAll");
    }

    this.saveOrModify=function(methodName,contentCategory) {
        return $http.post(url+"/contentCategory/"+methodName,contentCategory);
    }

    this.del=function(ids) {
        return $http.get(url+"/contentCategory/del?ids="+ids);
    }

    this.findOneById=function(id){
        return $http.get(url+"/contentCategory/findOneById/"+id);
    }

})