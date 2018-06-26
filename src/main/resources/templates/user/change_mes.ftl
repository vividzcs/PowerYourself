<#include "header.ftl" />
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/user/to_change_mes" class="list-group-item active">修改密码</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>修改密码</h1>
            </div>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="/user/to_change_pass">修改密码</a>
                </li>
                <li style="color: red;">${msg!}</li>
            </ul>
            <form action="/user/change_mes" class="mar_t15" method="post">
                <div class="form-group">
                    <label for="title">用户名</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="请输入修改后的用户名" value="${userDto.username!}">
                </div>
                <div class="form-group">
                    <label for="title">密码</label>
                    <input type="text" id="password" name="password" class="form-control" placeholder="请输入修改后的密码" value="">
                </div>
                <div class="form-group">
                    <label for="title">邮箱</label>
                    <input type="text" id="email" name="email" class="form-control" placeholder="请输入修改后的用户名" value="${userDto.email!}">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default pull-right">发布任务</button>
                </div>
            </form>

        </div>
    </div>
</div>

<#include "footer.ftl" />
</body>
</html>