var selectArticleText="";
$(function () {
    //通知按钮中span里未读数的显示值
    notificationAJAX("/message/unReadNotifyCount","GET",null,null,buildNotificationSpan);
});

function notificationAJAX(url,type,data,async,successMethod){
    $.ajax({
        type:type,
        url:url,
        data:data,
        async:(async==null?false:async),
        dataType: "json",
        contentType : "application/json",
        success:function (response) {
            successMethod(response);
        }
    });
}

//通知按钮中span里未读数的显示值
function buildNotificationSpan(response){
    var notifyCount=$('#notifyCount');
    if(response.code==200){
        notifyCount.text(response.unReadNotifyCount);
    }else{
        notifyCount.text("0");
    }
}

