<#include "header.ftl" />

<!--警告框-->
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success alert-dismissible fade in" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4>您的上次登录时间是</h4>
                <h2 style="text-align: center">${(Session.currentUser.lastLoginDatetime?string("yyyy/MM/dd HH:mm:ss"))!}</h2>
                <p>
                    <button type="button" class="btn btn-default"  data-dismiss="alert"  >确认</button>
                </p>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftl" />
</body>
</html>