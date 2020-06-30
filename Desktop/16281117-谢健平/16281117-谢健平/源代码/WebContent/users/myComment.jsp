<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <table lay-filter="tabledata">
                <thead>
                <tr>
                    <th lay-data="{align:'center',field:'a' }">小说标题</th>
                    <td lay-data="{align:'center',field:'b' }">添加日期</td>

                    <td lay-data="{align:'center',field:'d' }">评论内容</td>
                    <td lay-data="{align:'center',field:'e' }">操作</td>
                </tr>
                </thead>
                <c:forEach items="${commentList}" var="comment">
                    <tr>
                        <td>${comment.title}</td>
                        <td>
                            <fmt:formatDate value="${comment.addtime}" pattern="MM月dd日"/>
                        </td>


                        <td>${comment.memo}</td>
                        <td>

                            <a href="web/delete.action?id=${comment.commentid}" onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a>

                        </td>
                    </tr>
                </c:forEach>
            </table>


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
<script type="text/html" id="toolbarDemo">

    我的评论

</script>
<script>


    layui.use(['form', 'element', 'layer', 'carousel', 'util', 'flow', 'table'], function () {
        var table = layui.table;


        //转换静态表格
        table.init('tabledata', {

            even: true //开启隔行背景
            , limit: 100

        });
    });


</script>
</body>
</html>
