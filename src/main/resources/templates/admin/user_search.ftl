<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="user_list.html" class="list-group-item">用户管理</a>
                <a href="uesr_search.html" class="list-group-item active">用户搜索</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>用户管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="user_list.html">用户列表</a>
                </li>
                <li  class="active">
                    <a href="uesr_search.html">用户搜索</a>
                </li>
            </ul>
            <form action="#" class="uesr_search">
                <div class="alert alert-info" role="alert">
                    <strong>技巧提示：</strong>
                    支持模糊搜索和匹配搜索，匹配搜索使用*代替！
                </div>
                <div class="form-group">
                    <label for="name">用户名</label>
                    <input type="texte" id="name" class="form-control" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="uid">UID</label>
                    <input type="text" id="uid" class="form-control" placeholder="输入用户UID">
                </div>
                <div class="form-group">
                    <label for="yonghuzu">选择用户组</label>
                    <select id="yonghuzu" class="form-control">
                        <option>限制会员</option>
                        <option>新手上路</option>
                        <option>组册会员</option>
                        <option>中级会员</option>
                        <option>高级会员</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>

<#include "footer.ftl" />