<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/" class="list-group-item active">人员管理</a>
                <a href="/admin/to_search" role="button" class="list-group-item" data-toggle="modal" data-target="#myModal">人员搜索</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>人员管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="/admin/">人员列表</a>
                </li>
                <li>
                    <a href="/admin/to_search">人员搜索</a>
                </li>
            </ul>
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>人员名</th>
                    <th>Email</th>
                    <th>上一次登录</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#if allUser?exists && (allUser.list?size>0)>
                    <#list allUser.list as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.lastLoginDatetime!}</td>
                        <td>${user.activated?string("已激活","未激活")}</td>
                        <td>
                            <div role="presentation" class="dropdown">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#"
                                        role="button" aria-haspopup="true" aria-expanded="false">
                                    操作<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="/admin/freeze/${user.id}"
                                           onclick="confirmdelete(event,url=this.href)">冻结</a></li>
                                    <li><a href="/admin/active/${user.id}"
                                           onclick="confirmdelete(event,url=this.href)">激活</a></li>
                                    <li><a href="/admin/delete/${user.id}"
                                           onclick="confirmdelete(event,url=this.href)">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <nav class="pull-right">
                <ul class="pagination">
        <#if allUser.hasPreviousPage>
            <li><a href="/admin/search/${allUser.prePage}?search_item=${search_item}&search_value=${search_value}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
            </li>
        <#else >
            <li class="disabled"><span aria-hidden="true">&laquo;</span></li>
        </#if>

        <#list allUser.navigatepageNums as nav>
            <li ${(nav == allUser.pageNum)?string("class='active'","")}><a href="/admin/search/${nav}?search_item=${search_item}&search_value=${search_value}">${nav}</a></li>
        </#list>
        <#if allUser.hasNextPage>
            <li><a href="/admin/search/${allUser.nextPage}?search_item=${search_item}&search_value=${search_value}"><span aria-hidden="true">&raquo;</span></a></li>
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
    function confirmdelete(event, url) {
        if (!confirm("确认要冻结或删除吗")) {

        } else {
            $.post(url, function (res) {
                console.log(res);
                if (res.status == 0) {
                    //成功
                    window.location.href = "/admin/";
                } else {
                    alert(res.msg)
                }
            })
        }
        event.returnValue = false;

    }
</script>
</body>
</html>