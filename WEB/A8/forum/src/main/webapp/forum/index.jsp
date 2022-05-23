<%@ page import="com.example.forum.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: teodorfilp
  Date: 17/05/2022
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User user = (User) session.getAttribute("user");
  int userId = -1;
  if (user == null) {
    response.sendRedirect("/login.jsp");
  } else {
      userId = user.getId();
  }
%>
<html>
<head>
  <title>Forum</title>
  <link rel="stylesheet" href="main.css">
</head>
<body>
<div id="forum-container">
  <button style="float:right" onclick="logout()">Logout</button>
  <button id="toggle-add" class="card" onclick="toggleAddPost()">Toggle add post</button>
  <form id="add-form" class="card">
    <div class="form-item">
      <label for="category">Category</label>
      <input type="text" name="category" id="category" placeholder="Enter post category...">
    </div>
    <div class="form-item">
      <label for="title">Title</label>
      <input type="text" name="title" id="title" placeholder="Enter post title...">
    </div>
    <div class="form-item">
      <label for="content">Content</label>
      <textarea name="content" id="content" placeholder="Enter post content..."></textarea>
    </div>
    <button type="submit" class="card">Add post</button>
  </form>

  <div id="posts-container">
    <div class="post-item">
      <h2 class="post-title">title</h2>
      <p class="post-content">content</p>
      <span class="post-author">author</span>
    </div>
  </div>
</div>

<script src="main.js"></script>
<script>
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });

    let formElement = null;
    let items = [];
    let postId = null;

    let showAddForm = false;
    const toggleAddPost = () => {
        showAddForm = !showAddForm;
        formElement.style.display = showAddForm ? 'none' : 'block';
    }

    const logout = () => {
        <% session.removeAttribute("user"); %>
        location.reload();
    }

    const handleSubmit = async function(e) {
        e.preventDefault();

        let title = document.getElementById("title").value;
        let content = document.getElementById("content").value;
        let category = document.getElementById("category").value;

        if (!title || !content || !category) return;

        let params = new URLSearchParams({ postId, title, content, category, userId: <%= userId %> });
        const ok = await fetch(`/forum-servlet?` + params, { method: "POST" }).then(res => res.json());

        if (!ok) return alert("Something went wrong, try again later!");
        formElement.reset();
        await fetchItems();
    }

    const fetchItems = async () => {
        items = await fetch('/forum-servlet?category=' + params.category).then(res => res.json());
        renderItems();
    }

    document.addEventListener("DOMContentLoaded", () => {
        formElement = document.getElementById("add-form");
        formElement.addEventListener('submit', handleSubmit)
        document.getElementById("category").value = params.category;
        fetchItems();
    })

    const handleDelete = async (postId) => {
        if (!confirm("Are you sure you want to delete this post?")) return;

        const ok = await fetch(`/forum-servlet?postId=\${postId}`, { method: 'DELETE' })
        if (!ok) return alert("Something went wrong, try again later!");

        await fetchItems();
    }

    const addComment = async (postId) => {
        let comment = document.getElementById("add-comment-input-" + postId).value;
        if (!comment) return;

        const params = new URLSearchParams({ postId, text: comment, authorId: <%= userId %> });
        const ok = await fetch('/comments-servlet?' + params, { method: 'POST' }).then(x => x.json());

        if (!ok) return alert("Something went wrong, try again later!");

        await fetchItems();
    }

    const deleteComment = async (commentId) => {
        if (!confirm("Are you sure you want to delete this comment?")) return;

        const params = new URLSearchParams({ commentId });
        const ok = await fetch('/comments-servlet?' + params, { method: 'DELETE' }).then(x => x.json());

        if (!ok) return alert("Something went wrong, try again later!");

        await fetchItems();
    }

    const renderItems = () => {
        const postsContainer = document.getElementById('posts-container');
        postsContainer.innerHTML = items.reduce((acc, currentItem) => {
            return acc + getItemElement(currentItem, <%= userId %>)
        }, '');
    }
</script>
</body>
</html>
