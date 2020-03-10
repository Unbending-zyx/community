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

    var myTopic=$('#myTopic');
    var newComment=$('#newComment');
    var messageTitle=$('#messageTitle');
    var showList=$('#showList');

    //newComment中的第二个span显示为查看数
    messageAJAX("/message/unReadNotifyCount","GET",null,null,buildUnReadNotifyCount)


    myTopic.click(function(){
        showList.empty();
        myTopic.addClass("active")
        newComment.removeClass("active");
        messageTitle.html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 我的话题');
        var url="/message/articleListQuary?pageNum=1&pageSize=12";
        messageAJAX(url,"GET",null,null,buildListQuary);
    });
    newComment.click(function(){
        showList.empty();
        //控制显示数字的span标签消失
        // var newCommentSecondSpan=newComment.find("span").eq(1);
        // newCommentSecondSpan.hide();
        newComment.addClass("active");
        myTopic.removeClass("active");
        messageTitle.html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 最新回复');
        var url="/message/unReadNotifyList?pageNum=1&pageSize=8";
        messageAJAX(url,"GET",null,null,buildNotifyListQuary);
    });

    //此处flag在message页面最下端取得值  用于控制初始页面显示我的话题还是最新评论
    if (flag!=null && flag=="notice"){
        newComment.trigger('click');
    }else{
        myTopic.trigger('click');
    }
});

function messageAJAX(url,type,data,async,successMethod){
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

function buildListQuary(response){
    var showList=$('#showList');
    var pageList=$('#pageList');
    if (response.code==200){
        showList.empty();
        pageList.empty();
        var data=response.articleListQuary;
        var pageNum=response.pageNum;
        var pageCount=response.pageCount;
        for (var i=0;i<data.length;i++){
            showList.append('<div class="media">\n' +
                '                   <div class="media-left">\n' +
                '                     <a href="/article/'+data[i].id+'">\n' +
                '                       <img class="media-object img-rounded imgSize" src="'+data[i].user.avatarUrl+'" alt="...">\n' +
                '                     </a>\n' +
                '                   </div>\n' +
                '                   <div class="media-body">\n' +
                '                       <a style="color: #333;text-decoration: none;" href="/article/'+data[i].id+'"><h4 class="media-heading">'+data[i].title+'</h4></a>' +
                '                       <span>'+data[i].description.substring(0,20)+'.....</span><br>\n' +
                '                       <span class="article_data"> • '+data[i].likeCount+' 人点赞 • '+data[i].commentCount+' 个回复 • '+data[i].readingCount+' 次浏览   '+getMyDate(data[i].articleCreateTime)+'</span>\n' +
                '                   </div>' +
                '</div>');
        }

        //拼装分页标签
        var pageListString="";
        if (pageNum>3){
            pageListString+='<li onclick="pageClick('+1+')">' +
                '                                <a href="javascript:void(0);" aria-label="Previous">' +
                '                                    <span aria-hidden="true">&lt;&lt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageNum>1) {
            pageListString+='<li onclick="pageClick('+(pageNum-1)+')">' +
                '                                <a href="javascript:void(0);" aria-label="Previous">' +
                '                                    <span aria-hidden="true">&lt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageCount<=5){
            for (var i=1;i<=pageCount;i++){
                if (i==pageNum){
                    pageListString+='<li class="active" onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                }else{
                    pageListString+='<li onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                }
            }
        }else{
            if (pageNum<=3){
                for (var i=1;i<=5;i++){
                    if (i==pageNum){
                        pageListString+='<li class="active" onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                    }else{
                        pageListString+='<li onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                    }
                }
            }else{
                if (pageNum>=pageCount-2){
                    for (var i=pageCount-4;i<=pageCount;i++){
                        if (i==pageNum){
                            pageListString+='<li class="active" onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }else{
                            pageListString+='<li onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }
                    }
                }else{
                    for (var i=pageNum-2;i<=pageNum+2;i++){
                        if (i==pageNum){
                            pageListString+='<li class="active" onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }else{
                            pageListString+='<li onclick="pageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }
                    }
                }
            }
        }
        if (pageNum<pageCount){
            pageListString+='<li onclick="pageClick('+(pageNum+1)+')">' +
                '                                <a href="javascript:void(0);" aria-label="Next">' +
                '                                    <span aria-hidden="true">&gt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageNum<pageCount-2){
            pageListString+='<li onclick="pageClick('+pageCount+')">' +
                '                                <a href="javascript:void(0);" aria-label="Next">' +
                '                                    <span aria-hidden="true">&gt;&gt;</span>' +
                '                                </a>' +
                '                            </li>';
        }


        pageList.html(pageListString);

    }else{
        toastr.warning("页面加载失败，请重试");
    }
}

function pageClick(pageNum){
    var url="/message/articleListQuary?pageNum="+pageNum+"&pageSize=12";
    messageAJAX(url,"GET",null,null,buildListQuary);
}


// 时间戳转换为日期
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function buildUnReadNotifyCount(response){
    if (response.code==200){
        var newCommentSecondSpan=$('#newComment').find("span").eq(1);
        newCommentSecondSpan.text(response.unReadNotifyCount);
    }else{
        toastr.warning(response.msg);
    }
}

function buildNotifyListQuary(response){
    var showList=$('#showList');
    var pageList=$('#pageList');
    if (response.code==200){
        showList.empty();
        pageList.empty();
        var data=response.notificationList;
        var pageNum=response.pageNum;
        var pageCount=response.pageCount;
        for (var i=0;i<data.length;i++){
            if (data[i].type==1){
                if (data[i].status==0){
                    showList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 notifyDiv">\n' +
                        '                            <span>'+data[i].senderName+'</span>&nbsp;&nbsp;\n' +
                        '                            <span>回复了话题</span>&nbsp;&nbsp;\n' +
                        '                            <span><a href="/article/'+data[i].outerId+'" data="'+data[i].id+'" onclick="clickNotification(this)">'+data[i].outerTitle+'</a></span>' +
                        '                            <span class="label label-danger">未读</span>'+
                        '                        </div>');
                }else{
                    showList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 notifyDiv">\n' +
                        '                            <span>'+data[i].senderName+'</span>&nbsp;&nbsp;\n' +
                        '                            <span>回复了话题</span>&nbsp;&nbsp;\n' +
                        '                            <span><a href="/article/'+data[i].outerId+'" data="'+data[i].id+'" onclick="clickNotification(this)">'+data[i].outerTitle+'</a></span>' +
                        '                        </div>');
                }

            }
            if (data[i].type==2){
                if (data[i].status==0){
                    showList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 notifyDiv">\n' +
                        '                            <span>'+data[i].senderName+'</span>&nbsp;&nbsp;\n' +
                        '                            <span>回复了评论</span>&nbsp;&nbsp;\n' +
                        '                            <span><a href="/article/'+data[i].outerId+'" data="'+data[i].id+'" onclick="clickNotification(this)">'+data[i].outerTitle+'</a></span>' +
                        '                            <span class="label label-danger">未读</span>'+
                        '                        </div>');
                }else{
                    showList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 notifyDiv">\n' +
                        '                            <span>'+data[i].senderName+'</span>&nbsp;&nbsp;\n' +
                        '                            <span>回复了评论</span>&nbsp;&nbsp;\n' +
                        '                            <span><a href="/article/'+data[i].outerId+'" data="'+data[i].id+'" onclick="clickNotification(this)">'+data[i].outerTitle+'</a></span>' +
                        '                        </div>');
                }
            }
        }

        //拼装分页标签
        var pageListString="";
        if (pageNum>3){
            pageListString+='<li onclick="notifyPageClick('+1+')">' +
                '                                <a href="javascript:void(0);" aria-label="Previous">' +
                '                                    <span aria-hidden="true">&lt;&lt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageNum>1) {
            pageListString+='<li onclick="notifyPageClick('+(pageNum-1)+')">' +
                '                                <a href="javascript:void(0);" aria-label="Previous">' +
                '                                    <span aria-hidden="true">&lt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageCount<=5){
            for (var i=1;i<=pageCount;i++){
                if (i==pageNum){
                    pageListString+='<li class="active" onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                }else{
                    pageListString+='<li onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                }
            }
        }else{
            if (pageNum<=3){
                for (var i=1;i<=5;i++){
                    if (i==pageNum){
                        pageListString+='<li class="active" onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                    }else{
                        pageListString+='<li onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                    }
                }
            }else{
                if (pageNum>=pageCount-2){
                    for (var i=pageCount-4;i<=pageCount;i++){
                        if (i==pageNum){
                            pageListString+='<li class="active" onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }else{
                            pageListString+='<li onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }
                    }
                }else{
                    for (var i=pageNum-2;i<=pageNum+2;i++){
                        if (i==pageNum){
                            pageListString+='<li class="active" onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }else{
                            pageListString+='<li onclick="notifyPageClick('+i+')"><a href="javascript:void(0);">'+i+'</a></li>';
                        }
                    }
                }
            }
        }
        if (pageNum<pageCount){
            pageListString+='<li onclick="notifyPageClick('+(pageNum+1)+')">' +
                '                                <a href="javascript:void(0);" aria-label="Next">' +
                '                                    <span aria-hidden="true">&gt;</span>' +
                '                                </a>' +
                '                            </li>';
        }
        if (pageNum<pageCount-2){
            pageListString+='<li onclick="notifyPageClick('+pageCount+')">' +
                '                                <a href="javascript:void(0);" aria-label="Next">' +
                '                                    <span aria-hidden="true">&gt;&gt;</span>' +
                '                                </a>' +
                '                            </li>';
        }

        pageList.html(pageListString);

    }else{
        toastr.warning(response.msg);
    }
}

function notifyPageClick(pageNum){
    var url="/message/unReadNotifyList?pageNum="+pageNum+"&pageSize=8";
    messageAJAX(url,"GET",null,null,buildNotifyListQuary);
}

//将该条通知设置为已读
function clickNotification(object){
    var object =$(object);
    var id=object.attr("data");

    var data=JSON.stringify({
        "id":id
    });
    var url="/notify/setStatusRead";
    messageAJAX(url,"PUT",data,null,setStatusSuccess)
}

function setStatusSuccess(response){
    if (response.code==200){
        return true;
    }else{
        return false;
    }
}