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
