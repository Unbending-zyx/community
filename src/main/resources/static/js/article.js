$(function () {
    var articleId = $('#articleId');
    var commentContent = $('#commentContent');

    var firstCommentUrl = "/comment/selectAllFirstComment?type=1&parentId=" + articleId.val();
    articleAJAX(firstCommentUrl, "GET", null, null, buildFirstCommentList);


    $('#insertComment').click(function () {
        if(commentContent.val()=="" || commentContent.val()==null){
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
            for (var i = 0; i <=commentDTOList.length - 1; i++) {
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
                        '                                <span class="like_and_comment">\n' +
                        '                                    <span class="glyphicon glyphicon-thumbs-up icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].likeCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="like_and_comment">\n' +
                        '                                    <span class="glyphicon glyphicon-comment icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].commentCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                        <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
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
                        '                                <span class="like_and_comment">\n' +
                        '                                    <span class="glyphicon glyphicon-thumbs-up icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].likeCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="like_and_comment">\n' +
                        '                                    <span class="glyphicon glyphicon-comment icon"></span>\n' +
                        '                                    <span style="margin-right: 6px;">' + commentDTOList[i].commentCount + '</span>\n' +
                        '                                </span>\n' +
                        '                                    <span class="pull-right">' + getMyDate(commentDTOList[i].commentCreateTime) + '</span>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                        <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">\n' +
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