/**
 * Created by Administrator on 2018/11/29.
 */
app.controller('goodsController',function ($scope,$controller,goodsService,fileUploadService,itemCatService,tempTypeService) {

    $controller('baseController',{$scope:$scope});//继承

    //新增商品
    $scope.save=function(){
        $scope.goodsVo.goodsDesc.introduction=editor.html();
        goodsService.save($scope.goodsVo).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                alert(response.msg);
                $scope.goodsVo={goods:{},itemList:{},goodsDesc:{itemImages:[],specificationItems:[]}};
                $scope.itemCat1Leve2List={};
                $scope.itemCat1Leve3List={};
                $scope.specList={};
                editor.html("");
            }
        })
    }
    $scope.uploadFile=function () {
        fileUploadService.fileUpload().success(function(response){
            if(response.error!=0) {
                alert(response.message);
            }else{
                $scope.imgObj.url=response.url;
            }
        })
    }

    $scope.clearFileFrame=function() {
        $scope.imgObj={};
        $("#file").val("");
    }

    $scope.goodsVo={goods:{},itemList:{},goodsDesc:{itemImages:[],specificationItems:[]}};

    $scope.add_image_entity=function() {
        $scope.goodsVo.goodsDesc.itemImages.push($scope.imgObj);
    }

    //删除文件
    $scope.delFile=function(index) {
        var itemImage = $scope.goodsVo.goodsDesc.itemImages[index];

        fileUploadService.delFile(itemImage.url).success(function (response) {
            if(response.code!=0){
                alert(response.msg);
            }else {
                //先删除列表
                $scope.goodsVo.goodsDesc.itemImages.splice(index,1);

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

    $scope.$watch('goodsVo.goods.category2Id',function(newValue,oldValue){
        if(typeof(newValue) == "undefined") {
            return;
        }
        itemCatService.findByParentId(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品三级分类出错');
            }else {
                $scope.itemCat1Leve3List = response.data;
            }
        })
    })

    $scope.$watch('goodsVo.goods.category3Id',function(newValue,oldValue){
        if(typeof(newValue) == "undefined") {
            return;
        }
        itemCatService.findOne(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品三级分类模版出错');
            }else {
                var tempTypeJson = eval('(' + response.data.tempTypeId + ')');
                $scope.goodsVo.goods.typeTemplateId= tempTypeJson.id;
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
                $scope.goodsVo.goodsDesc.customAttributeItems=JSON.parse(response.data.customAttributeItems);
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

    //添加规格选项
    $scope.addSpecOps=function($event,name,value) {
        //判断规格选项是否添加过
        var specItem = $scope.judgeSpecInSpecList($scope.goodsVo.goodsDesc.specificationItems,'attributeName',name);
        if(specItem!=null) {//添加过
            if($event.target.checked) { //添加
                specItem.attributeValue.push(value);
            }else {//清除
                specItem.attributeValue.splice(specItem.attributeValue.indexOf(value),1);
                if(specItem.attributeValue.length==0) {
                    $scope.goodsVo.goodsDesc.specificationItems.splice($scope.goodsVo.goodsDesc.specificationItems.indexOf(specItem),1);
                }
            }

        }else{//没有添加
            $scope.goodsVo.goodsDesc.specificationItems.push({'attributeName':name,'attributeValue':[value]})
        }
    }

    //[{"attributeName":"网络","attributeValue":["移动3G","移动4G"]},{"attributeName":"机身内存","attributeValue":["16G","32G"]}]
    //生成规格选项列表
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

    $scope.searchGoods={};

    $scope.goodsStatus=['未申请','申请中','审核通过','已驳回'];

    //分页以及查找
    $scope.search=function(pageNum,pageSize){
        if(typeof($scope.searchGoods.auditStatus) == "undefined") {
            $scope.searchGoods.auditStatus='';
        }
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

})