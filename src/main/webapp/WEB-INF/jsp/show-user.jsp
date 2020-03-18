<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script type="text/javascript" src="jquery.validate.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
	$(document).ready(function() {
		var maxField = 2;
		var addButton = $('.add_button');
		var wrapper = $('.field_wrapper');
		var initialField = 1;
		var htmlField='<div>'+
						'<h6>Telephone</h6>'+
						'DDD: <input type="text" name="ddd[]" id="ddd" required=true rangelength=[2,2] digits=true/>'+ 
						'Number: <input type="text" name="number[]" id="number" required=true rangelength=[8,9] digits=true />'+
						'Type: <select name="type[]" id="type" required=true>'+
        				'<option value="CELLPHONE">Cellphone</option>'+
        				'<option value="RESIDENTIAL">Residential</option>'+
        				'</select>'+
						'<a href="javascript:void(0);" class="remove_button"> Remove</a></div>';
		
		
		
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
        }
      });
    });
  </script>
	
	
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