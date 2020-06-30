<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!-- ------------------------------------------------------------------------底部固定区域 -->
<div class="layui-footer1">
    © 新闻网站
    联系电话:010-82266699 京ICP备05082115号  <a href="<%=basePath%>admin/login.jsp">管理员登录</a>

</div>
<!-- ------------------------------------------------------------------------底部固定区域 -->
