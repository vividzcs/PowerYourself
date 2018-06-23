<#include "header.ftl" />
 <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="content.html" class="list-group-item">内容管理</a>
                <a href="content_post.html" class="list-group-item active">添加内容</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>内容管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="content.html">内容管理</a>
                </li>
                <li class="active">
                    <a href="content_post.html">添加内容</a>
                </li>
            </ul>
            <form action="#" class="mar_t15">
                <div class="form-group">
                    <label for="title">标题</label>
                    <input type="text" id="title" class="form-control" placeholder="请输入文章标题">
                </div>
                <div class="form-group">
                    <label for="parentId">所属分类</label>
                    <select id="parentId" name="parentId" class="form-control">
                        <option value="0">顶级分类</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="datetimepicker" >提醒时间</label>
                    <input type="text" class="form-control" name="beginTime" class="beginTime" value="" id="datetimepicker"  placeholder="请输入提醒时间"/>
                    <input type="hidden" id="dtp_input1" value="" />
                </div>
                <div class="form-group">
                    <label for="datetimepicker1">到期时间</label>
                    <input type="text" class="form-control" name="endTime" class="endTime" value="" id="datetimepicker1"  placeholder="请输入到期时间"/>
                    <input type="hidden" name="dtp_input1" id="dtp_input1" value="" />
                </div>

                <div class="form-group">
                    <label for="content">文章内容</label>
                    <textarea id="content" class="form-control" rows="10" cols="10" placeholder="请输入文章正文部分"></textarea>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox">全局置顶
                    </label>
                    <button type="submit" class="btn btn-default pull-right">发布文章</button>
                </div>
            </form>

        </div>
    </div>
</div>

<#include "footer.ftl" />
<script src="js/jquery.datetimepicker.full.js"></script>
<script>
    $.datetimepicker.setLocale('en');

    $('#datetimepicker_format').datetimepicker({value:'2015/04/15 05:03', format: $("#datetimepicker_format_value").val()});

    $("#datetimepicker_format_change").on("click", function(e){
        $("#datetimepicker_format").data('xdsoft_datetimepicker').setOptions({format: $("#datetimepicker_format_value").val()});
    });
    $("#datetimepicker_format_locale").on("change", function(e){
        $.datetimepicker.setLocale($(e.currentTarget).val());
    });
    //获取到本地的时间
    var dataTime=new Date();
    var nowtime=dataTime.toLocaleDateString()+" "+dataTime.getHours()+":"+dataTime.getMinutes();
    $('#datetimepicker').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        disabledDates:['2015/01/08','2015/01/09','2015/01/10'],
        startDate: dataTime.toLocaleDateString()
    });
    $('#datetimepicker').datetimepicker({value:nowtime,step:10});
    $('#datetimepicker1').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        disabledDates:['2015/01/08','2015/01/09','2015/01/10'],
        startDate: dataTime.toLocaleDateString()
    });
    $('#datetimepicker1').datetimepicker({value:nowtime,step:10});
</script>
</body>
</html>