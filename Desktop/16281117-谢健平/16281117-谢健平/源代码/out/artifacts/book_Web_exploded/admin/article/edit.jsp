<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%><!DOCTYPE html>
<html>

<head>
    <title></title>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        function selimage() {
            layer.open({
                type: 2,
                title: '上传图片',
                area: ['500px', '100px'],
                offset: '250px',
                content: '<%=basePath%>saveimage.jsp'
            });
        }
    </script>
</head>
<body>


<div class="layui-row">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>编辑</legend>
    </fieldset>
    <form class="layui-form" method="post" action="admin/article/update.action" name="myform" onsubmit="return check()">
        <input
                type="hidden" name="articleid" value="${article.articleid}"/>

        <div class="layui-form-item">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block"><input type="text" lay-verify="required" required="required"
                                                  class="layui-input" name="title"
                                                  style="width: 160px" id="title" value="${article.title}"/></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-inline">
                <style>
                    .layui-form-select dl {
                        z-index: 9999999;
                    }
                </style>

                <select name="catalogid" style="width: 160px;z-index: 999999;overflow: visible;" id="catalogid">
                    <option value="${article.catalogid}">${article.catalogname }</option>

                    <c:forEach items="${catalogList}" var="catalog">
                        <option value="${catalog.catalogid}">${catalog.catalogname }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属书籍</label>
            <div class="layui-input-inline">
                <style>
                    .layui-form-select dl {
                        z-index: 9999999;
                    }
                </style>
                <select name="bookinfoid"
                        style="width: 160px;z-index: 999999" id="bookinfoid">

                    <option value="${article.bookinfoid}">${article.bookinfoname }</option>

                    <c:forEach
                        items="${bookinfoList}" var="bookinfo">
                    <option value="${bookinfo.bookinfoid}">${bookinfo.bookinfoname}</option>
                </c:forEach></select></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">图片</label>
            <div class="layui-input-block">
                <input type="text" lay-verify="required" required="required" class="layui-input" name="image"
                       style="width: 160px" id="image" value="${article.image}"
                       onclick="selimage();" readonly="readonly"/></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否置顶</label>
            <div class="layui-input-block">
                <input type="radio" name="istop"
                       id="istop" value="是"
                       checked="checked" title="是"/>
                <input
                        type="radio" name="istop" id="istop" value="否" title="否"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否轮播</label>
            <div class="layui-input-block">
                <input type="radio" name="isflv"
                       id="isflv" value="是"
                       checked="checked" title="是"/>
                <input
                        type="radio" name="isflv" id="isflv" value="否" title="否"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <script id="contents" name="contents" type="text/plain">${article.contents}</script>
                <script type='text/javascript' src='assets/ueditor/ueditor.config_simple.js'></script>
                <script type='text/javascript' src='assets/ueditor/ueditor.all.js'></script>
                <script type='text/javascript' src='assets/ueditor/lang/zh-cn/zh-cn.js'></script>
                <script type='text/javascript'>  ue = UE.getEditor('contents');</script>

            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="addtime" id="addtime"
                       value="<fmt:formatDate value="${article.addtime}" pattern="YYYY-MM-dd"/>"/>
                <input type="hidden" name="hits" id="hits" value="${article.hits}"/>
                <input type="submit" name="Submit" class="layui-btn" lay-submit="" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>

    </form>
</div>
<script src="assets/layui/layui.js"></script>
<script>
    function check() {
        if (document.myform.title.value == '') {
            alert('请输入标题');
            return false;
        }
        if (document.myform.image.value == '') {
            alert('请输入图片');
            return false;
        }
        if (document.myform.catalogid.value == '') {
            alert('请输入分类');
            return false;
        }
        /*     if (document.myform.istop.value == '') {
                 alert('请输入是否置顶');
                 return false;
             }
             if (document.myform.isflv.value == '') {
                 alert('请输入是否轮播');
                 return false;
             }*/
        if (document.myform.contents.value == '') {
            alert('请输入内容');
            return false;
        }
        /*     if (document.myform.addtime.value == '') {
                 alert('请输入发布日期');
                 return false;
             }
             if (document.myform.hits.value == '') {
                 alert('请输入点击数');
                 return false;
             }*/
    }

    layui.use('form', function () {
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        //……

        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
    });
</script>
</body>
</html>