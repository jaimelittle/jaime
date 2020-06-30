<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!--头---------------------------------------------------------------------------------------------->
<div class="layui-header">
    <script src="assets/js/main.js"></script>
    <div class="layui-col-md9 layui-col-xs12 layui-col-sm12  ">
        <a href="<%=basePath%>">
            <div class="layui-logo11  ">新闻网站</div>
        </a>

        <ul class="layui-nav   nav1 " lay-filter="nav-menu-top" id="mainnav">
            <li class="layui-nav-item  "><a href="<%=basePath%>"> 首页 </a></li>


                <c:forEach items="${catalogList}" var="catalog" varStatus="status">
                    <c:if test="${status.count<5}">
                        <li class="layui-nav-item  ">
                            <a href="web/article.action?catalogid=${catalog.catalogid}"
                            > ${catalog.catalogname }</a>
                        </li>
                    </c:if>

                </c:forEach>

            <c:if test="${catalogList.size()>4}">

                <li class="layui-nav-item"><a href="javascript:;">其他</a>
                    <dl class="layui-nav-child ">
                        <c:forEach items="${catalogList}" var="catalog" varStatus="status">
                            <c:if test="${status.count>4}">
                                <dd>
                                    <a href="web/article.action?catalogid=${catalog.catalogid}"
                                    > ${catalog.catalogname }</a>
                                </dd>
                            </c:if>

                        </c:forEach>

                    </dl>
                </li>
            </c:if>



        </ul>

        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" lay-filter="nav-menu-top" id="menu_right">
            <li class="layui-nav-item  ">
                <a href="web/bbs.action">留言板</a>
            </li>

            <c:if test="${sessionScope.userid != null }">

                <%-- 用户--%>
                <li class="layui-nav-item"><a href="javascript:;">用户:${sessionScope.users.username}</a>
                    <dl class="layui-nav-child  ">
                        <dd>
                            <a href="web/prePwd.action"> 修改密码</a>
                        </dd>
                        <dd>
                            <a href="web/preInfo.action"> 修改资料</a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="web/myComment.action">我的评论</a>
                </li>
                <li class="layui-nav-item">
                    <a href="web/exit.action">退出登录</a>
                </li>


            </c:if>
            <c:if test="${sessionScope.userid == null }">

                <li class="layui-nav-item"><a href="web/preLogin.action">登录 </a>
                </li>


                <li class="layui-nav-item"><a href="web/preReg.action">注册 </a>
                </li>
            </c:if>

        </ul>
        <script>
            /* /!*注册*!/
             function reg() {

                 layer.open({
                     type: 2,
                     title: '注册',
                     area: ['500px', '580px'],
                     offset: '50px',
                     content: 'index/preReg.action'
                 });

             }

             /!*登录*!/
             function login() {
                 layer.open({
                     type: 2,
                     title: '登录',
                     area: ['500px', '580px'],
                     offset: '50px',
                     content: 'index/preLogin.action'
                 });
             }*/


            (function ($) {


                // 启用滚动条
                $(document.body).css({
                    "overflow-x": "auto",
                    "overflow-y": "auto"
                });

            })(window.jQuery);
        </script>
        <!--移动端菜单-->
        <ul class="layui-nav  nav_mobi" lay-filter="nav-menu-top" id="menu_mobi" style="display: none">
            <li class="layui-nav-item"><a href="javascript:;">菜单</a>
                <dl class="layui-nav-child  ">

                </dl>
            </li>


        </ul>
    </div>
</div>
<!--头---------------------------------------------------------------------------------------------->



