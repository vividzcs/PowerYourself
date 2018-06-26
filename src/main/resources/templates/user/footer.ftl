<!--footer-->
<footer class="navbar-fixed-bottom" style="background:#f8f8f8;opacity: 1">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>
                    Copyright&nbsp;©&nbsp;2018-${.now?string("yyyy")}&nbsp;&nbsp;<a href="http://todo.nwuer.com">todo.nwuer.com</a>&nbsp;&nbsp;陕ICP备16019866号
                </p>
            </div>
        </div>
    </div>
</footer>
<!--footer-->


<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script>
    $('.big-nav li').click(function(event) {

        $(this).tab('show');
    });
</script>