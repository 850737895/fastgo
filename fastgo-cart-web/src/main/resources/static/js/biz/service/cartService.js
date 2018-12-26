/**
 * Created by Administrator on 2018/12/25.
 */
app.service('cartService',function($http) {

    var url = "http://localhost:9107";

    /**
     * 加载购物车
     */
    this.loadCartList=function() {
        return $http.get(url+'/cart/cartList');
    }
    /**
     * 修改购物车数据
     * @param skuId
     * @param num
     */
    this.addCartItemNum=function(skuId,num) {
        return $http.get(url+'/cart/addCartList?skuId='+skuId+"&num="+num);
    }

    this.loadSelectCartList=function(skuIds) {
        return $http.get(url+"/cart/loadSelectCartList?skuIds="+skuIds);
    }
})