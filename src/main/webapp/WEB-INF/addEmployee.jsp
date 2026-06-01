<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員管理システム</title>
</head>
<body>
    <h1>従業員管理システムへようこそ</h1>
    <form action = "addEmployee" method = "post">
    <input name = "name" value = "name">
    <input name = "age" value = "age">
    <select name = "departmentId" value = "department">
    <option value = "1">開発部</option>
    <option value = "2">営業部</option>
    <option value = "3">事務部</option>
    </select>

    <input type = "submit" value = "追加">

    </form>


</body>
</html>
