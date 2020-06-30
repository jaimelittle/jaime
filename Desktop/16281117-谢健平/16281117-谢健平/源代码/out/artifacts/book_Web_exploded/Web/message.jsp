<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <base href="<%=basePath%>">


    <%

        //弹出消息
        String message = (String) request.getAttribute("message");
        if (message == null) {
            message = "";
        }
        if (!message.trim().equals("")) {
            out.println("<script language='javascript'>");
            out.println("alert('" + message + "');");
            out.println("</script>");
        }
        request.removeAttribute("message");


        //跳转
        String path1 = (String) request.getAttribute("path");
        if (path1 == null) {
            path1 = "";
        }
        if (!path1.trim().equals("")) {
            out.println("<script language='javascript'>");
            out.println("document.location.href =\"" +basePath+ path1 + "\";");
            out.println("</script>");
        }
        request.removeAttribute("path");
    %>

</head>

<body>
${message}
${path}


</body>
</html>