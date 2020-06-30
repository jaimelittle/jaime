<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>${title}</title>
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <link rel="stylesheet" href="assets/css/workspace.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">


        function check() {

            var repassword = document.myform.repassword.value;
            var repassword1 = document.myform.repassword1.value;
            if (repassword != repassword1) {
                alert('两次密码要一样');
                document.myform.password.focus();
                return false;
            }

        }
    </script>

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

</head>
<body>

<div class="layui-layout layui-layout-admin">


    <!--头---------------------------------------------------------------------------------------------->
    <jsp:include page="../Web/head.jsp"></jsp:include>
    <!--头---------------------------------------------------------------------------------------------->


    <!-- ------------------------------------------------------------------------------内容主体区域 -->

    <div class="layui-row   body-aa">


        <div class="layui-col-md9">

            <blockquote class="layui-elem-quote">修改密码</blockquote>
            <div align="center" style="width: 100%; ">

                <div style="width: 400px;" class="layui-anim layui-anim-up">
                    <h2 style="margin-bottom: 30px; color: white; font-size: 24px;">修改密码</h2>
                    <form class="layui-form" action="web/editpwd.action" method="post" name="myform"
                          onSubmit="return check()">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名:</label>
                            <div class="layui-input-block">
                                <div class="layui-form-mid layui-word-aux">${sessionScope.users.username } </div>

                                <input
                                        type="hidden" name="username" style="width: 260px; height: 30px"
                                        id="username" value="${sessionScope.username }"/>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">旧密码:</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" id="password" required lay-verify="required"
                                       value=""
                                       placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码:</label>
                            <div class="layui-input-block">
                                <input type="password" name="repassword" id="repassword" required lay-verify="required"
                                       value=""
                                       placeholder="请输入确认密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"> 输入新密码:</label>
                            <div class="layui-input-block">
                                <input type="password" name="repassword1" id="repassword1" required
                                       lay-verify="required"
                                       value=""
                                       placeholder="请输入确认密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item" style="margin-top: 30px;">
                            <div class="layui-input-block" style="margin-left: 0px;">
                                <button class="layui-btn layui-btn-normal  " lay-submit
                                        lay-filter="register-form-commit" style="width: 300px;">提交
                                </button>
                            </div>
                        </div>
                    </form>


                </div>
            </div>


        </div>
        <div class="layui-col-md3 body-right">
            <jsp:include page="../Web/right.jsp"></jsp:include>


        </div>

    </div>


    <!-- ------------------------------------------------------------------------底部固定区域 -->
    <jsp:include page="../Web/foot.jsp"></jsp:include>

    <!-- ------------------------------------------------------------------------底部固定区域 -->


</div>
<script src="assets/layui/layui.js"></script>
<script>


    // 主方法
    layui.use(['form', 'element', 'layer', 'carousel', 'util', 'flow'], function () {

        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        //……

        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
    });


</script>
</body>
</html>
