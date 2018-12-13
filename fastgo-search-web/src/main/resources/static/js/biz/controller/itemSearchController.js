/**
 * Created by 85073 on 2018/12/9.
 */
app.controller('itemSearchController',function($scope,$controller,$location,itemSearchService) {
    $controller('baseController', {$scope: $scope});//继承

    $scope.searchEntity={"keywords":'',"categoryName":'',"brand":'','spec':{},'price':'','pageNum':1,'pageSize':20,"sortSpec":'',"sortField":""};


    $scope.keyworkdSearch=function() {
        if(typeof ($scope.searchEntity)=='undefined' || $scope.searchEntity=="''") {
            alert('请输入搜索结果');
        }

        if(isNaN($scope.searchEntity.pageNum)) {
            alert("请输入正确的数字查询");
            return ;
        }
        if($scope.searchEntity.pageNum<=0) {
            $scope.searchEntity.pageNum=1;
        }
        if($scope.searchEntity.pageNum>=$scope.totalPage&&$scope.totalPage>0) {
            $scope.searchEntity.pageNum=$scope.totalPage;
        }
        $scope.searchEntity.pageNum= parseInt( $scope.searchEntity.pageNum) ;

        itemSearchService.keyworkdSearch($scope.searchEntity).success(function (response) {

            if(response.code!=0) {
                alert("搜索君开小差了，请稍后再试......");
            }else{
                $scope.itemList = response.data.rows;
                $scope.categoryNameList = response.data.groupNames;
                $scope.brandList = response.data.brandList;
                $scope.specList = response.data.specList;
                $scope.totalPage = response.data.totalPage;
                $scope.totalCount = response.data.totalCount;
                $scope.targetPage=$scope.searchEntity.pageNum;
                buildPageAble();
                $scope.searchEntity.pageNum=1;
                $scope.keywordIsBrand();
            }
        })
    }

    /**
     * 构建分页
     */
    buildPageAble=function() {
        //开始页
        var firstPage = 1;
        //结束页
        var lastPage = $scope.totalPage;
        $scope.pageLable=[];

        //前面有点
         $scope.firstDot = true;
        //后面有点
        $scope.lastDot = true;
        if($scope.totalPage>5) {

            if($scope.searchEntity.pageNum<3) {//当前页小于等于三
                lastPage = 5;
                $scope.firstDot= false;
            }else if($scope.searchEntity.pageNum>=$scope.totalPage-2) {
                firstPage= $scope.totalPage-4;
                $scope.lastDot = false;
            }else {
                firstPage = $scope.searchEntity.pageNum-2;
                lastPage = $scope.searchEntity.pageNum+2;
                //前面有点
                $scope.firstDot = true;
                //后面有点
                $scope.lastDot = true;
            }

        }else {
            //前面有点
            $scope.firstDot = false;
            //后面有点
            $scope.lastDot = false;
        }

        for(var index=firstPage;index<=lastPage;index++) {
            $scope.pageLable.push(index);
        }

    }

    /**
     * 增加搜索条件
     */
    $scope.addSearchEntity=function(key,value) {
        if(key=='categoryName' || key =='brand' || key=='price') {
            $scope.searchEntity[key] = value;
        }else{
            $scope.searchEntity.spec[key]=value;
        }
        $scope.keyworkdSearch();
    }

    /**
     * 移除收索条件
     * @param key
     */
    $scope.removeSearchEntity = function(key) {
        if(key=='categoryName' || key =='brand' || key=='price') {
            $scope.searchEntity[key] = '';
        }else{
            delete $scope.searchEntity.spec[key];//移除此属性
        }
        $scope.keyworkdSearch();
    }

    $scope.queryByPage=function(currentPage) {
        if(currentPage<1) {
            currentPage=1;
        }else if(currentPage>$scope.totalPage) {
            currentPage = $scope.totalPage
        }
        $scope.searchEntity.pageNum=currentPage;
        $scope.keyworkdSearch();
    }

    $scope.isFirstPage=function() {
        if($scope.searchEntity.pageNum==1) {
            return true;
        }else {
            return false;
        }
    }

    $scope.isLastPage=function() {
        if($scope.searchEntity.pageNum==$scope.totalPage) {
            return true;
        }else {
            return false;
        }
    }

    $scope.sortSearch=function(sortSpec,sortField) {
        $scope.searchEntity.sortSpec=sortSpec;
        $scope.searchEntity.sortField=sortField;
        $scope.keyworkdSearch();
    }

     $scope.keywordIsBrandFlag = false;

    /**
     * 如果搜索关键字是品牌 ，那么就隐藏品牌列表
     */
    $scope.keywordIsBrand=function() {
        for(var index=0;index<$scope.brandList.length;index++) {
            if($scope.searchEntity.keywords.indexOf($scope.brandList[index].text)>=0) {
                $scope.keywordIsBrandFlag = true;
                return;
            }
        }
        $scope.keywordIsBrandFlag=false;
    }

    $scope.loadKeyword=function() {
        $scope.searchEntity.keywords=$location.search()['keywords'];
        $scope.keyworkdSearch();
    }


})