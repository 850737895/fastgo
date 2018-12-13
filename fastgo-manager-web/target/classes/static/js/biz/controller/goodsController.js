/**
 * Created by Administrator on 2018/11/29.
 */
app.controller('goodsController',function ($scope,$controller,$location,goodsService,itemCatService,tempTypeService) {

    $controller('baseController',{$scope:$scope});//继承

    $scope.searchGoods={};

    $scope.itemCatListValue=[];

    $scope.goodsVo={goods:{},itemList:{},goodsDesc:{itemImages:[],specificationItems:[]}};


    $scope.goodsStatus=['未申请','申请中','审核通过','已驳回'];

    $scope.findAllItemCatInit=function () {
        itemCatService.findAll().success(function (resposne) {
            if(resposne.code!=0) {
                alert(resposne.msg);
            }else{
                for(var index=0;index<resposne.data.length;index++) {
                    $scope.itemCatListValue[resposne.data[index].id]=resposne.data[index].name;
                }
            }
        })
    }

    //分页以及查找
    $scope.search=function(pageNum,pageSize){

        goodsService.findList4Page(pageNum,pageSize,$scope.searchGoods).success(function(response){
            if(response.code!=0) {
                alert(response.msg);
            }else{
                //当前页的数据
                $scope.goodsList = response.data.result;
                //修改总条数
                $scope.paginationConf.totalItems=response.data.total;
            }
        })

    }

    //审核通过/不通过
    $scope.aduitPass=function(status) {
        goodsService.aduitPass($scope.selectIds,status).success(function(response) {

            var sumbitFlag = true;
            for(var index=0;index<$scope.selectIds.length;index++) {
                sumbitFlag=checkSumbitAduitStatus($scope.selectIds[index],$scope.goodsList);
                if(!sumbitFlag) {
                    break;
                }
            }
            if(!sumbitFlag){
                alert("商品审核或者的数据只能是【申请中】状态的数据");
                return ;
            }

            if(response.code!=0) {
                alert(response.msg);
            }else{
                $scope.loadPageList();
                $scope.selectIds=[];
            }
        });
    }

    //检测提交审核的状态只能为为（未申请的状态）
    checkSumbitAduitStatus=function(goodsId,goodsList){

        for(var index=0;index<goodsList.length;index++) {
            if(goodsId==goodsList[index]['id']){
                if(goodsList[index]['auditStatus']=='1'){
                    return true;
                }
            }else{
                continue;
            }
        }
        return false;

    }

    //查询商品vo 信息加载到编辑页面
    $scope.findOne=function() {
        var goodsId = $location.search()['id'];
        if(goodsId==null) {
            return null;
        }
        goodsService.findOne(goodsId).success(function(response) {
            $scope.goodsVo=response.data;
            editor.html($scope.goodsVo.goodsDesc.introduction );//商品介绍
            //商品图片
            $scope.goodsVo.goodsDesc.itemImages=JSON.parse($scope.goodsVo.goodsDesc.itemImages);
            //扩展属性
            $scope.goodsVo.goodsDesc.customAttributeItems=JSON.parse($scope.goodsVo.goodsDesc.customAttributeItems);
            //规格选择
            $scope.goodsVo.goodsDesc.specificationItems= JSON.parse($scope.goodsVo.goodsDesc.specificationItems);
            //转换sku列表中的规格对象
            $scope.goodsVo.itemList=JSON.parse( $scope.goodsVo.itemList);
            for(var i=0;i< $scope.goodsVo.itemList.length;i++ ){
                $scope.goodsVo.itemList[i].spec=  JSON.parse($scope.goodsVo.itemList[i].spec);
            }
        })
    }

    //查询一级分类
    $scope.qryItemCat1Level=function(parentId){
        itemCatService.findByParentId(parentId).success(function(response){
            if(response.code!==0) {
                alert('初始化商品一级分类出错');
            }else {
                $scope.itemCat1LevelList = response.data;
            }
        })
    }

    $scope.$watch('goodsVo.goods.category1Id',function(newValue,oldValue){
        if(typeof($scope.goodsVo.goods.category1Id) == "undefined") {
            return;
        }
        itemCatService.findByParentId(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品二级分类出错');
            }else {
                $scope.itemCat1Leve2List = response.data;
            }
        })
    })

    $scope.$watch('goodsVo.goods.category2Id',function(newValue,oldValue) {
        if (typeof(newValue) == "undefined") {
            return;
        }
        itemCatService.findByParentId(newValue).success(function (response) {
            if (response.code !== 0) {
                alert('初始化商品三级分类出错');
            } else {
                $scope.itemCat1Leve3List = response.data;
            }
        })
    })

    $scope.typeTemplate={};
    $scope.$watch('goodsVo.goods.typeTemplateId',function(newValue,oldValue){
        if(typeof(newValue) == "undefined") {
            return;
        }
        tempTypeService.findOne(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品品牌出错');
            }else {
                $scope.typeTemplate.brandIds=JSON.parse(response.data.brandIds);
                if($location.search()['id']==null) {
                    $scope.goodsVo.goodsDesc.customAttributeItems=JSON.parse(response.data.customAttributeItems);
                }
            }
        });

        tempTypeService.findSpecListByTempTypeId(newValue).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else {
                $scope.specList = response.data;
            }
        })
    })

    //判断规格与规格选项是否应该被勾选
    $scope.checkAttributeValue=function(specName,optionName){
        var items= $scope.goodsVo.goodsDesc.specificationItems;
        var object =$scope.judgeSpecInSpecList( items,'attributeName', specName);

        if(object!=null){
            if(object.attributeValue.indexOf(optionName)>=0){//如果能够查询到规格选项
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    $scope.generatorItemList=function() {
        //初始化itemList
        $scope.goodsVo.itemList=[{'spec':{},'price':0,'num':9999,'status':0,'isDefault':0}];

        var specList = $scope.goodsVo.goodsDesc.specificationItems;
        for(var index=0;index<specList.length;index++) {
            $scope.goodsVo.itemList = addTableColumn($scope.goodsVo.itemList,specList[index].attributeName,specList[index].attributeValue);
        }
    }

    addTableColumn=function(itemList,columnName,columnValue) {
        var newItemList=[]
        for(var i=0;i<itemList.length;i++) {
            var oldItem = itemList[i];
            for(var j=0;j<columnValue.length;j++) {
                var newItem = JSON.parse(JSON.stringify(oldItem));
                newItem.spec[columnName]=columnValue[j];
                newItemList.push(newItem);
            }
        }
        return newItemList;
    }

})


