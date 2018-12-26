/**
 * Created by Administrator on 2018/12/25.
 */
app.controller("cartController",function($scope,$controller,$location,cartService){
    $controller('baseController', {$scope: $scope});//继承

    /**
     * 加载购物车列表
     */
    $scope.loadCartList=function() {
        cartService.loadCartList().success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.cartList=response.data;
                $scope.initAllOrderItemList($scope.cartList);

            }
        })
    }

    $scope.addCartItemNum=function(skuId,num) {
        cartService.addCartItemNum(skuId,num).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.cartList=response.data;
                $scope.initAllOrderItemList($scope.cartList);
                calTotalMoney($scope.selected,$scope.content);

            }
        })
    }

    $scope.cutCartItemNum=function(skuId,num) {
        cartService.addCartItemNum(skuId,num).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.cartList=response.data;
                //统计金额
                $scope.initAllOrderItemList($scope.cartList);
                calTotalMoney($scope.selected,$scope.content);

            }
        })
    }

    $scope.totalChoose=0;



    /**
     * 计算勾选的商品价格
     * @param skuIds
     * @param orderItemList
     */
    calTotalMoney=function(skuIds,orderItemList) {
        $scope.totalMoney=0.00;
        for(var i=0;i<skuIds.length;i++) {
            for(var j=0;j<orderItemList.length;j++) {
                if(skuIds[i]==orderItemList[j].itemId) {
                    $scope.totalMoney= $scope.totalMoney+orderItemList[j].totalFee;
                }
            }
        }
        //保留二位小数
        $scope.totalMoney = $scope.totalMoney.toFixed(2);
    }


    /**
     * 统计购物车总金额
     * @param cartList
     */
    $scope.initAllOrderItemList=function(cartList) {
        $scope.content=[];
        var cartListJson = JSON.parse(JSON.stringify(cartList));
        for(var index=0;index<cartList.length;index++) {
            var orderItemList = JSON.parse(JSON.stringify(cartListJson[index].orderItemList));
            for(var i=0;i<orderItemList.length;i++) {
                $scope.content.push(orderItemList[i]);
            }
        }

    }


    $scope.content=[];


    /**
     * 选中的购物车列表
     * @type {[*]}
     */
    $scope.selected = [];
    //动作 和 id号 如果是add则把id号放入 selected数组中
    var updateSelected = function (action, id) {
        if (action == 'add' && $scope.selected.indexOf(id) == -1) $scope.selected.push(id);
        if (action == 'remove' && $scope.selected.indexOf(id) != -1) $scope.selected.splice($scope.selected.indexOf(id), 1);
        calTotalMoney($scope.selected,$scope.content);
        $scope.totalChoose=$scope.selected.length;
    };
    //更新某一列数据的选择
    $scope.updateSelection = function ($event, id) {
        console.log( id );
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        updateSelected(action, id);
    };

    //全选操作
    $scope.selectAll = function ($event) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        // 遍历这个 content 把所有的元素从selected中添加或删除
        for (var i = 0; i < $scope.content.length; i++) {
            var contact = $scope.content[i];
            updateSelected(action, contact.itemId);
        }
    };
    $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
    };
    //如果数据长度 == 全选的
    $scope.isSelectedAll = function () {
        return $scope.selected.length == $scope.content.length;
    };

    /**
     * 跳转到结算页
     */
    $scope.toSettlementPage=function() {
        window.open("getOrderInfo.html#?skuIds="+$scope.selected);
    }

    /**
     * 结算页加载勾选的购物车列表
     */
    $scope.loadSelectCartList=function(){
        var skuIds = $location.search()['skuIds'];
        alert(skuIds)
        cartService.loadSelectCartList(skuIds).success(function (resposne) {
            if(resposne.code!=0) {
                alert(resposne.msg);
            }else{
                $scope.selectedCartList=resposne.data;
            }
        })
    }

})
