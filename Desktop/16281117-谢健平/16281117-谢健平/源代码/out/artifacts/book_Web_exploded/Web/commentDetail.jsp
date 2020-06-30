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


        <div class="layui-col-md9">


            <%--主帖--%>
            <ul class="layui-timeline" id="right-time-list">

                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis"></i>
                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">

                            ${bbs.title }
                        </h3>
                        <div class="layui-timeline-content">
                            <div class="layui-row"
                                 style=" background-color: #eeeeee;margin-bottom: 10px ;padding: 10px ">
                                <div class="layui-col-md3" style=" text-align: center;">
                                    <div class="layui-row">
                                        <img src="${bbs.image }" border="0" style="  width: 170px;">
                                    </div>
                                    <div class="layui-row">

                                        <font
                                                color="#000066" face="Gulim"><b>${bbs.username }</b> </font>

                                    </div>
                                    <div class="layui-row">
                                        发布时间:<fmt:formatDate value="${bbs.addtime}"
                                                             pattern="MM月dd日"/>
                                    </div>
                                </div>

                                <div class="layui-col-md9"
                                     style="padding: 10px;float: left;text-align: left;text-indent: 25px;color: #0C0C0C ">


                                    ${bbs.contents }


                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>


            <%--回复--%>
            <c:forEach items="${rebbsList}" var="rebbs">

                <ul class="layui-timeline" id="right-time-list">

                    <li class="layui-timeline-item">
                        <i class="layui-icon layui-timeline-axis"></i>
                        <div class="layui-timeline-content layui-text">
                            <h3 class="layui-timeline-title">
                                <font
                                        color="#000066" face="Gulim"><b>${rebbs.username }</b> </font>
                                回复时间:<fmt:formatDate value="${rebbs.addtime}"
                                                     pattern="MM月dd日"/>
                            </h3>
                            <div class="layui-timeline-content">
                                <div class="layui-row"
                                     style=" background-color: #eeeeee;margin-bottom: 10px ;padding: 10px ">
                                    <div class="layui-col-md3" style=" text-align: center;">
                                        <img src="${rebbs.image }" border="0" style="float: left; width: 170px;">

                                    </div>
                                    <div class="layui-col-md9"
                                         style="padding: 10px;float: left;text-align: left;text-indent: 25px;color: #0C0C0C ">


                                            ${rebbs.contents }


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
                            <input type="hidden" name="jumpurl" value="web/bbsDetail.action?id=${bbs.bbsid}&"/>
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
            <c:if test="${sessionScope.userid == null }">
                <div style="margin-bottom: 40px;font-weight: bold;font-size: 22px">登录后,可回帖</div>
            </c:if>
            <c:if test="${sessionScope.userid != null }">
                <div class="layui-row" style="margin-bottom: 40px;">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>回复留言</legend>
                    </fieldset>
                    <form class="layui-form" method="post" action="web/rebbs.action" name="myform">


                        <div class="layui-form-item">
                            <label class="layui-form-label">内容</label>
                            <div class="layui-input-block">
                                <script id="contents" name="contents" type="text/plain"></script>
                                <script type='text/javascript' src='../assets/ueditor/ueditor.config_simple.js'></script>
                                <script type='text/javascript' src='../assets/ueditor/ueditor.all.js'></script>
                                <script type='text/javascript' src='../assets/ueditor/lang/zh-cn/zh-cn.js'></script>
                                <script type='text/javascript'>  ue = UE.getEditor('contents');</script>

                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <input type="hidden" name="bbsid" id="bbsid" value="${bbs.bbsid }"/>

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
    <jsp:include page="foot.jsp"></jsp:include>

    <!-- ------------------------------------------------------------------------底部固定区域 -->


</div>
<script src="../assets/layui/layui.js"></script>
<script type="text/html" id="toolbarDemo">

    留言板

</script>
<script>


    layui.use(['form', 'element', 'layer', 'carousel', 'util', 'flow', 'table'], function () {
        var table = layui.table;


        //转换静态表格
        table.init('tabledata', {

            even: true //开启隔行背景

        });
    });


</script>
</body>
</html>

