window.onload = function() {
	//$('#logout').on('click', loadLogOutView);
	displayUserName();
	getAllTickets();
	$('#all').on('click', getAllTickets);
	$('#pending').on('click', getPendingTickets);
	$('#approve').on('click', getApprovedTickets);
	$('#deny').on('click', getDeniedTickets);
	$('#edit').on('click', getEditList);
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


function getEditList() {
	
	$('#list').empty();
	$('#reimbursementList').empty();
	$('#name').empty();
	$('#form').removeAttr("hidden");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#title').val('');
			$('#title').html('Approve or Deny Reimbursements');
			let reims = JSON.parse(xhr.responseText);
			
			for(let r of reims){
				appendToEditList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendToEditList(r){
	

	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	if (r.reimbStatusId == 1) {
	
		var option = $(`<option>ID: ${r.reimbId}, Type: ${typeStr}, Amount: $ ${r.reimbAmount}, 
		Description: ${r.reimbDescription}, Date submitted: ${r.reimbSubmitted}</option>`);
		$('#list').append(option);
		
	}
}



function getPendingTickets() {
	$('#reimbursementList').empty();
	$('#name').empty();
	$('#form').attr("hidden",true);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			$('#title').val('');
			$('#title').html('Pending Reimbursements');
			
			let reims = JSON.parse(xhr.responseText);
			
			for(let r of reims){
				appendToPendingTicketList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}



function appendToPendingTicketList(r){
	

	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	if (r.reimbStatusId == 1) {
		findUserById(r.reimbAuthor);
		var li = $(`<li>Type: ${typeStr}, Amount: $ ${r.reimbAmount}, Description: ${r.reimbDescription}, 
		Date submitted: ${r.reimbSubmitted}, Status: ${statusStr}</li>`);
		$('#reimbursementList').append(li);
	}
}

function getApprovedTickets() {
	$('#reimbursementList').empty();
	$('#name').empty();
	$('#form').attr("hidden",true);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			$('#title').val('');
			$('#title').html('Approved Reimbursements');
			
			let reims = JSON.parse(xhr.responseText);
			
			for(let r of reims){
				appendToApprovedTicketList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendToApprovedTicketList(r){
	

	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	if (r.reimbStatusId == 2) {
		findUserById(r.reimbAuthor);
		var li = $(`<li>Type: ${typeStr}, Amount: $ ${r.reimbAmount}, 
		Description: ${r.reimbDescription}, Date submitted: ${r.reimbSubmitted}, Status: ${statusStr}</li>`);
		$('#reimbursementList').append(li);
	}
}

function getDeniedTickets() {
	$('#reimbursementList').empty();
	$('#name').empty();
	$('#form').attr("hidden",true);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			$('#title').val('');
			$('#title').html('Denied Reimbursements');
			let reims = JSON.parse(xhr.responseText);
			
			for(let r of reims){
				appendToDeniedTicketList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendToDeniedTicketList(r){
	

	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	if (r.reimbStatusId == 3) {
	
		findUserById(r.reimbAuthor);
		
		var li = $(`<li>Type: ${typeStr}, Amount: $ ${r.reimbAmount}, Description: ${r.reimbDescription},
		Date submitted: ${r.reimbSubmitted}, Status: ${statusStr}</li>`);
		$('#reimbursementList').append(li);
	}
}

function getAllTickets(){
	$('#reimbursementList').empty();
	$('#name').empty();
	$('#form').attr("hidden",true);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			$('#title').val('');
			$('#title').html('All Reimbursements');
			
			let reims = JSON.parse(xhr.responseText);
			
			for(let r of reims){
				appendToTicketList(r);
			}
		}
	}
	xhr.open("GET", "manager");
	xhr.send();
}

function appendToTicketList(r){
	
	var typeStr = typeString(r.reimbTypeId);
	var statusStr = statusString(r.reimbStatusId);
	
	findUserById(r.reimbAuthor);
	var li = $(`<li>Type: ${typeStr}, Amount: $ ${r.reimbAmount}, Description: ${r.reimbDescription}, Date submitted: 
	${r.reimbSubmitted}, Status: ${statusStr}</li>`);
	$('#reimbursementList').append(li);
}

function findUserById(id) {
	
	var obj = {
			userId: id
	};
	
	var users;
	var toSend = JSON.stringify(obj);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			
			users = JSON.parse(xhr.responseText);
			var li = $(`<li>${users.lastName}, ${users.firstName}</li>`);
			$('#name').append(li);
		}
	}
	
	xhr.open("POST", "users");
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(toSend);
	return users;
}

function loadLogOutView() {
	
	var xhr = new XMLHttpRequest();
	console.log("HERE AT LOG OUT");
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//do things w response
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("get", "logout");
	xhr.send();	
	
	
	
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
