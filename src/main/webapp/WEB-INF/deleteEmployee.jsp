<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員管理システム</title>
</head>
<body>
    <h1>従業員削除</h1>
    <%-- 【修正】method="post" を追加し、action をコンテキストパス起点に変更 --%>
    <form action = "${pageContext.request.contextPath}/delete" method = "post">
    <input type = "text" name = "deleteNumber" value = "">

    <input type = "submit" name = "confirm" value = "確認">

    </form>




</body>
</html>
