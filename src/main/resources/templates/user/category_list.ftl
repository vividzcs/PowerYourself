<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="category_list.ftl" class="list-group-item active">分类管理</a>
                <a href="" role="button"  class="list-group-item" data-toggle="modal" data-target="#myModal">添加分类</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>分类管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="category_list.ftl">分类列表</a>
                </li>
                <li>
                    <a href="" role="button" data-toggle="modal" data-target="#myModal">添加分类</a>
                </li>
            </ul>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>分类名</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <#list allCategory as cate>
                    <tr>
                        <th scope="row">${cate.id}</th>
                        <td>${cate.categoryName}</td>
                        <td>
                            <div role="presentation" class="dropdown">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                    操作<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                   <li><a href="/user/category/edit/${cate.id}">编辑</a></li>
                                   <li><a href="/user/category/delete/${cate.id}" onclick="confirmdelete(event,url=this.href)">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </#list>
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
                        <label for="parentId">所属分类</label>
                        <select id="parentId" name="parentId" class="form-control">
                            <option value="0">顶级分类</option>
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
        var parentId = $("#parentId").val();
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