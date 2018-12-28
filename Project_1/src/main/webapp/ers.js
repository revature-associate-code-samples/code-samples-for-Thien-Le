window.onload = function(){
	loadHomeView();
	
	$('#createTicket').on('click', loadForm);
	$('#homeNav').on('click', loadHomeView);
	//$('#logout').on('click', loadLogoutView);

}

function loadForm() {

	$('#form').removeAttr("hidden");
}

function displayUserName(){
	//send request to /users
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
		
		let users = JSON.parse(xhr.responseText);
		
		$('#dropdownMenu').html(`${users.firstName} ${users.lastName}`);
		}
	}
	xhr.open("GET", "users");
	xhr.send();
}

function loadLogoutView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//do things w response
			//$('#view').html(xhr.responseText);
		}
	}
	xhr.open("get", "logout");
	xhr.send();	
}

function loadHomeView(){
	
	displayUserName();
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.resposeText);
			
			$('#view').html(xhr.responseText);
			getTickets();
		}
	}

	xhr.open("GET", "home.view", true);
	xhr.send();	
}





function getTickets(){
	//send request to /reimbursements
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			let reims = JSON.parse(xhr.responseText);
			
			let count = 0;
			for(let r of reims){
				count++;
				appendToTicketList(r, count);
			}
		}
	}
	xhr.open("GET", "reimbursements");
	xhr.send();
}

function appendToTicketList(r, count){
	
	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	var tr = $(`<tr><td>${count}. Type: ${typeStr}, Amount: $ ${r.reimbAmount}, Description: ${r.reimbDescription}, Date submitted: ${r.reimbSubmitted}, Date approved: ${r.reimbResolved}, Status: ${statusStr}</td></tr>`);
	$('#reimbursementRow').append(tr);
	
}


function typeString(typeId) {
	
	switch(typeId) {
    case 1:
        return "Lodging";
    case 2:
        return "Travel";
    case 3:
    	return "Food";
    case 4:
    	return "Other";
    default:
        return "Nothing";
}
}

function statusString(id) {
	switch(id) {
	case 1:
		return "Pending";
	case 2:
		return "Approved";
	case 3:
		return "Denied";
	default:
		return "Nothing";
	}
}

