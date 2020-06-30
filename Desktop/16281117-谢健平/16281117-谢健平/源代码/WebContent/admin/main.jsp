<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <base href="<%=basePath%>"/>
    <title>书籍推荐网站</title>
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" id="banner">
        <div class="layui-logo">书籍推荐网站</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <!-- <ul class="layui-nav layui-layout-left">
             <li class="layui-nav-item"><a href="">XX</a></li>
             <li class="layui-nav-item"><a href="">XX</a></li>
             <li class="layui-nav-item"><a href="">XX</a></li>
             <li class="layui-nav-item">
                 <a href="javascript:;">其它系统</a>
                 <dl class="layui-nav-child">
                     <dd><a href=""> XX</a></dd>
                     <dd><a href="">XX </a></dd>
                     <dd><a href="">XX</a></dd>
                 </dl>
             </li>
         </ul>-->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <!--<a href="javascript:;">
                    &lt;!&ndash;<img src="http://t.cn/RCzsdCq" class="layui-nav-img">&ndash;&gt;
                    ADMIN
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">XX</a></dd>
                    <dd><a href="">XX</a></dd>
                </dl>-->
                <b>管理员</b>


                ${sessionScope.adminname} </li>

            <li class="layui-nav-item">
            <a href="javascript:void(0);" onclick="logout()">退出登录</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理员 </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/admin/create.action">新增管理员</a>
                        </dd>
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/admin/getAll.action">管理员列表</a>
                        </dd>

                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">书籍分类</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/catalog/create.action">新增书籍分类</a>
                        </dd>
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/catalog/getAll.action">书籍分类列表</a>
                        </dd>


                    </dl>
                </li>

                <li class="layui-nav-item"><a href="javascript:;">书籍管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/bookinfo/create.action">新增书籍</a>
                        </dd>
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/bookinfo/getAll.action">书籍列表</a>
                        </dd>


                    </dl>
                </li>

                <li class="layui-nav-item  layui-nav-itemed"><a href="javascript:;">书籍目录章节</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/article/create.action">新增书籍目录章节</a>
                        </dd>
                        <dd>
                            <a class="link" href="javascript:void(0);" id="admin/article/getAll.action">书籍目录章节列表</a>
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
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="mainFrame" src="admin/welcome.jsp" width="100%" frameborder="0" onload="changeFrameHeight()">
        </iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 书籍推荐网站
    </div>
</div>
<script src="assets/layui/layui.js"></script>
<script>
    function changeFrameHeight() {
        var ifm = document.getElementById("mainFrame");
        var hh = document.documentElement.clientHeight - $("#banner").height() - 55;

        ifm.height = hh;//(banner顶部高度)
    }

    /*点击菜单后,从这里跳转*/
    $(".link").each(function () {
        $(this).on("click", function () {
            var url = "";
            var id = $(this).attr("id");

            $("#mainFrame").attr("src", id);
        });
    });  //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });

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


