/**
 * Created by Administrator on 2018/11/29.
 */
app.service('goodsService',function($http){

    var url = "http://localhost:9101"

    this.save=function(goodsVo){
        transGoodsVo(goodsVo);
        return $http.post(url+"/goods/save",goodsVo);
    }

    this.findList4Page=function(pageNum,pageSize,searchGoods){
        return $http.post(url+"/goods/findList4Page?pageNum="+pageNum+"&pageSize="+pageSize,searchGoods);
    }

    this.findOne=function(goodsId) {
        return $http.get(url+"/goods/findGoodsVoById/"+goodsId);
    }

    this.update=function(goodsVo) {
        transGoodsVo(goodsVo);
        return $http.post(url+"/goods/update",goodsVo);
    }

    this.sumbitAduit=function(ids) {
        return $http.get(url+"/goods/applyAduit/ids"+ids);
    }

    transGoodsVo=function(goodsVo) {
        goodsVo.goodsDesc.itemImages=JSON.stringify(goodsVo.goodsDesc.itemImages);
        goodsVo.goodsDesc.customAttributeItems=JSON.stringify(goodsVo.goodsDesc.customAttributeItems);
        goodsVo.goodsDesc.specificationItems=JSON.stringify(goodsVo.goodsDesc.specificationItems);
        goodsVo.itemList=JSON.stringify(goodsVo.itemList);
    }
})