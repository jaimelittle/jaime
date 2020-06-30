<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>${title}</title>
    <link rel="stylesheet" href="../assets/layui/css/layui.css">
    <link rel="stylesheet" href="../assets/css/workspace.css">
    <script src="../assets/js/jquery-3.2.1.js"></script>

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

</head>
<body>

<div class="layui-layout layui-layout-admin">


    <!--头---------------------------------------------------------------------------------------------->
    <jsp:include page="head.jsp"></jsp:include>
    <!--头---------------------------------------------------------------------------------------------->


    <!-- ------------------------------------------------------------------------------内容主体区域 -->

    <div class="layui-row   body-aa">


        <div class="layui-col-md9">

            <blockquote class="layui-elem-quote"> 用户登录</blockquote>
            <div align="center" style="width: 100%; margin-top: 30px;">

                <div style="width: 300px;padding-right: 30px" class="layui-anim layui-anim-up">
                    <h2 style="margin-bottom: 30px; color: white; font-size: 24px;">登录</h2>
                    <form class="layui-form" action="web/login.action" method="post" name="myform">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名:</label>
                            <div class="layui-input-block">
                                <input type="text" name="username" required lay-verify="required" value="jaime"
                                       placeholder="请输入用户名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码:</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" required lay-verify="required" value="123456"
                                       placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-normal  " lay-submit
                                        lay-filter="register-form-commit">登录
                                </button>
                            </div>
                        </div>
                    </form>


                </div>
            </div>



        </div>
        <div class="layui-col-md3 body-right">
            <jsp:include page="right.jsp"></jsp:include>


        </div>

    </div>


    <!-- ------------------------------------------------------------------------底部固定区域 -->
    <jsp:include page="foot.jsp"></jsp:include>

    <!-- ------------------------------------------------------------------------底部固定区域 -->


</div>
<script src="../assets/layui/layui.js"></script>
<script>
    (function ($) {


        // 主方法
        layui.use(['element', 'layer', 'carousel', 'util', 'flow'], function () {

            var carousel = layui.carousel;


            // 常规轮播
            carousel.render({
                elem: '#carousel-ad',
                width: '100%',
                height: '300px',
                interval: 3000
            });


        });

    })(window.jQuery);
</script>
</body>
</html>


