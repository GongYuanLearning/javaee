<%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 2021/12/2
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>天天生鲜-注册</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
  <script type="text/javascript" src="${ctx}/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/register.js"></script>
</head>
<body>
<div class="register_con">
  <div class="l_con fl">
    <a class="reg_logo"><img src="${ctx}/images/logo02.png"></a>
    <div class="reg_slogan">足不出户  ·  新鲜每一天</div>
    <div class="reg_banner"></div>
  </div>

  <div class="r_con fr">
    <div class="reg_title clearfix">
      <h1>用户注册</h1>
      <a href="#">登录</a>
    </div>
    <div class="reg_form clearfix">
      <spring:form
              modelAttribute="registerDto"
              cssStyle=""
              cssClass="form"
              htmlEscape="true"
              action="${ctx}/register/handle"
              method="post"
              enctype="application/x-www-form-urlencoded">
        <ul>
          <li>
            <spring:errors path="*" cssClass="error" />
          </li>
          <li>
            <label>用户名:</label>
            <spring:input
                    path="username" id="username" cssErrorClass="error" />
            <spring:errors path="username" cssClass="error" />
          </li>
          <li>
            <label>密码:</label>
            <spring:password path="pwd" id="pwd" />
            <spring:errors path="pwd" cssClass="error" />
          </li>
          <li>
            <label>确认密码:</label>
            <spring:password path="cpwd" id="cpwd" />
          </li>
          <li>
            <label>邮箱:</label>
            <input type="email" name="email" id="email">
          </li>
          <li>
            <label>电话:</label>
            <input type="text" name="phone" id="phone">
          </li>
          <li>
            <label>角色:</label>
            <div>
              <%--<spring:select
                      path="role"
                      items="${roles}" />--%>
                <%--<spring:select
                        path="role"
                        items="${roles}">
                  <spring:option value="value" label="key" />
                </spring:select>--%>
                <spring:select
                        path="role">
                  <spring:options items="${roles}" />
                </spring:select>
            </div>
          </li>
          <li>
            <label>爱好:</label>
            <%--<c:forEach items="${hobbies}" var="hobby">
              <spring:checkbox
                      path="hobbies"
                      value="${hobby}"
                      label="${hobby}" />
            </c:forEach>--%>
            <spring:checkboxes path="hobbies"
                               items="${hobbies}" />
                                <%--itemLabel="value"
                                itemValue="key" />--%>

            <%--<select name="hobby">
              <c:forEach items="${hobbies}" var="hobby">
                <option value="${hobby}">${hobby}</option>
              </c:forEach>
            </select>--%>
            <span class="error_tip">提示信息</span>
          </li>
          <li class="agreement">
            <input type="checkbox" name="allow" id="allow" checked="checked">
            <label>同意”天天生鲜用户使用协议“</label>
            <span class="error_tip2">提示信息</span>
          </li>
          <li class="reg_sub">
            <input type="submit" value="注 册" name="">
          </li>
        </ul>
      </spring:form>
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
