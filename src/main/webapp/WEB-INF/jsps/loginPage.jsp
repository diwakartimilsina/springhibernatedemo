<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new song</title>
</head>
<body>
	<form:form method="POST" modelAttribute="user">
	   <table>
	    <tr>
	        <td><form:label path="userName">Username</form:label></td>
	        <td><form:input path="userName" type="text"/></td>
	        <td><form:errors path="userName"/>
	    </tr>
	    <tr>
	        <td><form:label path="password">Password</form:label></td>
	        <td><form:input path="password" type="text"/></td>
	        <td><form:errors path="password"/>
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