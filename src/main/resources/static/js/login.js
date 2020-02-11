
$(function(){
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
            alert("用户名不能为空");
            return;
        }
        if (registeredPassword.val()==""){
            alert("密码不能为空");
            return;
        }
        if (registeredConfirmPassword.val()==""){
            alert("确认密码不能为空");
            return;
        }
        if (registeredAccountName.val()==""){
            alert("昵称不能为空");
            return;
        }
        if (registeredPassword.val()!=registeredConfirmPassword.val()){
            alert("两次密码输入不一致");
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
                    alert(response.msg);
                    $('#registeredClose').trigger('click');
                }
                if (response.type=="1"){
                    alert(response.msg);
                }

            }
        });

    });

    $('#loginSubmit').click(function(){
        var loginUserName=$('#loginUserName');
        var loginPassword=$('#loginPassword');
        if (loginUserName.val()==""){
            alert("用户名不能为空");
            return;
        }
        if (loginPassword.val()==""){
            alert("密码不能为空");
            return;
        }
        var data=JSON.stringify(
            {
                "username":loginUserName.val(),
                "password":loginUserName.val()
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