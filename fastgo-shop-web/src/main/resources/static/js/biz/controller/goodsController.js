/**
 * Created by Administrator on 2018/11/29.
 */
app.controller('goodsController',function ($scope,goodsService,fileUploadService,itemCatService,tempTypeService) {

    //新增商品
    $scope.save=function(){
        $scope.goodsVo.goodsDesc.introduction=$("#introduction").val();
        goodsService.save($scope.goodsVo).success(function (response) {
            if(response.code!=0) {
                alert(response.msg);
            }else{
                alert(response.msg);
                $scope.goodsVo={};
                KindEditor.html("#introduction","");
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

    $scope.goodsVo={goods:{},goodsDesc:{itemImages:[],specificationItems:[]}};

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
        itemCatService.findByParentId(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品二级分类出错');
            }else {
                $scope.itemCat1Leve2List = response.data;
            }
        })
    })

    $scope.$watch('goodsVo.goods.category2Id',function(newValue,oldValue){
        itemCatService.findByParentId(newValue).success(function(response){
            if(response.code!==0) {
                alert('初始化商品三级分类出错');
            }else {
                $scope.itemCat1Leve3List = response.data;
            }
        })
    })

    $scope.$watch('goodsVo.goods.category3Id',function(newValue,oldValue){
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

    //定义规格选项结构
    $scope.specItem={attributeName:{},attributeValue:[]}

    $scope.addSpecOps=function($event,name,value) {
        if($event.target.checked) { //勾选

            //看规格对象是否添加到集合中
            var specificationItem = $scope.judgeSpecInSpecList($scope.goodsVo.goodsDesc.specificationItems,'attributeName',value);

            if(specificationItem!=null) {//添加过
                specificationItem.attributeValue.push(value);
                $scope.goodsVo.goodsDesc.specificationItems.push(specificationItem);

            }else{
                $scope.specItem.attributeName=name;
                $scope.specItem.attributeValue.push(value);
                $scope.goodsVo.goodsDesc.specificationItems.push($scope.specItem);
                $scope.specItem={attributeName:{},attributeValue:[]}
            }

        }else{//反选

        }
    }

    $scope.judgeSpecInSpecList=function(list,key,keyValue){
        for(var index=0;list.length;index++) {
            if(list[index][key]==keyValue) {
                return list[index];
            }
        }
        return null;
    }

})