<#include "header.ftl" />
<!--导航-->

<div class="container">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">网站数据统计</div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>统计项目</th>
                        <th>数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">正常任务</th>
                        <td>${normal}</td>
                    </tr>
                    <tr>
                        <th scope="row">完成任务</th>
                        <td>${finished}</td>
                    </tr>
                    <tr>
                        <th scope="row">过期任务</th>
                        <td>${overdate}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftl" />
</body>
</html>