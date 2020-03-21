$(function () {
    //toastr初始化
    toastr.options = {
        "closeButton": false, //是否显示关闭按钮
        "debug": false, //是否使用debug模式
        "positionClass": "toast-top-full-width",//弹出窗的位置
        "showDuration": "300",//显示的动画时间
        "hideDuration": "1000",//消失的动画时间
        "timeOut": "5000", //展现时间
        "extendedTimeOut": "1000",//加长展示时间
        "showEasing": "swing",//显示时的动画缓冲方式
        "hideEasing": "linear",//消失时的动画缓冲方式
        "showMethod": "fadeIn",//显示时的动画方式
        "hideMethod": "fadeOut" //消失时的动画方式
    };

    personalInformationAJAX("/person/selectUserMessage", "GET", null, null, null, null, pageInit)

    $('#subBtn').click(function () {
        var password = $('#password');
        var name = $('#name');
        var bio = $('#bio');
        var data=JSON.stringify({
            "password":password.val(),
            "accountName":name.val(),
            "gitBio":bio.val()
        });

        personalInformationAJAX("/person/upadteUserAndPwd","POST",data,null,null,null,updataUserSuccess);


    });


    $("#imgUploadBtn").click(function () {
        $("#imgUploadFormInput").click();
    });

//下面是ajax上传文件的代码，此处就不做过多讲解。
    $("#imgUploadFormInput").change(function () {//如果上传文件的input内容发生了变化
        $val = $("#imgUploadFormInput").val();
        if ($val != '') {//要上传的文件名不为空
            $data = new FormData($("#imgUploadForm")[0]);//创建一个formdata对象
            personalInformationAJAX("/person/uploadAvatar", "POST", $data, null, false, false, uploadAvatar);
        }
    });
});

//文件上传时 contentType属性设置问false processData也要设置为false  否则报错
function personalInformationAJAX(url, type, data, async, contentType, processData, successMethod) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        async: (async == null ? false : async),
        dataType: "json",
        processData: (processData == null ? true : false),
        contentType: (contentType == null ? "application/json" : contentType),
        success: function (response) {
            successMethod(response);
        }
    });
}

//初始化页面内信息
function pageInit(response) {
    if (response.code == 200) {
        var username = $('#username');
        var password = $('#password');
        var name = $('#name');
        var bio = $('#bio');
        var avatar=$('#avatar');

        username.val(response.user.username);
        password.val(response.user.password);
        name.val(response.user.accountName);
        bio.val(response.user.gitBio);
        avatar.attr("src",response.user.avatarUrl);
    } else {
        toastr.warning(response.msg);
    }
}

//修改成功
function updataUserSuccess(response){
    if (response.code == 200) {
        toastr.success(response.msg);
    } else {
        toastr.warning(response.msg);
    }
}

//头像上传成功的处理方法
function uploadAvatar(response) {
    if (response.code == 200) {
        var avatar = $('#avatar');
        avatar.attr("src", response.url);
    } else {
        toastr.warning(response.msg);
    }
}