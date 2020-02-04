
$(function(){
    // //git登录方法
    // $('#gitLogin').click(function(){
    //     var url="https://github.com/login/oauth/authorize?client_id=0126d63e0e7c068e32f5&redirect_uri=http://localhost:8080/callback&scope=user&state=1";
    //     loginAJAX(url,"GET",null,false);
    // });
});

function loginAJAX(url,type,data,asyncType){
    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        async:asyncType,
        data: data,
        success: function (response) {
            alert("成功");
            alert(response.code);
        }
    });
}