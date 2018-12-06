/**
 * Created by Administrator on 2018/11/20.
 */
app.service('tempTypeService',function($http){
    var url = "http://localhost:9100";

    this.listPage=function(pageNum,pageSize,qryCondition) {
        return $http.get(url+"/templateType/list?pageNum="+pageNum+"&pageSize="+pageSize+"&qryCondition="+qryCondition);
    }

    this.save = function(methodName,tempType) {
       return $http.post(url+"/templateType/"+methodName,tempType);
    }

    this.del = function (ids) {
        return $http.get(url+"/templateType/del?ids="+ids);
    }

    this.findOne = function(id) {
        return $http.get(url+"/templateType/findOne/"+id);
    }

    this.initTempTypeList=function() {
        return $http.get(url+"/templateType/initTempTypeList");
    }

    this.findSpecListByTempTypeId=function(tempTypeId) {
        return $http.get(url+"/goods/specOpsList/"+tempTypeId);
    }
})
