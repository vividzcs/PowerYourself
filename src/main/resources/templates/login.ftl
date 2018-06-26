<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>PowerYourself--掌握你的生活</title>
		<link media="all" type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
		<link media="all" type="text/css" rel="stylesheet" href="css/loginstyle.css">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-offset-3 col-md-6">
					<div class="form-horizontal">
						<span class="heading">PowerYourself--登录</span>
						<div class="form-group">
							<input name="username" type="text" autocomplete="new-password" class="form-control" id="username" value="请输入用户名" />
						</div>
						<div class="form-group">
							<input name="password" type="password" autocomplete="new-password" class="form-control" id="password" value="请输入密码" />
						</div>
						<div class="form-group">
							<li class="li4">
								<input class="submit" type="button" id="btnSubmit" value="登录" onclick="check()">
							</li>

							<li class="li3">
								<a  class="visitor" href="to_register">>>>> 注册</a>
       						</li>
                            <li class="li3">
                                <a  class="visitor" href="to_forget">??忘记密码</a>
                            </li>
                            <li class="li3">
                                <a  class="visitor" href="/">>> 回到首页</a>
                            </li>
							<li class="li3" id="warn" style="display: none"></li>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	
	<script src="js/jquery.min.js"></script>
	<!--动态背景-->
	<script src="js/jquery.gradientify.min.js"></script>
	<script>
		$(document).ready(function() {
			$("body").gradientify({
				gradients: [
					{ start: [49,76,172], stop: [242,159,191] },
					{ start: [255,103,69], stop: [240,154,241] },
					{ start: [33,229,241], stop: [235,236,117] }
				]
			});
		});
	</script>
		
	<!--登录验证-->
    <script type="text/javascript"> 
		$('#username').focus(function () { 
			$('#username').val(''); 
		}) 
		$('#password').focus(function () { 
			$('#password').val(''); 
		}) 
		$('#username').blur(function () { 
			if ($('#username').val() =='') { 
				$('#username').val('用户名不能为空'); 
				$('#username').css({'color':'red'}); 
			} 
		}) 
		$('#username').focus(function () {
			$('#username').css({'color':'#ADADAD'}); 
		});
			
		$('#password').blur(function () { 
			if ($('#password').val() == '') { 
				$('#password').val('111111');
				$('#password').css({'color':'red'}); 
			} 
		}) 
		$('#password').focus(function () {
			$('#password').css({'color':'#ADADAD'}); 
		});

		function check () {
			var data = {'username':$('#username').val(),'password':$('#password').val()}
			$.post('/login',data,function(res){
			    var warn = $("#warn")
			    if(!res.status) {
					//跳转
					// alert(res.data.role)
					if(res.data.role == "10"){
                        window.location.href = "/user/"
					}else{
                        window.location.href = "/admin/"
					}

				}else{
			        warn.css('display','block').css('color','red');
			        warn.html(res.msg);
			        console.log(res)
                    return false;
				}
			},'json')
		}
	</script> 	
</html>
