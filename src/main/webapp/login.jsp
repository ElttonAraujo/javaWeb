<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
 
 <style type="text/css">
 	h6{
 		color: #FF0000;
 	}
 </style>
</head>
<body>

 <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
  <h1>Login Form</h1>
  <h6><c:out value='${requestScope["error"]}' /></h6>
  
  
  
  <form action="login" method="post">

   <div class="form-group">
    <label for="uname">Email:</label> <input type="text"
     class="form-control" id="email" placeholder="email"
     name="email" required>
   </div>

   <div class="form-group">
    <label for="uname">Password:</label> <input type="password"
     class="form-control" id="password" placeholder="Password"
     name="password" required>
   </div>


   <button type="submit" class="btn btn-primary">Submit</button>
  </form>
 </div>
</body>
</html>

