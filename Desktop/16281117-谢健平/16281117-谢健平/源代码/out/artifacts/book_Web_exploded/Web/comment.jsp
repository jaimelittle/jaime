<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <table lay-filter="tabledata">
                <thead>
                <tr>
                    <th lay-data="{align:'center',field:'a' }">标题</th>
                    <td lay-data="{align:'center',field:'b' }">日期</td>
                    <td lay-data="{align:'center',field:'e' }">发布人</td>
                    <td lay-data="{align:'center',field:'c' }">点击数</td>
                    <td lay-data="{align:'center',field:'d' }">回复数</td>
                </tr>
                </thead>
                <c:forEach items="${bbsList}" var="var">
                    <tr>
                        <td>
                            <a href="web/bbsDetail.action?id=${var.bbsid }">
                                <font class="p14"> ${var.title }</font>
                            </a>

                        </td>
                        <td>
                            <div class="f10">
                                <fmt:formatDate value="${var.addtime}" pattern="MM月dd日"/>

                            </div>

                        </td>
                        <td>

                                ${var.username }
                        </td>
                        <td>${var.hits }</td>
                        <td>${var.repnum }</td>


                    </tr>
                </c:forEach>
            </table>
            <table width='98%' border='0' style="margin-top: 8px; margin-left: 5px;">
                <TR align="right">
                    <TD>
                        <form action="" method="get" name="formpage">
                            <input type="hidden" name="pageCount" value="${pager.pageCount}"/>
                            <!--//用于给上面javascript传值-->
                            <input type="hidden" name="page" value="${pager.pageNumber}"/>
                            <!--//用于给上面javascript传值-->
                            <input type="hidden" name="jumpurl" value="web/bbs.action?"/>
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
                <div style="margin-bottom: 40px;font-weight: bold;font-size: 22px">登录后,可发帖</div>
            </c:if>
            <c:if test="${sessionScope.userid != null }">
                <div class="layui-row">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>发表留言</legend>
                    </fieldset>
                    <form class="layui-form" method="post" action="web/addbbs.action" name="myform">
                        <div class="layui-form-item">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-block">
                                <input type="text" lay-verify="required" required="required"
                                       class="layui-input" placeholder="请输入标题"
                                       name="title" style="width: 160px" id="title"/></div>
                        </div>

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
