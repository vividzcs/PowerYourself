<#include "header.ftl" />
<!--导航-->

<div class="container">
<div class="row">
<div class="col-md-2">
    <div class="list-group">
        <a href="/user/job/" class="list-group-item active">正常任务</a>
        <a href="/user/job/finished" class="list-group-item">已完成任务</a>
        <a href="/user/job/over_date" class="list-group-item">过期任务</a>
        <a href="/user/job/to_add" class="list-group-item">添加任务</a>
    </div>
</div>
<div class="col-md-10">
<div class="page-header">
    <h1>任务管理</h1>
</div>
<ul class="nav nav-tabs">
    <li class="active">
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
</ul>
<table class="table">
    <thead>
    <tr>
        <th>所属分类</th>
        <th>任务标题</th>
        <th>内容</th>
        <th>发布时间</th>
        <th>提醒时间</th>
        <th>过期时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#list allJob.list as job>
    <tr>
        <th>${job.categoryName}</th>
        <th scope="row">${job.title}</th>
        <th>

            <div class="progress" title="${job.title}"
                 data-container="body" data-toggle="popover"
                 data-content="${job.notation?html}">
                <#assign conLen=job.title?length />
                <#if conLen<10>
                ${job.title}
                <#else >
                    ${job.title[0..9]}...
                </#if>
            </div>
        </th>
        <td>${(job.beganTime?string("yyyy/MM/dd HH:mm"))!}</td>
        <td>${(job.remindTime?string("yyyy/MM/dd HH:mm"))!}</td>
        <td>${(job.endTime?string("yyyy/MM/dd HH:mm"))!}</td>
        <td>
            <div role="presentation" class="dropdown">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    操作<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="/user/job/edit/${job.id}">编辑</a></li>
                    <li><a href="/user/job/delete/${job.id}" onclick="confirmdelete(event,url=this.href)">删除</a></li>
                    <li><a href="/user/job/done/${job.id}"  onclick="confirmdone(event,url=this.href)">完成</a></li>
                </ul>
            </div>
        </td>
    </tr>
    </#list>
    </tbody>
</table>
<nav class="pull-right">
    <ul class="pagination">
        <#if allJob.hasPreviousPage>
            <li><a href="/user/job/${allJob.prePage}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        <#else >
            <li class="disabled"><span aria-hidden="true">&laquo;</span></li>
        </#if>

        <#list allJob.navigatepageNums as nav>
            <li ${(nav == allJob.pageNum)?string("class='active'","")}><a href="/user/job/${nav}">${nav}</a></li>
        </#list>
        <#if allJob.hasNextPage>
            <li><a href="/user/job/${allJob.nextPage}"><span aria-hidden="true">&raquo;</span></a></li>
        <#else >
            <li class="disabled"><span aria-hidden="true">&raquo;</span></li>
        </#if>

    </ul>
</nav>
</div>
</div>
</div>
<#include "footer.ftl" />
    <script>
        $(function () {
            $("[data-toggle='popover']").popover({
                trigger: "click| hover | focus",
                placement: "auto",
                delay:
                        {show: 100, hide: 500}
            })
        });

        function confirmdelete(event,url){
            if(!confirm("确认要删除吗")) {

            }else{
                $.post(url,function(res){
                    if(res.status == 0){
                        //成功
                        window.location.href="/user/job/";
                    }else{
                        alert(res.msg)
                    }
                })
            }
            event.returnValue = false;

        }

        function confirmdone(event,url){
            if(!confirm("确认要标识完成吗")) {

            }else{
                $.post(url,function(res){
                    if(res.status == 0){
                        //成功
                        window.location.href="/user/job/";
                    }else{
                        alert(res.msg)
                    }
                })
            }
            event.returnValue = false;

        }
    </script>
</body>
</html>