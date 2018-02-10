/**
 * 
 */
window.onload = function(){
	
	var firstName = document.getElementById('firstName');
	var lastName = document.getElementById('lastName');
	var userName = document.getElementById('userName');
	var password = document.getElementById('inputPassword');
	var confirmUser = document.getElementById('confirmUserName');
	var confirmPass = document.getElementById('confirmPassword');
	var email = document.getElementById('inputEmail');
	var registerButton = document.getElementById('registerButton');
	
	registerButton.disabled = true;
	var mistakes = [true, true, true, true, true, true, true];
	
	firstName.addEventListener("blur", function() {
		if(this.value == "") {
			alert("Please fill out the first field with your first name");
			mistakes[0] = true;
			registerButton.disabled = true;
		} else {
			mistakes[0] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	lastName.addEventListener("blur", function() {
		if(this.value == "") {
			alert("Please fill out the second field with your last name");
			mistakes[1] = true;
			registerButton.disabled = true;
		} else {
			mistakes[1] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	userName.addEventListener("blur", function() {
		if(this.value == "") {
			alert("Please fill out the third field with a new username");
			mistakes[2] = true;
			registerButton.disabled = true;
		} else {
			mistakes[2] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	password.addEventListener("blur", function() {
		if(this.value == "") {
			alert("Please fill out the fourth field with a password");
			mistakes[3] = true;
			registerButton.disabled = true;
		} else {
			mistakes[3] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	confirmUser.addEventListener("blur", function() {
		if(this.value != userName.value) {
			alert("Your confirmed username does not match with username.");
			mistakes[4] = true;
			registerButton.disabled = true;
		} else {
			mistakes[4] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	confirmPass.addEventListener("blur", function() {
		if(this.value != password.value) {
			alert("Your confirmed password does not match with password.");
			mistakes[5] = true;
			registerButton.disabled = true;
		} else {
			mistakes[5] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
	
	email.addEventListener("blur", function() {
		if(this.value == "") {
			alert("Please fill out the seventh field with your email");
			mistakes[6] = true;
			registerButton.disabled = true;
		} else {
			mistakes[6] = false;
			if(!(mistakes[0] || mistakes[1] || mistakes[2] || mistakes[3] ||
					mistakes[4] || mistakes[5] || mistakes[6])) {
				registerButton.disabled = false;
			} else {
				registerButton.disabled = true;
			}
		}
	});
};

function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//console.log(xhr.responseText);
			document.getElementById('view').innerHTML = xhr.responseText;		
		}
	}
	console.log("getting homepage")
	xhr.open("GET", "managerView.go.dash", true);
	xhr.send();
};