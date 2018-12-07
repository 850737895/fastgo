/**
 * Created by Administrator on 2018/12/7.
 */
app.service('contentService',function ($http) {
    var url = "http://localhost:9102";
    this.findListByCategoryId=function(categoryId){
        return $http.get(url+"/content/findListByCategoryId/"+categoryId);
    }
})
