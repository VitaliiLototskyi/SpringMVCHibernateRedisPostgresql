<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Developer Info</title>
</head>
<body>

<h2>Developer Information</h2>

<div class="list">
    <c:if test="${fn:length(devList) > 0}">
        <table cellpadding="5">
            <tr class="even">
                <th>DevId</th>
                <th>DevName</th>
            </tr>
            <c:forEach items="${devList}" var="dev" varStatus="status">
            <tr>
                <td>${dev.id}</td>
                <td>${dev.name}</td>
            </tr>
            </c:forEach>

        </table>
    </c:if>

</div>


</body>
</html>