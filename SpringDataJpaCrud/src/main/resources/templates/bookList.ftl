<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
</head>
<body>
<a href="/bookAdd.html">添加</a>
<table>
	<tr>
		<th>编号</th>
		<th>图书名称</th>
		<th>图书作者</th>
		<th>操作</th>
	</tr>
	<#list bookList as book>
		<tr>
			<td>${book.id}</td>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>
				<a href="/book/preUpdate/${book.id}">修改</a>
				<a href="/book/delete?id=${book.id}">删除</a>
			</td>
		</tr>
	</#list>
</table>
</body>
</html>