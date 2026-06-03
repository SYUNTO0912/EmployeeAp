<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員管理システム</title>
</head>
<body>
    <h1>従業員情報の更新</h1>
    <h2>従業員情報</h2>
    <ul>
        <li>名前：${name}</li>
        <li>年齢：${age}</li>
        <li>部署番号：${departmentId}</li>

    </ul>

    <form action = "update" method = "post">
    名前：<input type = "text" name = "name" value = "" >
    年齢：<input type = "text" name = "age" value = "">
    部署名：
    <select name = "departmentName">
    <option value = "開発">開発部</option>
    <option value = "営業">営業部</option>
    <option value = "事務">事務部</option>
    </select>




</body>
</html>
