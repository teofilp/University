<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: teodorfilp
  Date: 13/06/2022
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ArrayList<String> options = new ArrayList<String>() {{
    add("Option 1");
    add("Option 2");
  }};
%>
<html>
<head>
  <title>Login</title>
</head>
<body>
  <form action="/login-servlet" method="post">
    <label>Username</label>
    <input type="text" name="username">
    <label>Password</label>
    <input type="password" name="password">
    <button type="submit">Submit</button>
  </form>
<%--  <select>--%>
<%--    <c:forEach items="<%= options %>" var="item">--%>
<%--      <option value="${item}">${item}</option>--%>
<%--    </c:forEach>--%>
<%--  </select>--%>
</body>
</html>
