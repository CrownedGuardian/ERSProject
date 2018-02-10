/**
 * 
 */
window.onload = function() {
	
	loadDashboardView();

};

function loadDashboardView() {
	console.log("Initiating dashboard view");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// console.log(xhr.responseText);
			var userCred = JSON.parse(xhr.responseText);
			console.log(typeof (userCred.roleId));
			console.log("Username: " + userCred.username + "\n" + "Role id: "
					+ userCred.roleId);
			switch (userCred.roleId) {
			case 1:
				loadEmployeeView(userCred);
				break;
			case 2:
				loadManagerView(userCred);
				break;
			default:
				alert("No match for role id");
				break;
			}
		} else {
			console.log(xhr.readyState + " " + xhr.status);
		}
	}
	xhr.open("GET", "userCred.do.dash", true);
	xhr.send();
};

function loadManagerView(userCred) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('appView').innerHTML = xhr.responseText;
			$("#tableSection").hide();
			var selectValue = "UNKNOWN";
			$('select').on('change', function() {
				selectValue = this.value;
				showReimbursements(userCred, selectValue);
			});
			showReimbursements(userCred, selectValue);
		}
	}
	xhr.open("GET", "managerView.go.dash", true);
	xhr.send();
}

function showReimbursements(userCred, selectValue) { // Shows reimbursements on manager side
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var reimbursements = JSON.parse(xhr.responseText);
			if (reimbursements != null && reimbursements != undefined
					&& reimbursements.length != 0) {
				$("#defaultMessage").hide();
				$("#tableSection").show();
				$('tbody').empty();
				for (var i = 0; i < reimbursements.length; i++) {
					console.log("Reimbursement: " + reimbursements[i].authorId);
					var status, type, resolvedTime = reimbursements[i].timeResolved;
					if (resolvedTime != null) {
						resolvedTime = new Date(resolvedTime).toUTCString()
					} else {
						resolvedTime = "--";
					}
					switch (reimbursements[i].statusId) {
					case 1:
						status = "PENDING";
						break;
					case 2:
						status = "APPROVED";
						break;
					case 3:
						status = "DENIED";
						break;
					default:
						status = "UNKNOWN";
						break;
					}
					switch (reimbursements[i].typeId) {
					case 1:
						type = "LODGING";
						break;
					case 2:
						type = "TRAVEL";
						break;
					case 3:
						type = "FOOD";
						break;
					default:
						type = "OTHER";
						break;
					}
					var hasButtons = (status == "PENDING") ? "<td><div class='btn-group' role='group' aria-label='...'>"
					+ "<button type='button' id='button" + i + "_1'  value='" + reimbursements[i].reimbursementId + "' class='btn btn-default'><span class='glyphicon glyphicon-ok' aria-hidden='true'></span></button>"
					+ "<button type='button' id='button" + i + "_2'  value='" + reimbursements[i].reimbursementId + "' class='btn btn-default'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></div>"
					+ "</td>" : "<td>" + status + "</td>";
					$("tbody").append(
							"<tr id='"
									+ reimbursements[i].reimbursementId
									+ "'><td>"
									+ reimbursements[i].reimbursementId
									+ "</td><td>"
									+ reimbursements[i].amount
									+ "</td><td>"
									+ new Date(reimbursements[i].timeSubmitted)
											.toUTCString() + "</td><td>"
									+ resolvedTime + "</td><td>"
									+ reimbursements[i].description
									+ "</td><td>" + reimbursements[i].authorId
									+ "</td><td>"
									+ reimbursements[i].resolverId
									+ "</td><td>" + type
									+ "</td>" + hasButtons + "</tr>");
				}
				for(var i = 0; i < reimbursements.length; i++) {
					let approvalButton = document.getElementById("button"+i+"_1");
					let denialButton = document.getElementById("button"+i+"_2");
					if(approvalButton != null && approvalButton != undefined &&
					   denialButton != null && denialButton != undefined) {
						approvalButton.addEventListener("click", function() {
							approveReimbursement(userCred, this.value, selectValue);
						});
						denialButton.addEventListener("click", function() {
							denyReimbursement(userCred, this.value, selectValue);
						});
					}
				}
				
			}
			console.log(reimbursements);
		}
	}
	xhr.open("POST", "getReimbursements.do.dash", true);
	xhr.send(JSON.stringify([ userCred.username, userCred.roleId, selectValue ]));
}

// Employee side
function loadEmployeeView(userCred) {
	console.log("Initiating employee view");
	console.log("Username: " + userCred.username + "\n" + "Role id: "
			+ userCred.roleId);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('appView').innerHTML = xhr.responseText;
			// $("#tableSection").hide();
			var btns = document.getElementsByTagName("li");

			// Loop through the buttons and add the active class to the
			// current/clicked button
			for (var i = 0; i < btns.length; i++) {
				btns[i].addEventListener("click", function() {
					var current = document.getElementsByClassName("active");
					current[0].className = current[0].className.replace(
							"active", "");
					this.className += "active";
					if (this.innerHTML == "<a>View Past Tickets</a>") {
						getReimbursements(userCred);
					} else {
						requestReimbursement(userCred);
					}
				});
			}
			getReimbursements(userCred);
			// activeButton.classList.toggle("active");
		}
	}
	xhr.open("GET", "employeeView.go.dash", true);
	xhr.send();
}

function getReimbursements(userCred) { // Gets reimbursements in Employee dash
	console.log("Initiating reimbursements retrieval");

	console.log("Username: " + userCred.username + "\n" + "Role id: "
			+ userCred.roleId);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var reimbursements = JSON.parse(xhr.responseText);
			console.log(reimbursements);
			viewReimbursements(userCred, reimbursements);
		}
	}
	xhr.open("POST", "getMyReimbursements.do.dash", true);
	xhr.send(JSON.stringify([ userCred.username, userCred.roleId, "UNKNOWN" ]));
}

function viewReimbursements(userCred, reimbursements) {
	console.log("Initiating reimbursements view");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('newView').innerHTML = xhr.responseText;
			if (reimbursements != null && reimbursements != undefined
					&& reimbursements.length != 0) {
				console.log(reimbursements);
				$("#defaultMessage").hide();
				$("#tableSection").show();
				for (var i = 0; i < reimbursements.length; i++) {
					console.log("Reimbursement: " + reimbursements[i].authorId);
					var status, type, resolvedTime = reimbursements[i].timeResolved;
					if (resolvedTime != null) {
						resolvedTime = new Date(resolvedTime).toUTCString()
					} else {
						resolvedTime = "--";
					}
					switch (reimbursements[i].statusId) {
					case 1:
						status = "PENDING";
						break;
					case 2:
						status = "APPROVED";
						break;
					case 3:
						status = "DENIED";
						break;
					default:
						status = "UNKNOWN";
						break;
					}
					switch (reimbursements[i].typeId) {
					case 1:
						type = "LODGING";
						break;
					case 2:
						type = "TRAVEL";
						break;
					case 3:
						type = "FOOD";
						break;
					default:
						type = "OTHER";
						break;
					}
					$("tbody").append(
							"<tr id='"
									+ reimbursements[i].reimbursementId
									+ "'><td>"
									+ reimbursements[i].reimbursementId
									+ "</td><td>"
									+ reimbursements[i].amount
									+ "</td><td>"
									+ new Date(reimbursements[i].timeSubmitted)
											.toUTCString() + "</td><td>"
									+ resolvedTime + "</td><td>"
									+ reimbursements[i].description
									+ "</td><td>" + userCred.firstName + " "
									+ userCred.lastName + "</td><td>"
									+ reimbursements[i].resolverId
									+ "</td><td>" + status + "</td><td>" + type
									+ "</td></tr>");
				}
			} else {
				$("#tableSection").hide();
			}
		}
	}
	xhr.open("GET", "viewReimbursements.go.dash", true);
	xhr.send();
}

function requestReimbursement(userCred) { // Shows view to request
											// reimbursement
	console.log("Initiating reimbursement request view");
	console.log("Username: " + userCred.username + "\n" + "Role id: "
			+ userCred.roleId);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('newView').innerHTML = xhr.responseText;
			var amount = document.getElementById("amount");
			var reimbType = document.getElementById("type");
			var description = document.getElementById("description");
			$("#addReimbursementButton").on('click', function() {
				if (amount.value == "") {
					alert("Please enter an amount");
				} else if (reimbType == 0) {
					alert("Please select reimbursement type");
				} else {
					addReimbursement(userCred);
				}
			});
		}
	}
	xhr.open("GET", "requestReimbursement.go.dash", true);
	xhr.send();
}

function addReimbursement(userCred) { // Creates new reimbursement, then goes
										// to view reimbursements
	console.log("Initiating reimbursement creation");
	console.log("Username: " + userCred.username + "\n" + "Role id: "
			+ userCred.roleId);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var reimbursements = JSON.parse(xhr.responseText);
			viewReimbursements(userCred, reimbursements);
		}
	}
	xhr.open("POST", "addReimbursement.do.dash", true);
	xhr.send(JSON.stringify([ userCred.username, userCred.roleId,
			$("#amount").val(), $("#type").val(), $("#description").val() ]));
}

function approveReimbursement(userCred, value, selectValue) {
	console.log("Approving reimbursement");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('appView').innerHTML = xhr.responseText;
			var newSelectValue = "UNKNOWN";
			$('select').on('change', function() {
				newSelectValue = this.value;
				showReimbursements(userCred, newSelectValue);
			});
			showReimbursements(userCred, selectValue);
		}
	}
	xhr.open("POST", "approveReimbursement.go.dash", true);
	xhr.send(JSON.stringify([ value, userCred.userId, selectValue ]));
}

function denyReimbursement(userCred, value, selectValue) {
	console.log("Getting resolver");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('appView').innerHTML = xhr.responseText;
			var newSelectValue = "UNKNOWN";
			$('select').on('change', function() {
				newSelectValue = this.value;
				showReimbursements(userCred, newSelectValue);
			});
			showReimbursements(userCred);
		}
	}
	xhr.open("POST", "denyReimbursement.go.dash", true);
	xhr.send(JSON.stringify([ value, userCred.userId, selectValue ]));
}