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

    var articleId = $('#articleId');
    var commentContent = $('#commentContent');
    var articleTag = $('#articleTag');

    var firstCommentUrl = "/comment/selectComment?type=1&parentId=" + articleId.val();
    articleAJAX(firstCommentUrl, "GET", null, null, buildFirstCommentList);

    var relatedArticleUrl = "/findRelatedArticles?id=" + articleId.val() + "&tag=" + articleTag.val();
    articleAJAX(relatedArticleUrl,"GET",null,null,buildRelatedArticleList)

    $('#insertComment').click(function () {
        if (commentContent.val() == "" || commentContent.val() == null) {
            toastr.warning("回复内容不能为空");
            return;
        }
        var data = JSON.stringify(
            {
                "type": 1,
                "parentId": articleId.val(),
                "commentContent": commentContent.val()
            }
        );
        articleAJAX("/comment/insert", "POST", data, null, insertCommentSuccess);
    });
});

function articleAJAX(url, type, data, async, successMethod) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        async: (async == null ? false : async),
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            successMethod(response);
        },
        error: function (XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.responseText != "") {
                window.location.href = "/login/show";
            }
        }
    });
}

function insertCommentSuccess(response) {
    if (response.code == 200) {
        //如果成功  隐藏评论框
        //$('#commentTextarea').hide();
        $('#commentContent').val("");
        window.location.reload();
    } else {
        toastr.warning(response.msg);
    }
}

function buildFirstCommentList(response) {
    var commentList = $('#commentList');
    commentList.empty();
    if (response.code == 200) {
        var commentDTOList = response.commentDTOList;
        if (commentDTOList == null || commentDTOList.length == 0) {
            return;
        } else {
            for (var i = 0; i <= commentDTOList.length - 1; i++) {
                if (commentDTOList[i].user.gitName != null) {
                    commentList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
                        '                        <div class="media">\n' +
                        '                            <div class="media-left">\n' +
                        '                                <a href="#">\n' +
                        '                                    <img class="media-object img-rounded imgSize"\n' +
                        '                                         src="' + commentDTOList[i].user.avatarUrl + '">\n' +
                        '                                </a>\n' +
                        '                            </div>\n' +
                        '                            <div class="media-body">\n' +
                        '                                <h4 class="media-heading">' + commentDTOList[i].user.gitName + '</h4>\n' +
                        '                                <div>' + commentDTOList[i].commentContent + '</div>\n' +
                        '                                <div class="menu">\n' +
                        '                                <span class="like_and_comment" data="' + commentDTOList[i].id + '" onclick="changeLike(this)">\n' +
                        '                                    <span class="glyphicon glyphicon-thumbs-up icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].likeCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                <span class="like_and_comment" data="' + commentDTOList[i].id + '" onclick="secondComments(this)">\n' +
                        '                                    <span class="glyphicon glyphicon-comment icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].commentCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                        '                                </div>\n' +
                        '                               <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comments collapse" style="background: #f9f9f9;" id="comment_' + commentDTOList[i].id + '">' +
                        '                               </div>' +
                        '                            </div>\n' +
                        '                        </div>' +
                        '                       <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
                        '                    </div>');
                } else if (commentDTOList[i].user.accountName != null) {
                    commentList.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
                        '                        <div class="media">\n' +
                        '                            <div class="media-left">\n' +
                        '                                <a href="#">\n' +
                        '                                    <img class="media-object img-rounded imgSize"\n' +
                        '                                         src="' + commentDTOList[i].user.avatarUrl + '">\n' +
                        '                                </a>\n' +
                        '                            </div>\n' +
                        '                            <div class="media-body">\n' +
                        '                                <h4 class="media-heading">' + commentDTOList[i].user.accountName + '</h4>\n' +
                        '                                <div>' + commentDTOList[i].commentContent + '</div>\n' +
                        '                                <div class="menu">\n' +
                        '                                <span class="like_and_comment" data="' + commentDTOList[i].id + '" onclick="changeLike(this)">\n' +
                        '                                    <span class="glyphicon glyphicon-thumbs-up icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].likeCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                <span class="like_and_comment" data="' + commentDTOList[i].id + '" onclick="secondComments(this)">\n' +
                        '                                    <span class="glyphicon glyphicon-comment icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].commentCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                        '                                </div>\n' +
                        '                               <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 sub-comments collapse" style="background: #f9f9f9;" id="comment_' + commentDTOList[i].id + '">' +
                        '                               </div>' +
                        '                            </div>\n' +
                        '                        </div>' +
                        '                        <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                        '                    </div>');
                }
            }
        }
    } else {
        toastr.warning(response.msg);
    }
}

// 时间戳转换为日期
function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
    return oTime;
};

//补0操作
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

var commentObject = null;
var commentFirstId = -1;
var commentSpan = null;

//二级评论   操作方法   点击事件
function secondComments(object) {
    var object = $(object);
    commentSpan = object;
    var id = object.attr("data");
    commentFirstId = id;
    object.css("color", "#999");
    var comment = $('#comment_' + id);
    //var insertComment=$('#comment_'+id+' .well');
    //设置窗口的显隐性  点击一次  显示  再点击一次  隐藏
    comment.toggleClass("in");


    //当元素的class存在 in属性时  执行   及窗体显示时执行
    if (comment.hasClass("in")) {
        object.css("color", "#499ef3");
        commentObject = comment;
        var secondCommentsUrl = "/comment/selectComment?type=2&parentId=" + id;
        articleAJAX(secondCommentsUrl, "GET", null, null, buildSecondCommentList);
    }

}

function buildSecondCommentList(response) {
    var code = response.code;
    if (code == 200) {
        commentObject.empty();
        var commentDTOList = response.commentDTOList;
        if (commentDTOList != null) {
            if (commentObject != null) {
                for (var i = 0; i < commentDTOList.length; i++) {
                    if (commentDTOList[i].user.gitName != null) {
                        commentObject.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 secondComments">' +
                            '                       <div class="media">' +
                            '                           <div class="media-left">' +
                            '                               <img class="media-object img-rounded imgSize" src="' + commentDTOList[i].user.avatarUrl + '">' +
                            '                           </div>' +
                            '                           <div class="media-body">\n' +
                            '                               <h5 class="media-heading">' + commentDTOList[i].user.gitName + '</h5>\n' +
                            '                               <div>' + commentDTOList[i].commentContent + '</div>' +
                            '                               <div class="menu">' +
                            '                                   <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                            '                               </div>\n' +
                            '                           </div>\n' +
                            '                       </div>\n' +
                            '               </div>');
                    } else if (commentDTOList[i].user.accountName != null) {
                        commentObject.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 secondComments">' +
                            '                       <div class="media">' +
                            '                           <div class="media-left">' +
                            '                               <img class="media-object img-rounded imgSize" src="' + commentDTOList[i].user.avatarUrl + '">' +
                            '                           </div>' +
                            '                           <div class="media-body">\n' +
                            '                               <h5 class="media-heading">' + commentDTOList[i].user.accountName + '</h5>\n' +
                            '                               <div>' + commentDTOList[i].commentContent + '</div>' +
                            '                               <div class="menu">' +
                            '                                   <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                            '                               </div>\n' +
                            '                           </div>\n' +
                            '                       </div>\n' +
                            '               </div>');
                    }
                }
                if (commentFirstId != -1) {
                    commentObject.append('<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
                        '                                    <input type="text" class="form-control" placeholder="评论一下……" id="input-' + commentFirstId + '">\n' +
                        '                                    <button type="button" class="btn btn-success pull-right" onclick="insertSecondComment(this)" data="' + commentFirstId + '">评论\n' +
                        '                                    </button>\n' +
                        '</div>');
                }
                //commentObject = null;
                //commentFirstId=-1;
            }
        }
    } else {
        toastr.warning(response.msg);
    }
}

function insertSecondComment(object) {
    var object = $(object);
    var parentId = object.attr("data");
    var input = $('#input-' + parentId);

    if (input.val() == "" || input.val() == null) {
        toastr.warning("评论内容不能为空");
        return;
    }
    var url = "/comment/insert";
    var data = JSON.stringify({
        "type": 2,
        "parentId": parentId,
        "commentContent": input.val()
    });
    articleAJAX(url, "POST", data, null, insertSecondCommentSuccess);
    input.val("");
}

function insertSecondCommentSuccess(response) {
    if (response.code == 200) {
        var secondCommentsUrl = "/comment/selectComment?type=2&parentId=" + commentFirstId;
        articleAJAX(secondCommentsUrl, "GET", null, null, buildSecondCommentList);
        //访问二级评论数的ajax方法
        var secondCommentCountUrl = "/comment/selectSecondCommentCount?type=2&parentId=" + commentFirstId;
        articleAJAX(secondCommentCountUrl, "GET", null, null, changeSecondCommentCount)

    } else {
        toastr.warning(response.msg);
    }
}

function changeSecondCommentCount(response) {
    if (response.code == 200) {
        var commentCount = commentSpan.find("span").eq(1);
        commentCount.text(response.secondCommentCount);
    } else {
        toastr.warning(response.msg);
    }
}

var likeObject = null;

//点赞操作
function changeLike(object) {
    var object = $(object);
    likeObject = object;
    var firstCommentId = object.attr("data");
    var likeUrl = "/comment/changeLike?parentId=" + firstCommentId;
    articleAJAX(likeUrl, "GET", null, null, likeSuccess);
}

function likeSuccess(response) {
    if (response.code == 200) {
        var like = likeObject.find("span").eq(1);
        like.text(response.likeCount);
    } else {
        toastr.warning(response.msg);
    }
}

function buildRelatedArticleList(response){
    if (response.code==200){
        var relatedArticleList= $('#relatedArticleList');
        var list=response.relatedArticles;
        for (var i=0;i<list.length;i++){
            relatedArticleList.append('<li><a href="/article/'+list[i].id+'">'+list[i].title+'</a></li>');
        }
    } else{
        toastr.warning(response.msg);
    }
}

