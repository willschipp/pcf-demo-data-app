/**
 * 
 */

function buildTable(targetTable) {
	//reset
	$(targetTable).empty();
	//retrieve from the services
	$.ajax({
		url : './app/customer',
		contentType : 'application/json',
		data : 'json',
		success : function(data) {
			console.log(data);
			var headerRow = null;
			var startedHeaderRow = false;
			var finishedHeaderRow = false;
			$.each(data, function(idx, value) {
				var rowData = "<tr>";
				$.each(value, function(key, val) {
					if (!startedHeaderRow) {
						headerRow += "<tr>";
						startedHeaderRow = true;
					}//end if
					if (!finishedHeaderRow) {
						headerRow += "<th>";
						headerRow += key;
						headerRow += "</th>";
					}//end if
					//now add the row
					rowData += "<td>";
					rowData += val;
					rowData += "</td>";
				});
				if (!finishedHeaderRow) {
					headerRow += "</tr>";
					startedHeaderRow = true;
					finishedHeaderRow = true;
					$(targetTable).append(headerRow);
				}//end if
				rowData += "</tr>";
				$(targetTable).append(rowData);
			});
		}

	});
}



//build the form based on the data queried
function buildForm(targetForm) {
	console.log('invoking build form');
	//get the data results
	$.ajax({
				url : './app/customer/fieldNames',
				contentType : 'application/json',
				dataType : 'json',
				success : function(data) {
					//get the root of the form
					console.log(data);
					//loop and build
					$.each(data, function(index, value) {
						if (value != 'id') {
							//get the fielName
							var fieldName = value + "input";
							//build a form element
							var formElement = "<div class=\"form-group\">";
							formElement += "<label for=\"" + fieldName + "\">"
									+ value + "</label>";
							formElement += "&nbsp;"
							formElement += "<input type=\"\" id=\"" + fieldName
									+ "\" placeholder=\"" + value + "\"/>";
							formElement += "</div>";
							//now add
							$(targetForm).append(formElement);
						}//end if
					});
					//add the last --> the submit button
					var submitButton = "<button type=\"submit\" class=\"btn btn-default\">Save</button>";
					$(targetForm).append(submitButton);
				}
			});

}