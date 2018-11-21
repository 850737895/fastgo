/**
 * Created by Administrator on 2018/11/21.
 */
app.controller('itemCatController',function($scope,$controller,itemCatService,tempTypeService){
    $controller('baseController',{$scope:$scope});//继承

    //列表加载
    $scope.search=function(pageNum,pageSize){
        var itemCatName='';
        if(typeof($scope.itemCatName) != "undefined") {
            itemCatName = $scope.itemCatName
        }
        itemCatService.search(pageNum,pageSize,itemCatName).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            } else{
                $scope.itemCatList = response.data.result;
            }
        })
    }

/*    //查询下一级
    $scope.searchNextLevel=function(parentId){
        //记录当前页码
        itemCatService.searchNextLevel(parentId).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            } else{
                $scope.itemCatList = response.data.result;
            }
        })
    }*/
    $scope.findByParentId=function(parentId) {
        $scope.parentId=parentId;
        itemCatService.findByParentId(parentId).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            } else{
                $scope.itemCatList = response.data;
            }
        })
    }

    $scope.grade=1;
    //记录当前页列表的上级ID
    $scope.parentId=0;

    //设置级别
    $scope.setGrade=function(value){
        $scope.grade=value;
    }

    $scope.tempTypeList={data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};

    $scope.selectList=function(itemCat) {
        if($scope.grade==1){
            $scope.entity_1=null;
            $scope.entity_2=null;
        }
        if($scope.grade==2){
            $scope.entity_1=itemCat;
            $scope.entity_2=null;
        }
        if($scope.grade==3){
            $scope.entity_2=itemCat;
        }

        $scope.findByParentId(itemCat.id);

    }
    
    $scope.initTempTypeList=function() {
        tempTypeService.initTempTypeList().success(function (response) {
            $scope.tempTypeList={data:response.data};
        })
    }
    
    //保存商品对象
    $scope.save = function() {

        var methodName = 'save';
        if($scope.itemCat.id!=null) {
            methodName = 'modify'
        }
        itemCatService.save(methodName,$scope.itemCat,$scope.parentId).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.findByParentId($scope.parentId);
            }
        })
    }
    
    $scope.findOne=function(id) {
        itemCatService.findOne(id).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.itemCat= response.data;
                $scope.itemCat.tempTypeId=  JSON.parse( $scope.itemCat.tempTypeId);
            }
        })
    }

    $scope.del=function () {
        if(confirm("你确定需要删除勾选的商品类目信息?")) {
            itemCatService.del($scope.selectIds).success(function (response) {
                if (response.code != 0) {
                    alert(response.msg);
                } else {
                    $scope.findByParentId($scope.parentId);
                    $scope.selectIds = [];
                }
            })
        }
    }
    
})
