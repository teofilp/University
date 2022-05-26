<%@ page import="com.example.forum.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User) session.getAttribute("user");
    int userId = -1;
    if (user == null) {
        response.sendRedirect("/login.jsp");
    } else {
        userId = user.getId();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
</head>
<body>
    <h1>Select category</h1>
    <ul id="list">
    </ul>
<script>
    document.addEventListener("DOMContentLoaded", async () => {
        var categories = await fetch('/hello-servlet').then(res => res.json());

        document.getElementById("list").innerHTML = categories.map(x => `
            <li>
                <a href="/forum?category=\${x}">\${x}</a>
            </li>
        `).reduce((acc, curr) => acc + curr, '');
    });
</script>
</body>
</html>