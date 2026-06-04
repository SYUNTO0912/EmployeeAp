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
    <form action = "insertUpdate" method = "post">
    <p>変更する従業員の従業員番号を入力してください</p>
    <select name = "selectEmployee">
    <c:forEach var = "id" items = "${employeeNumber}">
    <option value = "${id}">${id}</option>
    </c:forEach>

    </select>
    <input type = "submit" value = "決定">
    </form>




</body>
</html>
