<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  	<script src="http://code.jquery.com/jquery-1.11.1.js"></script>
	<!-- Inclusão do Plugin jQuery Validation-->
	<script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
	<link type="text/css"href="resources/css/form.css"rel="stylesheet" />
	<script type="text/javascript" src="resources/js/validate.js"></script>
	<script type="text/javascript" src="resources/js/addFieldTelephone.js"></script>

</head>
<body>
	<c:import url="/WEB-INF/template/logout.jsp"></c:import>
	
	
	<h4>Update User - ${user.id}</h4>
	<form action="updateUser" method="post" id="formValidation">
		<input type="hidden" name="id" value="${user.id}" /> 
		Name: <input type="text" name="name" value="${user.name}"/><br /> <br /> 
		E-mail: <input type="text" name="email" value="${user.email}"/><br /><br /> 
		password: <input type="text" name="password" value="${user.password}" /><br /> <br />
		<c:forEach var="tel" items="${user.telephones}" varStatus="te">
			<div class="field_wrapper">
				<h4>Telephone</h4>
        		DDD: <input type="text" name="ddd[]" id="ddd" required="true" rangelength=[2,2] digits=true value="${tel.ddd}"/>
        		Number: <input type="text" name="number[]" id="number" required=true rangelength=[8,9] digits=true value="${tel.number}" />
        		Type: <select name="type[]" id="type" required="true">
        		<c:choose>
        			<c:when test="${tel.type == 'CELLPHONE'}">
        				<option value="CELLPHONE" selected>Cellphone</option>
        				<option value="RESIDENTIAL">Residential</option>
        			</c:when>
        			<c:otherwise>
        				<option value="CELLPHONE" >Cellphone</option>
        				<option value="RESIDENTIAL" selected>Residential</option>
        			</c:otherwise>
        	</c:choose>
        	</select>
        	<c:choose>
        		<c:when test="${te.count == 2}">
        			<a href="javascript:void(0);" class="remove_button"> Remove</a>
        		</c:when>
        		<c:otherwise>
        			<a href="javascript:void(0);" class="add_button" title="Add field">Add</a>
        		</c:otherwise>
        	</c:choose>
        	</div>
		</c:forEach> 
		<input type="submit" value="Update" />
	</form>
</body>
</html>