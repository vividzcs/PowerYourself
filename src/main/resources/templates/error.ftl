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
    <div class="col-md-12">
            <div class="alert alert-danger alert-dismissible fade in" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4>错误页面</h4>
                <h2 style="text-align: center">${msg!}</h2>
                <p>
                    <button type="button" class="btn btn-default"  data-dismiss="alert"  >确认</button>
                </p>
            </div>
        </div>
        
<#include "user/footer.ftl" />
</body>
</html>