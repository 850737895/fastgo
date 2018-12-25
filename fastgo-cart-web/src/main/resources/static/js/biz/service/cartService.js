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
})