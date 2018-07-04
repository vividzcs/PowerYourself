<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>来自PwoerYourself的提醒</title>
</head>
<body>
<h1>Title: ${title}</h1>
<h2>Content: ${desc}</h2>
<p>如果被阻拦可以复制链接到新窗口打开(12小时后失效)</p><br/>
<p>您在 ${beganTime} 设置了在 ${remindTime!} 的提醒! 您设置的截止时间是 ${endTime!}</p>
任务已完成: <a href="${done_url}?primary=${user_id}&primary1=${job_id}">完成任务</a><br/>
删除任务: <a href="${delete_url}?primary=${user_id}&primary1=${job_id}">删除任务</a><br/>
去后台修改任务: <a href="${domain}/to_login">登录后台</a>
</body>
</html>