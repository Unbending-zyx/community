
$(function(){
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



    //获取注册模态框中的输入框
    var registeredUsername=$('#registeredUsername');
    var registeredPassword=$('#registeredPassword');
    var registeredConfirmPassword=$('#registeredConfirmPassword');
    var registeredAccountName=$('#registeredAccountName');
    var registeredGitBio=$('#registeredGitBio');

    //关闭注册界面  清空输入框内容
    $('#registeredClose').click(function(){
        registeredUsername.val("");
        registeredPassword.val("");
        registeredConfirmPassword.val("");
        registeredAccountName.val("");
        registeredGitBio.val("");
    });
    //注册按钮的注册事件
    $('#registeredSubmit').click(function(){
        if (registeredUsername.val()==""){
            toastr.warning("用户名不能为空");
            return;
        }
        if (registeredPassword.val()==""){
            toastr.warning("密码不能为空");
            return;
        }
        if (registeredConfirmPassword.val()==""){
            toastr.warning("确认密码不能为空");
            return;
        }
        if (registeredAccountName.val()==""){
            toastr.warning("昵称不能为空");
            return;
        }
        if (registeredPassword.val()!=registeredConfirmPassword.val()){
            toastr.warning("两次密码输入不一致");
            return;
        }

        var data=JSON.stringify(
            {
                "username":registeredUsername.val(),
                "password":registeredPassword.val(),
                "accountName":registeredAccountName.val(),
                "gitBio":registeredGitBio.val()
            }
        );
        $.ajax({
            type: "POST",
            url: "/login/insertUser",
            dataType: "json",
            async:false,
            contentType : "application/json",
            data: data,
            success: function (response) {
                if (response.type=="0"){
                    toastr.success(response.msg);
                    $('#registeredClose').trigger('click');
                }
                if (response.type=="1"){
                    toastr.warning(response.msg);
                }

            }
        });

    });

    $('#loginSubmit').click(function(){
        var loginUserName=$('#loginUserName');
        var loginPassword=$('#loginPassword');
        if (loginUserName.val()==""){
            toastr.warning("用户名不能为空");
            return;
        }
        if (loginPassword.val()==""){
            toastr.warning("密码不能为空");
            return;
        }
        var data=JSON.stringify(
            {
                "username":loginUserName.val(),
                "password":loginPassword.val()
            }
        );
        $.ajax({
            type: "POST",
            url: "/login/login",
            dataType: "json",
            async:false,
            contentType : "application/json",
            data: data,
            success: function (response) {
                if (response.code==1 || response.code==2){
                    alert(response.msg);
                }
                if (response.code==0){
                    window.location.href = "/";
                }

            }
        });

    });

});



function loginAJAX(url,type,data,asyncType){
    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        async:asyncType,
        contentType : "application/json",
        data: data,
        success: function (response) {
            alert(response.msg);
        }
    });
}