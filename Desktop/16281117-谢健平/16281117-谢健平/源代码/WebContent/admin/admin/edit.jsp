<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>"/>
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
</head>

<body>


<div class="layui-row">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>编辑</legend>
    </fieldset>
    <form class="layui-form" method="post" action="admin/admin/update.action"
          name="myform" onsubmit="return check()">
        <input
                type="hidden" name="adminid" value="${admin.adminid}"/>
        <input
                type="hidden" name="password" id="password"
                value="${admin.password}"/>

        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block"><input type="text" lay-verify="required" required="required"
                                                  class="layui-input" name="username"
                                                  style="width: 160px" id="username" value="${admin.username}"/></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block"><input type="text" lay-verify="required" required="required"
                                                  class="layui-input" name="realname"
                                                  style="width: 160px" id="realname" value="${admin.realname}"/></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-block"><input type="text" lay-verify="required" required="required"
                                                  class="layui-input" name="contact"
                                                  style="width: 160px" id="contact" value="${admin.contact}"/></div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block"><input type="submit" class="layui-btn" lay-submit="" name="Submit"
                                                  value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>

    </form>
</div>

<script src="assets/layui/layui.js"></script>
<script>
    function check() {
        if (document.myform.username.value == '') {
            alert('请输入用户名');
            return false;
        }
        if (document.myform.password.value == '') {
            alert('请输入密码');
            return false;
        }
        if (document.myform.realname.value == '') {
            alert('请输入姓名');
            return false;
        }
        if (document.myform.contact.value == '') {
            alert('请输入联系方式');
            return false;
        }
    }

    layui.use('form', function () {
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        //……

        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
    });
</script>
</body>
</html>