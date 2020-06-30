<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- ------------------------------------------------------------------------右侧固定区域 -->
<div class="layui-row">
    <form method="post" action="web/article.action">
        <div class="layui-col-md8  ">
            <div class="">
                <div class="layui-input-block" style="margin-left: 0px;">
                    <input type="text" name="keyword" lay-verify="required" placeholder="请输入关键字"
                           autocomplete="off"
                           class="layui-input" value="${keyword}">
                </div>
            </div>
        </div>
        <div class="layui-col-md4  ">

            <button class="layui-btn   layui-btn-fluid">
                搜索
            </button>
        </div>
    </form>
</div>


<div class="layui-collapse  ">


    <div style="text-indent: 2em; font-size: 16px;padding: 10px; ">
        <p>
            新闻频道是面向全球，多语种，多终端的立体化新闻信息共享平台。以视听与互动为核心，24小时不间断提供最快捷，最权威，最全面，最丰富的新闻视听与互动服务，传播中国，了解世界。
        </p>
        <p>新闻频道是面向全球，多语种，多终端的立体化新闻信息共享平台。以视听与互动为核心，24小时不间断提供最快捷，最权威，最全面，最丰富的新闻视听与互动服务，传播中国，了解世界。</p>
    </div>


</div>

<blockquote class="layui-elem-quote">浏览排行 </blockquote>
<ul class="layui-timeline" id="right-time-list">
    <c:forEach items="${hitsList}" var="article">
        <li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">
                    <a
                            href="web/articleDetail.action?id=${article.articleid }"
                            title="${article.title }">
                            ${article.title } </a>
                </h3>
                <div class="layui-timeline-content">
                    <h5>
                        <fmt:formatDate value="${article.addtime}" pattern="MM月dd日"/>
                    </h5>
                </div>
            </div>
        </li>
    </c:forEach>

</ul>
<!-- ------------------------------------------------------------------------右侧固定区域 -->
