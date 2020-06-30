<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

            <div class="layui-carousel" id="carousel-ad">
                <div carousel-item="">
                    <c:forEach items="${favList}" var="article">
                        <div>
                            <a href="web/articleDetail.action?id=${article.articleid }">
                                <img src="${article.image }"/>
                            </a>
                        </div>

                    </c:forEach>
                </div>
            </div>
            <div class="card_box">
                <div class="layui-row layui-col-space15">


                    <%--前四个栏目--%>
                    <c:forEach items="${frontList}" var="article" varStatus="status">
                        <c:if test="${status.index<6}">
                            <div class="layui-col-md6">
                                <div class="layui-card">
                                    <div class="layui-card-header">
                                        <a href="web/article.action?catalogid=${article.catalogid }"> ${article.catalogname }</a>&nbsp;&nbsp;
                                    </div>
                                    <div class="layui-card-body">

                                        <ul class="textlink_ul">
                                            <c:forEach items="${article.articleList}" var="a" varStatus="status">
                                                <c:if test="${status.count<5}">
                                                    <li>

                                                        <a href="web/articleDetail.action?id=${a.articleid }">
														                                                            <c:if test="${fn:length(a.title)>18 }">${ fn:substring( a.title ,0,18)} ...</c:if>
                                                            <c:if test="${fn:length(a.title)<=18 }">${  a.title } </c:if>

														</a>

                                                        <span>                                                                           <fmt:formatDate
                                                                value="${a.addtime}" pattern="MM月dd日"/>

                                               </span>

                                                    </li>
                                                </c:if>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>





                        <%--置顶--%>
                    <ul class="list_aa " type="rollbox">
                        <li type="rollitem">
                            <ul id="main-grid-ul" class="product_ul ">
                                <c:forEach items="${topList}" var="article">

                                    <li>
                                        <a href="web/articleDetail.action?id=${article.articleid }">
                                            <img src="${article.image }"/>
                                        </a>
                                        <p class="name">
                                            <a href="web/articleDetail.action?id=${article.title }">${article.title }
                                            </a>
                                        </p>

                                    </li>
                                </c:forEach>


                            </ul>
                        </li>
                    </ul>





                        <%--剩余的栏目--%>
                    <c:forEach items="${frontList}" var="article" varStatus="status">

                        <c:if test="${status.index>5}">
                            <div class="layui-col-md6">
                                <div class="layui-card">
                                    <div class="layui-card-header">
                                        <a href="web/article.action?catalogid=${article.catalogid }"> ${article.catalogname }</a>&nbsp;&nbsp;
                                    </div>
                                    <div class="layui-card-body">

                                        <ul class="textlink_ul">
                                            <c:forEach items="${article.articleList}" var="a" varStatus="status">
                                                <c:if test="${status.count<5}">
                                                    <li>

                                                        <a href="web/articleDetail.action?id=${a.articleid }">
														                                                            <c:if test="${fn:length(a.title)>18 }">${ fn:substring( a.title ,0,18)} ...</c:if>
                                                            <c:if test="${fn:length(a.title)<=18 }">${  a.title } </c:if>

														</a>

                                                        <span>                                                                           <fmt:formatDate
                                                                value="${a.addtime}" pattern="MM月dd日"/>

                                               </span>

                                                    </li>
                                                </c:if>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>


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

</script>
</body>
</html>

