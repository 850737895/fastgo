/**
 * Created by Administrator on 2018/11/29.
 */
app.service('goodsService',function($http){

    var url = "http://localhost:9100";

    this.findList4Page=function(pageNum,pageSize,searchGoods){
        return $http.post(url+"/goods/findList4Page?pageNum="+pageNum+"&pageSize="+pageSize,searchGoods);
    }

    this.aduitPass=function(ids,status) {
        return $http.get(url+"/goods/aduitPass?ids="+ids+"&status="+status);
    }

    this.findOne=function(goodsId) {
        return $http.get(url+"/goods/findGoodsVoById/"+goodsId);
    }

})