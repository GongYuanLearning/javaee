<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<%
    String username = (String) session.getAttribute("username");
    if(null != username) {
        response.sendRedirect("index.jsp");
    }
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>天天生鲜-登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <script type="text/javascript" src="${ctx}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $(document).ready(() => {
            $('form').submit((event) => {
                event.preventDefault();
                $.ajax({
                    url: '${ctx}/login',
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        username: $('input[name="username"]').val(),
                        pwd: $('input[name="pwd"]').val(),
                    }),
                    success: () => {
                        window.location.url = "${ctx}/index";
                    },
                    error: () => {
                    }
                });
            });
        })
    </script>
</head>
<body>
<div class="login_top clearfix">
    <a href="index.html" class="login_logo"><img src="${ctx}/images/logo02.png"></a>
</div>

<div class="login_form_bg">
    <div class="login_form_wrap clearfix">
        <div class="login_banner fl"></div>
        <div class="slogan fl">日夜兼程 · 急速送达</div>
        <div class="login_form fr">
            <div class="login_title clearfix">
                <h1>用户登录</h1>
                <a href="#">立即注册</a>
            </div>
            <div class="form_input">
                <form action="${ctx}/login" method="post">
                    <input type="text" name="username" value="${username}" class="name_input" placeholder="请输入用户名">
                    <div class="user_error">输入错误</div>
                    <input type="password" name="pwd" class="pass_input" placeholder="请输入密码">
                    <div class="pwd_error">输入错误</div>
                    <div class="more_input clearfix">
                        <input type="checkbox" name="rememberMe" value="true">
                        <label>一周之内免登陆</label>
                        <a href="#">忘记密码</a>
                    </div>
                    <input type="submit" name="" value="登录" class="input_submit">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer no-mp">
    <div class="foot_link">
        <a href="#">关于我们</a>
        <span>|</span>
        <a href="#">联系我们</a>
        <span>|</span>
        <a href="#">招聘人才</a>
        <span>|</span>
        <a href="#">友情链接</a>
    </div>
    <p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
    <p>电话：010-****888    京ICP备*******8号</p>
</div>

</body>
</html>
