<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%><!DOCTYPE html>
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
<form action="" method="post">
    <div class="layui-row">
        <div class="layui-col-md3">
            <div class="layui-form-item" style="margin-top: 20px; padding-left: 20px; margin-bottom: 10px;">
                <div class="layui-input-block" style="margin-left: 0px;">
                    <input type="text" name="keyword" lay-verify="required" placeholder="请输入关键字 " value="${keyword}"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <button class="layui-btn btn-chapter-search"
                    style="margin-top: 20px; margin-right: 5px; margin-bottom: 10px; margin-right: 20px;">
                <i class="layui-icon">&#xe615;</i> 搜索
            </button>

        </div>
    </div>
</form>
<table lay-filter="tabledata">
    <thead>
    <tr>
        <th lay-data="{align:'center',field:'a' }">图书名称</th>
        <th lay-data="{align:'center',field:'a1' }">作者</th>
        <th lay-data="{align:'center',field:'b' }">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookinfoList}" var="bookinfo">
        <tr>
            <td align="center">${bookinfo.bookinfoname}</td>
            <td align="center">${bookinfo.authorname}</td>
            <td align="center">
                <a
                        href="admin/bookinfo/getById.action?id=${bookinfo.bookinfoid}">编辑</a>
                <a
                        href="admin/bookinfo/delete.action?id=${bookinfo.bookinfoid}"
                        onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<table width='98%' border='0' style="margin-top: 8px; margin-left: 5px;">
    <TR align="right">
        <TD>
            <form action="" method="get" name="formpage">
                <input type="hidden" name="pageCount" value="${pager.pageCount}"/>
                <!--//用于给上面javascript传值-->
                <input type="hidden" name="page" value="${pager.pageNumber}"/>
                <!--//用于给上面javascript传值-->
                <input type="hidden" name="jumpurl" value="admin/bookinfo/getAll.action?"/>
                <!--//用于给上面javascript传值-->
                <a href="javascript:;" onClick="PageTop()"><strong>首页</strong></a>&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" onClick='PagePre()'><strong>上一页</strong></a>&nbsp;&nbsp;&nbsp;

                共${pager.rowCountTotal}条记录, 共计${pager.pageCount}页,
                当前第${pager.pageNumber}页&nbsp;&nbsp;&nbsp;
                <a href="javascript:;" onClick="PageNext()"><strong>下一页</strong></a>&nbsp;&nbsp;&nbsp;

                <a href="javascript:;" onClick="PageLast()"><strong>尾页</strong></a>
            </form>
            <script type="text/javascript" src="assets/js/page.js"></script>
        </TD>
    </TR>
</table>
<script src="assets/layui/layui.js"></script>
<script type="text/html" id="toolbarDemo">

    书籍 信息列表

</script>
<script>
    layui.use(['table', 'element', 'layer', 'carousel', 'util', 'flow'], function () {
        var table = layui.table;


//转换静态表格
        table.init('tabledata', {

            even: true //开启隔行背景

        });
    });
</script>
</body>
</html>