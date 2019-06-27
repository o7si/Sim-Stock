window.onload = function() {
	var x = document.cookie;
	console.log(x);
	getInformation();
	showInfromation();

}

function getNewPsw() {
	var json = {
		"token": document.cookie,
		"oldPassword": $('#oldPsw').val(),
		"newPassword": $('#profilePsw').val(),
		"repeatPassword": $('#repassWord').val(),
	}
	return json;
}

function newPswInput() {
	$.ajax({
		url: "/Sim-Stock/user/account/resetPassword",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getNewPsw()),
		dataType: "json",
		type: "post",
		success: function(data) {
			alert(data.desc);
		},
		error: function() {}
	})
}

function getIninfor() {
	var json = {
		"token": document.cookie
	}
	return json;
}

// 查看个人信息
function getInformation() {
	console.log("查看个人信息");
	$.ajax({
		url: "/Sim-Stock/user/information/find",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getIninfor()),
		dataType: "json",
		type: "post",
		success: function(data) {
			showAll(data);
			localStorage.setItem('nickname', data.data.information.nickname);
		},
		error: function() {}
	});
}


// 得到修改后的用户名
function getNickname() {
	var json = {
		"token": document.cookie,
		"field": "nickname",
		"value": $('.nickName').text(),
	};
	return json;

}
// 发送修改用户名请求 
function changeName(name) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getNickname()),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyName(name);
		},
		error: function() {}
	});
}

// 得到修改后的性别
function getGender(gender) {
	var json = {
		"token": document.cookie,
		"field": "gender",
		"value": gender,
	};
	return json;
}
// 发送修改性别请求
function changeGender(gender) {
	console.log(gender)
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getGender(gender)),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyGender(gender);
		},
		error: function() {}
	});
}


//得到修改后的生日
function getBirthday(birthday) {
	var json = {
		"token": document.cookie,
		"field": "birthday",
		"value": birthday,
	};
	return json;
}

//发送修改生日的请求
function changeBirthday(birthday) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getBirthday(birthday)),
		dataType: "json",
		type: "post",
		success: function(data) {
			// modifyBirthday(birthday);
		},
		error: function() {}
	});
}




// 得到修改后的地址
function getAddress(address) {
	var json = {
		"token": document.cookie,
		"field": "place",
		"value": address,
	};
	return json;
}

// 发送修改地址请求
function changeAddress(address) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getAddress(address)),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyAddressr(address);
		},
		error: function() {}
	});
}

// 获得职业
function getOccupation(occupation) {
	var json = {
		"token": document.cookie,
		"field": "industry",
		"value": occupation,
	};
	return json;
}

function changeOccupation(occupation) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getOccupation(occupation)),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyOccupation(data.data.information);
		},
		error: function() {}
	});
}

function getProfile(profile) {
	var json = {
		"token": document.cookie,
		"field": "profile",
		"value": profile,
	};
	return json;
}



function changeProfile(profile) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getProfile(profile)),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyProfile(data.data.profile);
		},
		error: function() {}
	});
}



function showInfromation() {
	$(".userId").text(localStorage.getItem('username'));
	var date = new Date(parseInt(localStorage.getItem('createTime')));
	$(".userTime").text(date.getFullYear() + "-" + parseInt(date.getMonth() + 1) + "-" + date.getDate());
}
