<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>従業員管理システム</title>
</head>
<body>
    <h1>従業員登録が完了しました</h1>
    <ul>
        <%-- 
            【修正内容】
            1. ${msg} を ${name} に変更（サーブレット側の request.setAttribute("name", ...) と合わせる）。
            2. <c:out> タグは JSTL の宣言が必要なため、よりシンプルな ${属性名} の形式に変更。
            3. <li> タグを <ul> で囲み、HTMLとして正しく整形。
        --%>
        <li>名前：${name}</li>
        <li>年齢：${age}</li>
        <li>部署番号：${departmentId}</li>
        をデータベースに追加しました。
    </ul>


    <p>以上の内容で登録しました。</p>
    <%--${pageContext.request.contextPath}は/EmployeeApに変換--%>
    <a href="${pageContext.request.contextPath}/index.jsp">トップへ戻る</a>
</body>

</html>
