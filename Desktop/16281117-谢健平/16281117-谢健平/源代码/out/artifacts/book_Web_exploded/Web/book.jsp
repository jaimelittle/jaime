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


        <div class="layui-col-md9   ">


            <ul class="list_aa " type="rollbox">
                <li type="rollitem">
                    <ul id="main-grid-ul" class="product_ul">
                        <c:forEach items="${articleList}" var="article">

                            <li>
                                <a href="web/articleDetail.action?id=${article.articleid }">
                                    <img src="${article.image }"/>
                                </a>
                                <p class="name">
                                    <a href="web/articleDetail.action?id=${article.title }">${article.title }

                                            ${article.hits }次
                                    </a>
                                </p>

                            </li>
                        </c:forEach>


                    </ul>
                </li>
            </ul>

            <table width='98%' border='0' style="margin-top: 8px; margin-left: 5px;">
                <TR align="right">
                    <TD>
                        <form action="" method="get" name="formpage">
                            <input type="hidden" name="pageCount" value="${pager.pageCount}"/>
                            <!--//用于给上面javascript传值-->
                            <input type="hidden" name="page" value="${pager.pageNumber}"/>
                            <!--//用于给上面javascript传值-->
                            <input type="hidden" name="jumpurl" value="web/article.action?<c:if test="${catalogid!=null}">catalogid=${catalogid}&</c:if>"/>
                            <!--//用于给上面javascript传值-->
                            <a href="javascript:;" onClick="PageTop()"><strong>首页</strong></a>&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onClick='PagePre()'><strong>上一页</strong></a>&nbsp;&nbsp;&nbsp;

                            共${pager.rowCountTotal}条记录, 共计${pager.pageCount}页,
                            当前第${pager.pageNumber}页&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onClick="PageNext()"><strong>下一页</strong></a>&nbsp;&nbsp;&nbsp;

                            <a href="javascript:;" onClick="PageLast()"><strong>尾页</strong></a>
                        </form>
                        <script type="text/javascript" src="../assets/js/page.js"></script>
                    </TD>
                </TR>
            </table>

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


        });

    })(window.jQuery);
</script>
</body>
</html>
