app.controller('baseController',function ($scope) {

    //分页对象
    $scope.paginationConf= {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.loadPageList();//重新加载 这个方法会重复调用两次
        }
    };

    //操作列表复选
    $scope.selectIds=[];//选中的ID集合
    $scope.updateSelection = function($event, id) {
        if($event.target.checked){//如果是被选中,则增加到数组
            $scope.selectIds.push( id);
        }else{
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除
        }
    }

    //加载分页列表
    $scope.loadPageList=function () {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }

    $scope.json2String=function(jsonStr,key) {
        var jsonObj =  JSON.parse(jsonStr);
        var retValue = "";
        for(var index=0;index<jsonObj.length;index++) {
            if(index>0) {
                retValue+=","
            }
            retValue+=jsonObj[index][key];

        }
        return retValue;
    }

    $scope.judgeSpecInSpecList=function(list,key,keyValue){
        for(var index=0;index<list.length;index++) {
            if(list[index][key]==keyValue) {
                return list[index];
            }
        }
        return null;
    }

})