function getuserJsonData() {
	var json = {
		"username": $("#registerUser").val(),
	};
	return json;
}

function getregisterJsonData() {
	var json = {
		"username": $("#registerUser").val(),
		"password": $("#registerpassWord").val()
	};
	return json;
}

function loginGetJsonData() {
	var json = {
		"username": $("#userName").val(),
		"password": $("#passWord").val()
	};
	return json;
}

function isExist() {
	$.ajax({
		url: "/Sim-Stock/user/account/exist",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getuserJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			console.log(data.desc);
			console.log(data.statusCode);
			if (data.statusCode == 1000) {
				addregistertip('userTip', '用户名可以注册');
				addregisterimg('userimg', 'img/registerTrue.png');
			} else if (data.statusCode == 1001) {
				addregistertip('userTip', '用户名已存在');
				addregisterimg('userimg', 'img/registerFalse.png');
			}
		},
		error: function() {
			console.log("no");
		}
	});
}

function login() {
	$.ajax({
		url: "/Sim-Stock/user/account/login",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(loginGetJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			if(data.statusCode == 1009) {
				localStorage.setItem('id',data.data.account.id);
				localStorage.setItem('username',data.data.account.username);
				localStorage.setItem('createTime',data.data.account.createTime);				
				window.location.href="http://simstock.com/front/profile.html";
			document.getElementById('loginMessage').innerHTML ="";
			}else if(data.statusCode == 1010) {
				document.getElementById('loginMessage').innerHTML ="用户名或密码错误";
			}
		},
		error: function() {
			console.log("no");
		}
	});
}

function register() {
	$.ajax({
		url: "/Sim-Stock/user/account/register",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getregisterJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			if(data.statusCode == 1004){
				document.getElementById('registerMessage').innerHTML ="注册成功，页面即将跳转";
			}else if(data.statusCode == 1005) {
				document.getElementById('registerMessage').innerHTML ="注册失败请重试";
			}
			console.log(data.desc);
			console.log(data.statusCode);
		},
		error: function() {
			console.log("no");
		}
	});
}