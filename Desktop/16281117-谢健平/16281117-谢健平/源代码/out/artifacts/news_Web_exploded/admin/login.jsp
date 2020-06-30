<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新闻网站</title>
    <link rel="stylesheet" href="assets/layui/css/layui.css">
    <script src="assets/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        function checkLogin() {
            var username = document.myform.username.value;
            var password = document.myform.password.value;
            if (username == '') {
                alert('请输入用户名');
                document.myform.username.focus();
                return false;
            }
            if (password == '') {
                alert('请输入密码');
                document.myform.password.focus();
                return false;
            }
        }
    </script>
    <style type="text/css">
        body {
            background-image: url(assets/images/login_bg.jpg);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            background-attachment:fixed;
        }

    </style>
</head>
<body   class="layui-anim layui-anim-scaleSpring">

<div align="center">
    <div id="main-body" align="left" style="margin-top: 200px">
        <div align="center" style="width: 100%; margin-top: 30px;">

            <div style="width: 300px;" class="layui-anim layui-anim-up">
                <h2 style="margin-bottom: 30px;color: white;font-size:  24px;">新闻网站</h2>

                <form class="layui-form" action="<%=basePath%>admin/login/login.action" name="myform" method="post"
                      onSubmit="return checkLogin()">
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="margin-left: 0px;">
                            <input type="text" name="username" required
                                   lay-verify="required" placeholder="请输入账号" autocomplete="off" value="admin"
                                   class="layui-input">
                        </div>
                        <!-- <div class="layui-form-mid layui-word-aux"></div> -->
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="margin-left: 0px;">
                            <input type="password" name="password" required
                                   lay-verify="required" placeholder="请输入账号密码" autocomplete="off" value="111111"
                                   class="layui-input">
                        </div>
                        <!-- <div class="layui-form-mid layui-word-aux"></div> -->
                    </div>

                    <div class="layui-form-item"  >
                        <div class="layui-input-block" style="margin-left: 0px;" >

                            <img id="cpacha-img" title="点击切换验证码" style="cursor:pointer;float: left;margin-right: 5px"
                                 src="system/getCpacha.action?vl=4&w=150&h=40&type=loginCpacha" width="100px" height="37px"
                                 onclick="changeCpacha()">
                            <input type="text" name="code" id="code" maxlength="4" required class="layui-input" style="width:194px; float: left"
                                   lay-verify="required"
                                   placeholder="验证码"
                                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                                   onkeyup="value=value.replace(/[\W]/g,'')"

                            >
                        </div>


                        <!-- <div class="layui-form-mid layui-word-aux"></div> -->
                    </div>
                    <div class="layui-form-item" style="margin-top: 30px;">
                        <div class="layui-input-block" style="margin-left: 0px;">
                            <button class="layui-btn layui-btn-normal  " lay-submit
                                    lay-filter="login-form-commit" style="width: 300px;">登录
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div style="text-align: center; clear: both;"></div>
<script src="assets/layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        //……

        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
    });
    function changeCpacha(){
        $("#cpacha-img").attr("src",'system/getCpacha.action?vl=4&w=150&h=40&type=loginCpacha&t=' + new Date().getTime());
    }
</script>
</body>
</html>

