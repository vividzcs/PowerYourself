<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/admin/" class="list-group-item">用户管理</a>
                <a href="/admin/to_search" class="list-group-item active">用户搜索</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>用户管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="/admin/">用户列表</a>
                </li>
                <li  class="active">
                    <a href="/admin/to_search">用户搜索</a>
                </li>
            </ul>
            <form action="/admin/search/" method="post" class="uesr_search">
                <div class="form-group">
                    <label for="search_item">选择用户组</label>
                    <select id="search_item" class="form-control" name="search_item">
                        <option value="id">UUID</option>
                        <option value="username">用户名</option>
                        <option value="email">邮箱</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="name">输入搜索值</label>
                    <input type="texte" id="name" name="search_value" class="form-control" placeholder="搜索值">
                </div>

                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>

<#include "footer.ftl" />