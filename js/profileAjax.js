window.onload = function() {
	getInformation();
	showInfromation();
	// findInfromation();
}

function test() {
		console.log( $('#bornSelect').val());
}
function getJsonData() {	
	var json = {
    "id" : localStorage.getItem('id'),
	};
	return json;
}

function getModify() {
	var json = {
		"avatar": "test.png",
    "nickname": $('#personNick').val(),
    "gender": $('#gender option:selected').val(),
    "birthday": $('#bornSelect').val(),
    "place": $('#citySelect').val(),
    "hobby": $('#hobbySelect').val(),
    "phone": $('#phoneInform').val(),
    "email": $('#emailSet').val(),
    "profile": $('#profileSet').val(),
    "uaid": localStorage.getItem('id')
	};
	return json;
}

// 查看个人信息
function getInformation(){
	$.ajax({
		url: "/Sim-Stock/user/information/show",
		contentType: "application/json;charset=UTF-8",
		data:JSON.stringify(getJsonData()),
		dataType: "json",
		type: "post",
		success: function(data) {
			var infor = data.data.information;
			$(".userNick").text(infor.nickname);
			modify(data);
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

function modify(obj) {
	$('#personNick').val(""); 
	$("#personNick").val(obj.data.information.nickname);
	$('#gender').val(""); 
	$("#gender option[value='"+obj.data.information.gender+"']").attr("selected", "selected");
	$('#bornSelect').val(""); 
	$('#bornSelect').val(obj.data.information.birthday);
	$('#citySelect').val(""); 
	$('#citySelect').val(obj.data.information.place);
	$('#phoneInform').val(""); 
	$('#phoneInform').val(obj.data.information.phone);
	$('#emailSet').val(""); 
	$('#emailSet').val(obj.data.information.email);
	$('#hobbySelect').val(""); 
	$('#hobbySelect').val(obj.data.information.hobby);
	$('#profileSet').val(""); 
	$('#profileSet').val(obj.data.information.profile);
}



function showInfromation() {
	$(".userId").text(localStorage.getItem('username'));
	var date = new Date(parseInt(localStorage.getItem('createTime')));
	$(".userTime").text(date.getFullYear() + "-"+ parseInt(date.getMonth()+1) + "-" + date.getDate());
}