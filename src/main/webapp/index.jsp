<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.servlet2.logic.Model" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%--<h1><%= "Hello World!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="hello-servlet">Hello Servlet</a> <br>--%>
<%--<a href="servlet">My Servlet</a>--%>
    <h1>Домашная страница по работе с пользователями</h1>
    Введите ID пользователя (0 - для вывода всего списка пользователей)
    <br>
    Доступно:
    <% Model model = Model.getInstance();
    out.println(model.getFromList().size()); %>
    <form method="get" action="get">
        <label>ID:
            <input type="text" name="id"/>
        </label>
        <button type="submit">Поиск</button>
    </form>
    <a href="addUser.html">Создать нового пользователя</a>
</body>
</html>