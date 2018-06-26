<#include "header.ftl" />

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="/user/category/" class="list-group-item active">分类管理</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>分类管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="/user/category/">分类列表</a>
                </li>
                <li class="active">
                    <a href="">编辑分类</a>
                </li>
            </ul>
            <form action="" class="mar_t15">
                <div>
                    <div class="form-group" >
                        <label for="parentId">所属分类(顶级分类没有所属)</label>
                        <select id="parentId" name="parentId" class="form-control">
                            <#list allCategoryOrdered as ordered>
                                <#if ordered[0].id == category.id>
                                    <option value="${ordered[0].id}" selected>${ordered[1]}${ordered[0].categoryName}</option>
                                <#else >
                                    <option value="${ordered[0].id}">${ordered[1]}${ordered[0].categoryName}</option>
                                </#if>

                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="addname">分类名</label>
                        <input type="text" id="categoryName" name="categoryName" class="form-control" placeholder="分类名" value="${category.categoryName}">
                        <input type="hidden" id="id" name="id" value="${category.id}">
                    </div>
                    <div id="warn" class="bounceInRight" style="display: none"></div>
                </div>
                <div class="form-group">
                    <button type="button" id="fmSubmit" class="btn btn-primary pull-right">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "footer.ftl" />

<script>
    $("#fmSubmit").click(function(){
        // var parentId = $("#parentId").val();
        var parentId = ($("#parentId").val()!=null) ? $("#parentId").val() : "00000000000000000000000000000000";
        var categoryName = $("#categoryName").val();
        var id = $("#id").val()
        if(categoryName.trim() == "" || parentId.trim() == "" || id.trim() == ""){
            $("warn").html("参数错误").css("display","block").css("color","red");
        }
        var data = {id:id,parentId:parentId,categoryName:categoryName};
        $.post("/user/category/update",data,function(res){
            if(res.status == 0){
                window.location.href="/user/category/";
            }else{
                $("#warn").html(res.msg).css("display","block").css("color","red");
            }
        },"json");
    });

</script>
</body>
</html>