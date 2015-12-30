<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new song</title>
</head>
<body>

<h2>Add new Song</h2>

<form:form method="POST" modelAttribute="song">
   <table>
    <tr>
        <td><form:label path="songName">Song Name</form:label></td>
        <td><form:input path="songName" type="text"/></td>
        <td><form:errors path="songName"/>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>