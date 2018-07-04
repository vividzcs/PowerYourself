<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>PowerYourself--掌握你的生活</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/bootstrap-maizi.css"/>
    <link rel="stylesheet" href="/css/home.css">
</head>
<body>
<!--导航-->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!--小屏幕导航按钮和logo-->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">PowerYourself</a>
        </div>
        <!--小屏幕导航按钮和logo-->
        <!--导航-->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav big-nav">
                <li class="active"><a href="/"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
                <li class="dropdown">
                    <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        分类
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <#if Session.currentUser?exists>
                            <#if allCategoryOrdered?exists>
                                <#list allCategoryOrdered as ordered>
                                    <li><a href="/find/${ordered[0].id}"><span
                                            class="glyphicon glyphicon-th-list"></span>${ordered[1]}${ordered[0].categoryName}
                                    </a></li>
                                </#list>

                            </#if>
                        <#else>
                            <li>&nbsp;&nbsp;请登录</li>
                        </#if>

                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <#if Session.currentUser?exists>
                    <li><a href="javascript:void(0)"></a></li>
                    <li class="dropdown">
                        <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            你好,${Session.currentUser.username}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dLabel">
                            <li><a href="/user/"><span
                                    class="glyphicon glyphicon-screenshot"></span>&nbsp;&nbsp;后台首页</a></li>
                        </ul>
                    </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
                <#else>
                    <li><a href="/to_login"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;登录</a></li>
                <li><a href="/to_register"><span class="glyphicon glyphicon-registration-mark"></span>&nbsp;&nbsp;注册</a>
                </li>
                </#if>

            </ul>
        </div>
        <!--导航-->

    </div>
</nav>
<!--导航-->

<!--警告框-->
<div class="container">
    <div class=col-xs-12>
        <h1 class="banner"><span class=hide>PowerYourself - </span>你的代办事项管家。</h1>
        <img src=images/logo.gif alt=PowerYourself class=hide>
    </div>
    <div class="col-xs-12" id="job_list">
        <#if allJob?exists>
            <#if (allJob.list?size >0)>
            <#list allJob.list as job>
            <li class="dropdown">

                <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <div id="job_item_detail" class="progress" title="${job.title}"
                         data-container="body" data-toggle="popover"
                         data-content='${job.notation?html}'>

                        <div class="progress-bar progress-bar-warning" style="width:${job.progress}%"></div>
                        <span class="content"><time>${(job.endTime?string("yyyy/MM/dd hh:mm"))!}</time>&nbsp;&nbsp;<#assign conLen=job.title?length />
                <#if conLen<10>
                    ${job.title}
                <#else >
                    ${job.title[0..9]}...
                </#if></span>

                    </div>
                </a>
                <ul class="dropdown-menu" aria-labelledby="dLabel">
                    <li><a href="/user/job/delete/${job.id}" onclick="confirmdelete(event,url=this.href)">删除</a></li>
                    <li><a href="/user/job/done/${job.id}"  onclick="confirmdone(event,url=this.href)">完成</a></li>
                </ul>
            </li>
            </#list>
            <#else >
            <div class="progress" title="Popover title"
                 data-container="body" data-toggle="popover"
                 data-content='暂无数据'>
                <span class="content" style="text-align: center;right: auto">暂无数据</span>
            </div>
            </#if>
            <#else>
            <li class="dropdown">

                <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <div class="progress" title="PowerYourself"
                         data-container="body" data-toggle="popover"
                         data-content="你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家">

                        <div class="progress-bar progress-bar-warning" style="width:30%"></div>

                        <span class="content"><time>2018-06-23 08:30</time>&nbsp;&nbsp;你的代办事项管家。你的代办事项管家。你的代办事项管家。</span>

                    </div>


                </a>
                <ul class="dropdown-menu" aria-labelledby="dLabel">
                    <li><a href=""><span class="glyphicon glyphicon-screenshot"></span>&nbsp;&nbsp;删除</a></li>
                    <li><a href=""><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;完成</a></li>

                </ul>
            </li>
            <li class="dropdown">

                <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <div class="progress" title="PowerYourself"
                         data-container="body" data-toggle="popover"
                         data-content="你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家">

                        <div class="progress-bar progress-bar-warning" style="width:30%"></div>

                        <span class="content"><time>2018-06-23 08:30</time>&nbsp;&nbsp;你的代办事项管家。你的代办事项管家。你的代办事项管家。</span>

                    </div>


                </a>
                <ul class="dropdown-menu" aria-labelledby="dLabel">
                    <li><a href=""><span class="glyphicon glyphicon-screenshot"></span>&nbsp;&nbsp;删除</a></li>
                    <li><a href=""><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;完成</a></li>

                </ul>
            </li>
        </#if>
    </div>
</div>
<#if allJob?exists>
<div class="container">
<nav class="pull-right">
    <ul class="pagination">
        <#if allJob.hasPreviousPage>
            <li><a href="/find/<#if cate_id?exists && cate_id!="0" >${cate_id+"/"}</#if>${allJob.prePage}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        <#else >
            <li class="disabled"><span aria-hidden="true">&laquo;</span></li>
        </#if>

        <#list allJob.navigatepageNums as nav>
            <li ${(nav == allJob.pageNum)?string("class='active'","")}><a href="/find/<#if cate_id?exists && cate_id!="0" >${cate_id+"/"}</#if>${nav}">${nav}</a></li>
        </#list>
        <#if allJob.hasNextPage>
            <li><a href="/find/<#if cate_id?exists && cate_id!="0" >${cate_id+"/"}</#if>${allJob.nextPage}"><span aria-hidden="true">&raquo;</span></a></li>
        <#else >
            <li class="disabled"><span aria-hidden="true">&raquo;</span></li>
        </#if>

    </ul>
</nav>
</div>
<#else>
<div class="container">
<nav class="pull-right">
    <ul class="pagination">
        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        <li class="active"><a href="#">1</a></li>
        <li><a href="#">2 </a></li>
        <li><a href="#">3 </a></li>
        <li><a href="#">4 </a></li>
        <li><a href="#">5 </a></li>
        <li><a href="#">6 </a></li>
        <li><a href="#"><span aria-hidden="true">&raquo;</span></a></li>
    </ul>
</nav>
</div>
</#if>
<!--footer-->
<footer class="navbar-fixed-bottom" style="background:#f8f8f8;opacity: 1">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>
                    Copyright&nbsp;©&nbsp;2018-${.now?string("yyyy")}&nbsp;&nbsp;<a href="http://todo.nwuer.com">todo.nwuer.com</a>&nbsp;&nbsp;陕ICP备16019866号
                </p>
            </div>
        </div>
    </div>
</footer>
<!--footer-->


<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("[data-toggle='popover']").popover({
            trigger: "click| hover | focus",
            placement: "auto",
            delay:
                    {show: 100, hide: 500}
        })
    });

    $('.big-nav li').click(function (event) {

        $(this).tab('show');
    });
    function confirmdelete(event,url){
        if(!confirm("确认要删除吗")) {

        }else{
            $.post(url,function(res){
                if(res.status == 0){
                    //成功
                    window.location.href="/";
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
                    window.location.href="/";
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