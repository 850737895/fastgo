/**
 * Created by Administrator on 2018/11/26.
 */
app.service('sellerService',function ($http) {
    var url = "http://localhost:9100";
    this.qrySellerList=function(pageNum,pageSize,seller) {
        return $http.post(url+"/seller/findListByPage?pageNum="+pageNum+"&pageSize="+pageSize,seller);
    }

    this.findOneById=function(sellerId) {
        return $http.post(url+"/seller/findOneById/"+sellerId);
    }

    this.updateAccountStatus(sellerId,acctStatus)
    {
        return $http.get(url + "/seller/updateAccountStatus?sellerId=" + sellerId + "&status=" + acctStatus);
    }
})
