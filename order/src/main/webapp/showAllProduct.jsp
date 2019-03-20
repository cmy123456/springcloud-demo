<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>
    <c:forEach items="${requestScope.list}" var="product">
        名字：${product.productName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        价格：${product.productPrice} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/cart/addCart?id=${product.productId}">加入购物车</a>
        <br/>
    </c:forEach>
</body>
</html>