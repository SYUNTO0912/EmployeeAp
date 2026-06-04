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
    
    <%-- 現在の情報を確認用に表示 --%>
    <h2>現在の情報</h2>
    <ul>
        <li>名前：${employee.employeeName}</li>
        <li>年齢：${employee.age}</li>
        <li>部署ID：${employee.departmentId}</li>
    </ul>

    <%-- 編集用のフォーム。idは隠しフィールド（hidden）で送信 --%>
    <h2>編集</h2>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${employee.employeeId}">
        名前：<input type="text" name="name" value="${employee.employeeName}"><br>
        年齢：<input type="text" name="age" value="${employee.age}"><br>
        部署：
        <select name="departmentId">
            <%-- 現在の部署IDに応じて selected を切り替え --%>
            <option value="1" ${employee.departmentId == 1 ? 'selected' : ''}>開発部</option>
            <option value="2" ${employee.departmentId == 2 ? 'selected' : ''}>営業部</option>
            <option value="3" ${employee.departmentId == 3 ? 'selected' : ''}>事務部</option>
        </select>
        <br>
        <input type="submit" value="更新">
    </form>




</body>
</html>
