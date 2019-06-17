window.onload = function() {
	getInformation();
	showInfromation();
}


// 查看个人信息
function getInformation(){
	console.log("查看个人信息");
	$.ajax({
		url: "/Sim-Stock/user/information/find",
		contentType: "application/json;charset=UTF-8",
		type: "post",
		success: function(data) {
			showAll(data);
		},
		error: function() {
		}
	});
}


// 得到修改后的用户名
function getNickname() {
		var json = {
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
		error: function() {
		}
	});
}

// 得到修改后的性别
function getGender(gender) {
		var json = {
			"field": "gender",
			"value": gender,
		};
		return json;

}
// 发送修改性别请求
function changeGender(gender) {
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getGender(gender)),
		dataType: "json",
		type: "post",
		success: function(data) {
			modifyNGender(gender);
		},
		error: function() {
		}
	});
}
// 修改个人信息
function modifyInformation(){
	console.log($('#gender option:selected').val());
	$.ajax({
		url: "/Sim-Stock/user/information/modify",
		contentType: "application/json;charset=UTF-8",
		data:JSON.stringify(getModify()),
		dataType: "json",
		type: "post",
		success: function(data) {
			modify(data);
		},
		error: function() {
		}
	});
}


function showInfromation() {
	$(".userId").text(localStorage.getItem('username'));
	var date = new Date(parseInt(localStorage.getItem('createTime')));
	$(".userTime").text(date.getFullYear() + "-"+ parseInt(date.getMonth()+1) + "-" + date.getDate());
}