<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員管理システム</title>
</head>
<body>
    <h1>従業員削除</h1>
    <form action = "decideDeleteEmployee" method = "post">
        <ul>
            <li>従業員番号：${employeeId}</li>
            <li>名前：${name}</li>
            <li>年齢：${age}</li>
            <li>部署番号：${departmentId}</li>

            <input type="hidden" name="employeeId" value="${employeeId}">
            削除してよろしいですか？<br>
            <input type = "submit" value = "削除">
        </ul>
       </form>




</body>
</html>
