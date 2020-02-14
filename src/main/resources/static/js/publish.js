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


    var article = $('#articleFrom');
    var title = $('#title');
    var description = $('#description');
    var tag = $('#tag');

    //文章提交
    $('#articleSubmit').click(function () {
        if (title.val() == "") {
            toastr.warning("标题不能为空");
            return;
        }
        if (description.val() == "") {
            toastr.warning("话题内容不能为空");
            return;
        }
        if (tag.val() == "") {
            toastr.warning("文章标签不能为空");
            return;
        }
        var data=JSON.stringify(
            {
                "title":title.val(),
                "description":description.val(),
                "tag":tag.val()
            }
        );
        $.ajax({
            type: "POST",
            url: "/publish/insert",
            dataType: "json",
            async:false,
            contentType : "application/json",
            data: data,
            success: function (response) {
                if (response.code==1){
                    toastr.success(response.msg);
                    title.val("");
                    description.val("");
                    tag.val("");
                }
                if (response.code==0){
                    toastr.warning(response.msg);
                }
                if (response.code==2){
                    toastr.warning(response.msg);
                    window.location.href = "/login/show";
                }
            }
        });
    });
});