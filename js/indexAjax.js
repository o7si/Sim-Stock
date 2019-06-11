function getJsonData() {
    var json = {
			"username": $("#registerUser").val(),
			"passWord":$("#registerpassWord").val()			
    };
		alert(json.username);
    return json;
}

function loginGetJsonData() {
    var json = {
			"username": $("#userName").val(),
			"passWord":$("#passWord").val()			
    };
    return json;
}

function isExist() {
	$.ajax({
		url: "/Sim-Stock/user/isExist",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			console.log(data.desc);
			console.log(data.statusCode);
		},
		error: function() {
			console.log("no");
		}
	});
}

function login() {
	$.ajax({
		url: "/Sim-Stock/user/login",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(loginGetJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			alert(data.desc);
			console.log(data.statusCode);
		},
		error: function() {
			console.log("no");
		}
	});
}

function register() {
	$.ajax({
		url: "/Sim-Stock/user/register",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			console.log(data.desc);
			console.log(data.statusCode);
		},
		error: function() {
			console.log("no");
		}
	});
}

