/**
 * Created by Administrator on 2018/11/29.
 */
app.service('goodsService',function($http){

    var url = "http://localhost:9101"

    this.save=function(goodsVo){
        goodsVo.goodsDesc.itemImages=JSON.stringify(goodsVo.goodsDesc.itemImages);
        goodsVo.goodsDesc.customAttributeItems=JSON.stringify(goodsVo.goodsDesc.customAttributeItems);
        goodsVo.goodsDesc.specificationItems=JSON.stringify(goodsVo.goodsDesc.specificationItems);
        goodsVo.itemList=JSON.stringify(goodsVo.itemList);
        return $http.post(url+"/goods/save",goodsVo);
    }
})