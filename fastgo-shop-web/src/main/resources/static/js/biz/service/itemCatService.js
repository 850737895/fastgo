/**
 * Created by Administrator on 2018/11/21.
 */
app.service('itemCatService',function ($http) {

    var url = "http://localhost:9101";

    /**
     * 分页查询商品列表层级
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @param parentId 父类id
     * @param qryCondition 查询条件
     */
    this.search=function(pageNum,pageSize,qryCondition){
        return $http.get(url+"/itemCat/level?pageNum="+pageNum+"&pageSize="+pageSize+"&qryCondition="+qryCondition);
    }
/*    this.searchNextLevel=function(parentId) {
        return $http.get(url+"/itemCat/level?parentId="+parentId);
    }*/

    this.findByParentId=function(parentId) {
        return $http.get(url+"/itemCat/findByParentId?parentId="+parentId);
    }

    this.save=function(methodName,itemCat,parentId) {
        if(methodName=='save') {
            return $http.post(url+"/itemCat/save?parentId="+parentId,itemCat);
        }else {
            return $http.post(url+"/itemCat/modify/",itemCat);
        }

    }


    this.findOne=function (id) {
        return $http.get(url+"/itemCat/findOne/"+id);
    }

    this.del = function (ids) {
        return $http.get(url+"/itemCat/del?ids="+ids);
    }

})
