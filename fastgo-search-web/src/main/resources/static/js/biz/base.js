var app=angular.module('fastgo',[]);
app.filter('trustHtml',['$sce',function($sce){
    //信任html
    return function (data) { //data是需要处理的值
        return $sce.trustAsHtml(data);
    }
}])
