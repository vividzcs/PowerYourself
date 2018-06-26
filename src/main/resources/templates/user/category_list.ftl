<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/user/category/" class="list-group-item active">分类管理</a>
                <a href="" role="button"  class="list-group-item" data-toggle="modal" data-target="#myModal">添加分类</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>分类管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="/user/category/">分类列表</a>
                </li>
                <li>
                    <a href="" role="button" data-toggle="modal" data-target="#myModal">添加分类</a>
                </li>
            </ul>
            <table class="table">
                <thead>
                    <tr>
                        <th>分类名</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <#list allCategoryOrdered as ordered>
                    <tr>
                        <td>${ordered[1]}${ordered[0].categoryName}</td>
                        <td>
                            <div role="presentation" class="dropdown">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                    操作<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                   <li><a href="/user/category/edit/${ordered[0].id}">编辑</a></li>
                                   <li><a href="/user/category/delete/${ordered[0].id}" onclick="confirmdelete(event,url=this.href)">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加分类</h4>
            </div>
            <div class="modal-body">
                <div>
                    <div class="form-group">
                        <label for="parentId">所属分类(顶级分类没有所属)</label>
                        <select id="parentId" name="parentId" class="form-control">
                            <#list allCategoryOrdered as ordered>
                                <option value="${ordered[0].id}">${ordered[1]}${ordered[0].categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="addname">分类名</label>
                        <input type="text" id="categoryName" name="categoryName" class="form-control" placeholder="分类名">
                    </div>
                    <div id="warn" class="bounceInRight" style="display: none"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="fmSubmit" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl" />

<script>
    $("#fmSubmit").click(function(){
        var parentId = ($("#parentId").val()!=null) ? $("#parentId").val() : "00000000000000000000000000000000";
        console.log(parentId);
        var categoryName = $("#categoryName").val();
        if(parentId == "" || parentId.trim() == ""){
            $("warn").html("参数错误").css("display","block").css("color","red");
        }
        if(categoryName == "" || categoryName.trim() == ""){
            $("warn").html("参数错误").css("display","block").css("color","red");
        }
        var data = {parentId:parentId,categoryName:categoryName};
        $.post("/user/category/add",data,function(res){
            if(res.status == 0){
                window.location.href="/user/category/";
            }else{
                $("#warn").html(res.msg).css("display","block").css("color","red");
            }
        },"json");
    });

    function confirmdelete(event,url){
        if(!confirm("确认要删除吗")) {

        }else{
            $.post(url,function(res){
                console.log(res);
                if(res.status == 0){
                    //成功
                    window.location.href="/user/category/";
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