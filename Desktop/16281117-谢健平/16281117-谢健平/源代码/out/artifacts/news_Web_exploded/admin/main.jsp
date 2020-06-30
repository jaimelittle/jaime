<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>"/>
    <title>新闻网站</title>
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
    <style>
        .layui-logo11 {
            font-weight: bold;
            font-size: 24px;
            color: #ffffff;

            margin-right: 20px;
            top: 0;

            height: 100%;
            line-height: 60px;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="catalog" class="catalog">


    <ul class="layui-nav layui-bg-green  layui-nav-tree layui-nav-side" lay-filter="nav-menu-top">


        <li class="layui-nav-item  layui-nav-itemed"><a href="javascript:;">管理员</a>
            <dl class="layui-nav-child">
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/admin/create.action">新增管理员</a>
                </dd>
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/admin/getAll.action">管理员列表</a>
                </dd>


            </dl>
        </li>

        <li class="layui-nav-item"><a href="javascript:;">内容分类</a>
            <dl class="layui-nav-child">
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/catalog/create.action">新增内容分类</a>
                </dd>
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/catalog/getAll.action">内容分类列表</a>
                </dd>


            </dl>
        </li>

        <li class="layui-nav-item  layui-nav-itemed"><a href="javascript:;">网站内容</a>
            <dl class="layui-nav-child">
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/article/create.action">新增网站内容</a>
                </dd>
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/article/getAll.action">网站内容列表</a>
                </dd>


            </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">留言交流列表</a>
            <dl class="layui-nav-child">
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/bbs/getAll.action">留言交流列表</a>
                </dd>
                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/rebbs/getAll.action">回复列表</a>
                </dd>


            </dl>
        </li>

        <li class="layui-nav-item">
            <a class="link" href="javascript:void(0);" id="admin/users/getAll.action"> 用户列表</a>

        </li>


        <li class="layui-nav-item">
            <a class="link" href="javascript:void(0);" id="admin/comment/getAll.action"> 评论列表</a>

        </li>

        <%--    </c:if>--%>
        <li class="layui-nav-item layui-nav-itemed">

            <a href="javascript:;">


                <b>管理员</b>


                ${sessionScope.adminname}

            </a>
            <dl class="layui-nav-child">

                <dd>
                    <a class="link" href="javascript:void(0);" id="admin/editpwd.jsp">修改密码</a>

                </dd>

                <dd>
                    <a href="javascript:void(0);" onclick="logout()">退出登录</a>
                </dd>
            </dl>
        </li>
    </ul>

    <ul class=" layui-layout-right">
        <div class="layui-logo11">新闻网站</div>


    </ul>
</div>

<div class="layui-row" style="margin-left: 200px">

    <iframe id="mainFrame" src="admin/welcome.jsp" width="100%" frameborder="0" onload="changeFrameHeight()">
    </iframe>


</div>

<script src="assets/layui/layui.js"></script>
<!-- <script src="assets/layui/layui.all.js"></script> -->
<script>
    function changeFrameHeight() {
        var ifm = document.getElementById("mainFrame");
        var hh = document.documentElement.clientHeight - $("#catalog").height() - 15;

        ifm.height = hh;//(catalog顶部高度)
    }

    /*点击菜单后,从这里跳转*/
    $(".link").each(function () {
        $(this).on("click", function () {
            var url = "";
            var id = $(this).attr("id");

            $("#mainFrame").attr("src", id);
        });
    });

    /*

        function editpwd() {
            layer.open({
                type: 2,
                title: '修改密码',
                area: ['500px', '280px'],
                offset: '50px',
                content: 'reg.html', // 新的页面地址
                end: function (index, layero) {

                }
            });
        }
    */


    function logout() {
        layer.confirm('确定退出?', function (index) {

            layer.close(index);
            window.location.href = '<%=basePath%>/admin/login/exit.action';


        });
    }
</script>

<script type="text/javascript">


    (function ($) {


        var ItemType = '';

        // 主方法
        layui.use(['element', 'table', 'layer', 'form'], function () {

            var table = layui.table, element = layui.element, layer = layui.layer, form = layui.form;


        });

    })(window.jQuery);
</script>
</body>
</html>

