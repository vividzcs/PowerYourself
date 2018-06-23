<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>PowerYourself--掌握你的生活</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/bootstrap-maizi.css"/>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
<!--导航-->
<nav class="navbar navbar-default">
    <div class="container">
        <!--小屏幕导航按钮和logo-->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="index.html" class="navbar-brand">vividzc adminx</a>
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
                                    <li><a href="/user/category/find/${ordered[0].id}"><span class="glyphicon glyphicon-th-list"></span>${ordered[1]}${ordered[0].categoryName}</a></li>
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
                            <li><a href="/user/"><span class="glyphicon glyphicon-screenshot"></span>&nbsp;&nbsp;后台首页</a></li>
                        </ul>
                    </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
                    <#else>
                    <li><a href="/to_login"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;登录</a></li>
                <li><a href="/to_register"><span class="glyphicon glyphicon-registration-mark"></span>&nbsp;&nbsp;注册</a></li>
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
    <div class="col-xs-12">
        <div class="progress" title="Popover title"
             data-container="body" data-toggle="popover"
             data-content="你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家">
            <div class="progress-bar progress-bar-success" style="width:20%"></div>
            <div class="progress-bar progress-bar-info progress-bar-striped" style="width:20%"></div>
            <div class="progress-bar progress-bar-warning" style="width:30%"></div>
            <div class="progress-bar progress-bar-danger progress-bar-striped" style="width:15%"></div>
            <span class="content"><time>2018-06-23 08:30</time>&nbsp;&nbsp;你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家</span>
        </div>

        <div class="progress" title="Popover title"
             data-container="body" data-toggle="popover"
             data-content="你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家">
            <div class="progress-bar progress-bar-success" style="width:20%"></div>
            <div class="progress-bar progress-bar-info progress-bar-striped" style="width:20%"></div>
            <div class="progress-bar progress-bar-warning" style="width:30%"></div>
            <div class="progress-bar progress-bar-danger progress-bar-striped" style="width:15%"></div>
            <span class="content"><time>2018-06-23 08:30</time>&nbsp;&nbsp;你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家。你的代办事项管家</span>
        </div>
    </div>
</div>

<!--footer-->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>
                    Copyright&nbsp;©&nbsp;2012-2017&nbsp;&nbsp;www.XXX.com&nbsp;&nbsp;京ICP备XXXX号
                </p>
            </div>
        </div>
    </div>
</footer>
<!--footer-->


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
    $(function (){
        $("[data-toggle='popover']").popover({
            trigger: "click| hover | focus",
            placement: "auto",
            delay:
                    { show: 100, hide: 500 }
        })
    });

    $('.big-nav li').click(function(event) {

        $(this).tab('show');
    });
</script>
</body>
</html>