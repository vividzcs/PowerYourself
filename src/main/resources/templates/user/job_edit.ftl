<#include "header.ftl" />
 <link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/user/job/" class="list-group-item">正常任务</a>
                <a href="/user/job/finished" class="list-group-item">已完成任务</a>
                <a href="/user/job/over_date" class="list-group-item">过期任务</a>
                <a href="/user/job/to_add" class="list-group-item">添加任务</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>内容管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="/user/job/">正常任务</a>
                </li>
                <li>
                    <a href="/user/job/finished">已完成任务</a>
                </li>
                <li>
                    <a href="/user/job/over_date">过期任务</a>
                </li>
                <li>
                    <a href="/user/job/to_add">添加任务</a>
                </li>
                <li class="active">
                    <a href="">编辑任务</a>
                </li>
                <li style="color: red;">${msg!}</li>
            </ul>
            <form action="/user/job/update" class="mar_t15" method="post">
                <div class="form-group">
                    <label for="title">标题</label>
                    <input type="text" id="title" name="title" class="form-control" placeholder="请输入任务标题" value="${job.title}">
                    <input type="hidden" name="id" value="${job.id}"/>
                </div>
                <div class="form-group">
                    <label for="taskCategoryId">所属分类</label>
                    <select id="taskCategoryId" name="taskCategoryId" class="form-control">
                            <#list allCategoryOrdered as ordered>
                                <#if ordered[0].id == job.taskCategoryId>
                                <option value="${ordered[0].id}" selected>${ordered[1]}${ordered[0].categoryName}</option>
                                <#else >
                                <option value="${ordered[0].id}">${ordered[1]}${ordered[0].categoryName}</option>
                                </#if>

                            </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="datetimepicker" >提醒时间</label>
                    <input type="text" class="form-control" name="remindTime" class="beginTime" value="" id="datetimepicker"  placeholder="请输入提醒时间"/>
                    <input type="hidden" id="dtp_input1" value="" />
                </div>
                <div class="form-group">
                    <label for="datetimepicker1">到期时间</label>
                    <input type="text" class="form-control" name="endTime" class="endTime" value="" id="datetimepicker1"  placeholder="请输入到期时间"/>
                    <input type="hidden" name="dtp_input1" id="dtp_input1" value="" />
                </div>

                <div class="form-group">
                    <label for="content">任务内容</label>
                    <textarea id="notation" name="notation" class="form-control" rows="10" cols="10" placeholder="请输入任务正文部分">${job.notation}</textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default pull-right">更新任务</button>
                </div>
            </form>

        </div>
    </div>
</div>

<#include "footer.ftl" />
<script src="/js/jquery.datetimepicker.full.js"></script>
<script>
    var dataTime=new Date();
    $.datetimepicker.setLocale('en');

    $('#datetimepicker_format').datetimepicker({value:'2015/04/15 05:03', format: $("#datetimepicker_format_value").val()});

    $("#datetimepicker_format_change").on("click", function(e){
        $("#datetimepicker_format").data('xdsoft_datetimepicker').setOptions({format: $("#datetimepicker_format_value").val()});
    });
    $("#datetimepicker_format_locale").on("change", function(e){
        $.datetimepicker.setLocale($(e.currentTarget).val());
    });
    //获取到本地的时间
    $('#datetimepicker').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        disabledDates:['2015/01/08','2015/01/09','2015/01/10'],
        startDate: dataTime.toLocaleDateString()
    });
    $('#datetimepicker').datetimepicker({value:'${job.remindTime}',step:10});
    $('#datetimepicker1').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        disabledDates:['2015/01/08','2015/01/09','2015/01/10'],
        startDate: dataTime.toLocaleDateString()
    });
    $('#datetimepicker1').datetimepicker({value:'${job.endTime}',step:10});
</script>
</body>
</html>