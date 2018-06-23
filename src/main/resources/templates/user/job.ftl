<#include "header.ftl" />
<!--导航-->

<div class="container">
<div class="row">
<div class="col-md-2">
    <div class="list-group">
        <a href="/user/job/" class="list-group-item active">任务管理</a>
        <a href="/user/job/job_post" class="list-group-item">添加任务</a>
    </div>
</div>
<div class="col-md-10">
<div class="page-header">
    <h1>任务管理</h1>
</div>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="content.html">任务管理</a>
    </li>
    <li>
        <a href="content_post.html">添加任务</a>
    </li>
</ul>
<table class="table">
    <thead>
    <tr>
        <th>任务标题</th>
        <th>作者</th>
        <th>发布时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">PowerYourself--掌握你的生活</th>
        <td>朱朝兵</td>
        <td>2015/08/08</td>
        <td>
            <div role="presentation" class="dropdown">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    操作<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">编辑</a></li>
                    <li><a href="#">删除</a></li>
                </ul>
            </div>
        </td>
    </tr>

    </tbody>
</table>
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
</div>
</div>
<#include "footer.ftl" />
</body>
</html>