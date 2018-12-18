/**
 * Created by 85073 on 2018/12/9.
 */
app.service('itemSearchService',function ($http) {
    var url = "http://localhost:9103";
    this.keyworkdSearch=function(search){
        return $http.post(url+"/tbItem/searchList",search);
    }
    this.toDetailPage=function(goodsId) {
        return $http.get(url+"/tbItem/detailHtml/"+goodsId);
    }
})