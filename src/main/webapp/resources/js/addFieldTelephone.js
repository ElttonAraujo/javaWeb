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