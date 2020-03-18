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
	
	
  	
</head>
<body>
	<script type="text/javascript" src="resources/js/addFieldTelephone.js"></script>
	<c:import url="/WEB-INF/template/logout.jsp"></c:import>


<script type="text/javascript">
	function removeNow(id, item){
		$.ajax({
			type: "POST",
        	url: "removeUser",
        	data: {
        		id: id
        	}
		});
		$(item).closest("tr").hide();	
		}
</script>
	
	
	<hr />
	<form action="addUser" method="POST"  id="formValidation">
		<h4 align="center">New User</h4>
		<div class="field_basic">
		<label>Name</label>
		<input type="text" name="name" id="name" autofocus="autofocus"/><br />
		<label>E-mail</label> 
		<input type="text" name="email" id="email"/><br /> 
		<label>Password</label>
		<input type="text" name="password" id="password"/><br />
		</div>
		<div class="field_wrapper">
    		<h4 align="center">Telephone</h4>
        	DDD: <input type="text" name="ddd[]" id="ddd" required=true rangelength=[2,2] digits=true/>
        	Number: <input type="text" name="number[]" id="number" required=true rangelength=[8,9] digits=true />
        	Type: <select name="type[]" id="type" required=true>
        	<option value="CELLPHONE">Cellphone</option>
        	<option value="RESIDENTIAL">Residential</option>
        	</select>
        	<a href="javascript:void(0);" class="add_button" title="Add field">Add</a>
		</div>
		<input type="submit" value="save">
		<br/><br/>
	</form>
	
	<div class="container">
  	<h2>List of Users</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>E-mail</th>
					<th>Password</th>
					<th>Telephone(s)</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td>
						<table>
							<tbody>
								<tr><td>
									<ul>							
									<c:forEach var="tel" items="${user.telephones}">
										<li>(${tel.ddd})-${tel.number}:${tel.type}</li>
									</c:forEach>
								</ul>
								</td></tr>	
							</tbody>
						</table>
					</td>
					<td>
					<a href="#" onclick="removeNow(${user.id},this)">Remover</a>
					</td>
					<td>
					<a href="mvc?controller=ShowUserController&id=${user.id}">Update</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
		
</body>
</html>