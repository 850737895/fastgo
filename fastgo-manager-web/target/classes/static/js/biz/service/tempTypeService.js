/**
 * Created by Administrator on 2018/11/20.
 */
app.service('tempTypeService',function($http){
    var url = "http://localhost:9100";

    this.listPage=function(pageNum,pageSize,qryCondition) {
        return $http.get(url+"/templateType/list?pageNum="+pageNum+"&pageSize="+pageSize+"&qryCondition="+qryCondition);
    }
})
