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
	
	<style type="text/css">
		input[type=text]:focus {
 			border: 3px solid #555;
		}
		form {
 			text-align:center;
  			border: 1px solid;
		}
		*{
  			box-sizing: border-box;
		}

		input[type=text], select{
  			width: 20%;
  			padding: 8px;
  			border: 1px solid #ccc;
  			border-radius: 4px;
  			resize: vertical;
		}
		

		label {
  			padding: 12px 12px 12px 0;
  			display: inline-block;
		}

		input[type=submit] {
  			background-color: #4CAF50;
  			color: white;
  			padding: 12px 20px;
  			border: none;
  			border-radius: 4px;
  			cursor: pointer;
  			float: left;
		}

		input[type=submit]:hover {
  		background-color: #45a049;
		}

		.container {
  			border-radius: 5px;
  			background-color: #f2f2f2;
  			padding: 20px;
		}

		.col-25 {
  			float: left;
  			width: 25%;
  			margin-top: 6px;
		}

		.col-75 {
  			float: left;
  			width: 25%;
  			margin-top: 6px;
		}

/* Clear floats after the columns */
		.row:after {
  			content: "";
  			display: table;
  			clear: both;
		}
		
		h4 {
  			display: block;
  			font-size: 1em;
  			font-family: serif;
  			font-weight: bold;
  			font-style: oblique;
			}
		
</style>
  	
</head>
<body>
	<c:import url="/WEB-INF/template/logout.jsp"></c:import>
	
	<script type="text/javascript">
		var form = $('#formValidation');
		var nameUser = $('#name').val();
		var emailUser = $('#email').val();
		var passwordUser = $('#password').val();
		var count = $("input[name^= 'ddd']").length;
		var dddArray = $("input[name^= 'ddd']");
		var numberArray = $("input[name^= 'number']");
		var typeArray = $("input[name^= 'type']");
		$('#save').click(function() {
			$.ajax({
				type: form.attr('method'),
				url: form.attr('action'),
				data: value,
				success : function() {
					$(".table").append('<tr><td>' + nameUser + '</td></tr>')
				}
			});
			return false;
		});
	
	</script>
	
	<script type="text/javascript">
    $(document).ready( function() {
      $('#formValidation').validate({
        rules:{ 
          name:{ 
            required: true,
            rangelength: [3,50]
          },
          email: {
            required: true,
            email: true
          },
          password: {
        	  required: true,
        	rangelength: [6,15]
          },
          ddd:{
        	 required: true,
         	 digits: true, 
         	 rangelength: [2,2]
          },
          number:{
        	 required: true,
         	 digits: true,
         	 rangelength: [8,9]
          },
          type:{
        	 required: true, 
         	 rangelength: [3,10]
        },
        }
      });
    });
  </script>

<script type="text/javascript">
	$(document).ready(function() {
		var maxField = 2;
		var addButton = $('.add_button');
		var wrapper = $('.field_wrapper');
		var htmlField='<div>'+
						'<h6>Telephone</h6>'+
						'DDD: <input type="text" name="ddd[]" id="ddd" required=true rangelength=[2,2] digits=true/>'+ 
						'Number: <input type="text" name="number[]" id="number" required=true rangelength=[8,9] digits=true />'+
						'Type: <select name="type[]" id="type" required=true>'+
        				'<option value="CELLPHONE">Cellphone</option>'+
        				'<option value="RESIDENTIAL">Residential</option>'+
        				'</select>'+
						'<a href="javascript:void(0);"class="remove_button"> Remove</a></div>';
		var initialField = 1;
		
		
		$(addButton).click(function() {
			if(initialField < maxField){
				initialField ++;
				$(wrapper).append(htmlField);
			}
		});
		
		$(wrapper).on('click', '.remove_button', function(e) {
			e.preventDefault();
	        $(this).parent('div').remove(); 
	        initialField--; 
		});
	});
	
</script>

<script type="text/javascript">
	function removeNow(id, item){
		$.post("removeUser", {'id' : id}, function(){
			$(item).closest("tr").hide();
			});
		}
</script>
	
	
	<hr />
	<form action="addUser" method="post" id="formValidation">
		<h4 align="center">New User</h4>
		<div class="field_basic">
		<label>Name</label>
		<input type="text" name="name" id="name"/><br />
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
		<button id="save">>Save</button>
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