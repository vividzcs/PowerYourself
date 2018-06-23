<#include "header.ftl" />
<!--导航-->

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h1>TAG标签管理</h1>
            </div>
            <div class="col-md-12 pad0">
                <form>
                    <div class="col-md-10">
                        <input class="form-control" placeholder="请输入要添加的标签">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-default">添加</button>
                    </div>
                </form>
            </div>
            <div class="col-md-12 taglist">
                <div class="alert alert-info alert-dismissible pull-left" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>bootstrap</strong>
                </div>
                <div class="alert alert-info alert-dismissible  pull-left" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>技术专家</strong>
                </div>
                <div class="alert alert-info alert-dismissible  pull-left" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>前端课程</strong>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftl" />
</body>
</html>