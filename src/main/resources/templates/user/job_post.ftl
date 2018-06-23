<#include "header.ftl" />

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
                    <label for="content">文章内容</label>
                    <textarea id="content" class="form-control" rows="15" cols="10" placeholder="请输入文章正文部分"></textarea>
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
</body>
</html>