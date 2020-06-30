<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
</head>
<body>


<div class="layui-row" style="margin-top: 50px;">
    <div class="layui-col-lg12">

        <div class="layui-text" style="text-align: center;font-size: 32px;font-weight: bold;padding: 150px">
            <h1>
                欢迎使用书籍推荐网站
            </h1>
        </div>
    </div>
</div>

<script src="assets/layui/layui.js"></script>

</body>
</html>