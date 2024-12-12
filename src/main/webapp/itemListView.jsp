<%@page import="aiwa.entity.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<%
	ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");
	String word = (String) request.getAttribute("word");
	int categoryId = (int) request.getAttribute("categoryid");
%>

<style>
	.card-img-top {
		height:300px;
		object-fit:cover;
	}
</style>
</head>
<body>
<div class="container">
	<h1 class="display-1 text-primary">ANIMALS <i class="bi bi-tencent-qq"></i></h1>

	<form action="ItemListController">
		<select name="categoryid">
			<option value="0" <%= categoryId == 0 ? "selected" : "" %>>すべて</option>
			<option value="1" <%= categoryId == 1 ? "selected" : "" %>>哺乳類</option>
			<option value="2" <%= categoryId == 2 ? "selected" : "" %>>爬虫類</option>
			<option value="3" <%= categoryId == 3 ? "selected" : "" %>>鳥類</option>
		</select>
		<input type="text" name="keyword" placeholder="キーワード" value="<%= word %>">
		<button type="submit">検索</button>
	</form>

	<div class="row">
		<% for(Item item : items) { %>
			<div class="col-md-4 col-sm-6 mb-3">

				<div class="card">
					<img src="<%= item.getImage() %>" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title text-truncate"><%= item.getItemName() %></h5>
						<p class="card-text"><%= item.getCategory().getCategoryName() %></p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>

			</div>
		<% } %>
	</div>
</div>
</body>
</html>