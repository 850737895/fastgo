/**
 * Created by Administrator on 2018/11/29.
 */
app.service('fileUploadService',function($http){

    var url = "http://localhost:9101";
    //文件上传
    this.fileUpload=function() {
        var formdata=new FormData();
        formdata.append('file',file.files[0]);//file 文件上传框的name
        return $http({
            url:url+'/file/upload',
            method:'post',
            data:formdata,
            headers:{ 'Content-Type':undefined },
            transformRequest: angular.identity
        });
    }

    //删除文件服务器上的文件
    this.delFile=function(imgUrl) {
        $http.get(url+"/file/del?fileUrl="+imgUrl);
    }
})
