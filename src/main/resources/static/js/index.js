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
    //indexAJAX("/articleList","GET",null,null,buildList);
    var url="/articleListQuary?pageNum=1&pageSize=12";
    indexAJAX(url,"GET",null,null,buildListQuary);
});

function indexAJAX(url,type,data,async,successMethod){
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

function buildList(response){
    var showList=$('#showList');
    if (response.code==200){
        var data=response.data;
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
    }else{
        toastr.warning("页面加载失败，请重试");
    }
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
    var url="/articleListQuary?pageNum="+pageNum+"&pageSize=12";
    indexAJAX(url,"GET",null,null,buildListQuary);
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
