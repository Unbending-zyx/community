$(function(){
    var myTopic=$('#myTopic');
    var newComment=$('#newComment');
    var messageTitle=$('#messageTitle');

    myTopic.click(function(){
        myTopic.addClass("active")
        newComment.removeClass("active");
        messageTitle.html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 我的话题');
        var url="/message/articleListQuary?pageNum=1&pageSize=12";
        messageAJAX(url,"GET",null,null,buildListQuary);
    });
    newComment.click(function(){
        newComment.addClass("active");
        myTopic.removeClass("active");
        messageTitle.html('<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 最新回复');
    });
    myTopic.trigger('click');
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
                '                        <div class="media-left">\n' +
                '                            <a href="#">\n' +
                '                                <img class="media-object img-rounded imgSize" src="'+data[i].user.avatarUrl+'" alt="...">\n' +
                '                            </a>\n' +
                '                        </div>\n' +
                '                        <div class="media-body">\n' +
                '                            <h4 class="media-heading">'+data[i].title+'</h4>\n' +
                '                            <span>'+data[i].description.substring(0,20)+'.....</span><br>\n' +
                '                            <span class="article_data"> • '+data[i].likeCount+' 人点赞 • '+data[i].commentCount+' 个回复 • '+data[i].readingCount+' 次浏览   '+getMyDate(data[i].articleCreateTime)+'</span>\n' +
                '                        </div>\n' +
                '                    </div>');
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