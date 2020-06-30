<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <jsp:include page="header.jsp"></jsp:include>
    <!--头---------------------------------------------------------------------------------------------->


    <!-- ------------------------------------------------------------------------------内容主体区域 -->

    <div class="layui-row   body-aa">


        <div class="layui-col-md9">

            <section class="demo-section demo-section--light  ">
                <div class="container">
                    <h2 class="title heading">
                        ${article.title }
                    </h2>
                    <div class="title2"> 发布时间：

                        <fmt:formatDate value="${article.addtime}" pattern="MM月dd日"/>
                        &nbsp;&nbsp; &nbsp;&nbsp;查看${article.hits }次&nbsp;
                    </div><!--   <video poster="assets/file/11.jpg" src="assets/video/video1.mp4"
                              data-ckin="default" data-overlay="1" data-title="java基础"></video>-->

                    <h6 class="content">
                        <img alt="" src="${article.image }" width="80%" style="text-align: center">
                        <p>
                            ${article.contents }
                        </p>
                    </h6>
                </div>
            </section>
            <%--评论--%>
            <c:if test="${commentList.size()==0}">
                <div style="margin-bottom: 40px;font-weight: bold;font-size: 22px">暂无评论</div>
            </c:if>
            <c:if test="${commentList.size()>0}">
                <c:forEach items="${commentList}" var="var" varStatus="status">

                    <ul class="layui-timeline" id="right-time-list">

                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis"></i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">
                                    <font color="#000066" face="Gulim"><b>${var.username }</b> </font>
                                    评论时间:<fmt:formatDate value="${var.addtime}" pattern="MM月dd日"/>
                                </h3>
                                <div class="layui-timeline-content">
                                    <div class="layui-row"
                                         style=" background-color: #eeeeee;margin-bottom: 10px ;padding: 10px ">
                                        <div class="layui-col-md3" style=" text-align: center;">
                                            <img src="${var.image }" border="0" style="float: left; width: 70px;">

                                        </div>
                                        <div class="layui-col-md9"
                                             style="padding: 10px;float: left;text-align: left;text-indent: 25px;color: #0C0C0C ">


                                                ${var.memo }


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>

                </c:forEach>
                <table width='98%' border='0' style="margin-top: 8px; margin-left: 5px;">
                    <TR align="right">
                        <TD>
                            <form action="" method="get" name="formpage">
                                <input type="hidden" name="pageCount" value="${pager.pageCount}"/>
                                <!--//用于给上面javascript传值-->
                                <input type="hidden" name="page" value="${pager.pageNumber}"/>
                                <!--//用于给上面javascript传值-->
                                <input type="hidden" name="jumpurl" value="web/articleDetail.action?id=${article.articleid }&"/>
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
            </c:if>
            <c:if test="${sessionScope.userid == null }">
                <div style="margin-bottom: 40px;font-weight: bold;font-size: 22px">登录后,可评论</div>
            </c:if>
            <c:if test="${sessionScope.userid != null }">
                <div class="layui-row" style="margin-bottom: 40px;">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>发表评论</legend>
                    </fieldset>
                    <form class="layui-form" method="post" action="web/addComment.action" name="myform">


                        <div class="layui-form-item">
                            <label class="layui-form-label">内容</label>
                            <div class="layui-input-block">
                                <script id="memo" name="memo" type="text/plain"></script>
                                <script type='text/javascript' src='assets/ueditor/ueditor.config_simple.js'></script>
                                <script type='text/javascript' src='assets/ueditor/ueditor.all.js'></script>
                                <script type='text/javascript' src='assets/ueditor/lang/zh-cn/zh-cn.js'></script>
                                <script type='text/javascript'>  ue = UE.getEditor('memo');</script>

                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <input type="hidden" name="articleid" id="articleid" value="${article.articleid }"/>

                                <input type="submit" class="layui-btn" lay-submit="" name="Submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                        </div>
                    </form>
                </div>

            </c:if>

        </div>
        <div class="layui-col-md3 body-right">
            <jsp:include page="right.jsp"></jsp:include>


        </div>

    </div>


    <!-- ------------------------------------------------------------------------底部固定区域 -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- ------------------------------------------------------------------------底部固定区域 -->


</div>
<script src="assets/layui/layui.js"></script>
<script>
    (function ($) {


        // 主方法
        layui.use(['element', 'layer', 'carousel', 'util', 'flow'], function () {


        });

    })(window.jQuery);
</script>
</body>
</html>
