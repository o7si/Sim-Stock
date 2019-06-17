function showMenu(div) {
	document.getElementById(div).style = "display : block";
}

function hiddenMenu(div) {
	document.getElementById(div).style = "display : none";
}

function changeMainPage() {
	document.getElementById('mainPage').src = 'img/mainPage2.png';
}

function hiddenMainPage() {
	document.getElementById('mainPage').src = 'img/mainPage.png';
}

function openMenu() {
	$('#asideLeft_small').css('display', 'none');
	$('#asideLeft_big').css('display', 'block');
	$('#article').css('width', '86%');
}

function closeMenu() {
	$('#asideLeft_small').css('display', 'block');
	$('#asideLeft_big').css('display', 'none');
	$('#article').css('width', '96.4%');
}

function clickperson() {
	$('.personDatabtn').click(function() {
		closeperson();
		$('.personData').css('display', 'block');
	})
	$('.personDeterminebtn').click(function() {
		closeperson();
		$('.personDetermine').css('display', 'block');
	})
	$('.personSafebtn').click(function() {
		closeperson();
		$('.personSafe').css('display', 'block');
	})
	$('.vipMemberbtn').click(function() {
		closeperson();
		$('.vipMember').css('display', 'block');
	})
	$('.personFriendbtn').click(function() {
		closeperson();
		$('.personFriend').css('display', 'block');
	})
}

function closeperson() {
	$('.personData').css('display', 'none');
	$('.personDetermine').css('display', 'none');
	$('.personSafe').css('display', 'none');
	$('.vipMember').css('display', 'none');
	$('.personFriend').css('display', 'none');
}
// 
// function mdPsw(obj) {
// 	if(obj == true) {
// 		$('#modifyPassword input').val("");
// 		console.log("上传成功");
// 	}else if(obj == false) {
// 		$('#modifyPassword input').val("");
// 	}
// 	$('#modifyPassword').css('display','none');
// }
// 
// 

function showModifyButton(id) {
	$(id).css('display', 'block');
}

function hiddenModifyButton(id) {
	$(id).css('display', 'none');
}

function profileValue(json) {
	console.log("profileValue");
	console.log(json.statusCode);
}

function showAll(json) {
	// $('.nickName').text(json.data.information.nickname);
	// console.log(json.data.information.gender);
	// if(json.data.information.gender == 0) {
	// 	$('.gender').text("保密");
	// }else if(json.data.information.gender == 1) {
	// 	$('.gender').text("男");
	// }else if(json.data.information.gender == 2) {
	// 	$('.gender').text("女");
	// }else if(json.data.information.gender == 3) {
	// 	$('.gender').text("其他");
	// }
	// $('.birthday').text(json.data.information.birthday);
	// $('.address').text(json.data.information.address);
	// $('.occupation').text(json.data.information.occupation);
	// $('.phone').text(json.data.information.phone);
	// $('.email').text(json.data.information.email);
	// $('.profile').text(json.data.information.profile);
}


var nickname;
var gender;


function nickNameShow() {
	nickname = $('.nickName').text();
	$('#nickName').css('display', 'none');
	$('#nameConfirm').css('display', 'block');
	$('.nickName').html("<input/> ");
	$('.nickName input').css({
		"width": "200px",
		"height": "26px",
		"outline": "none",
		"font-size": "16px",
		"padding-left": "10px"
	});
	$('.nickName input').val(nickname);
}

function nickNameHidden() {
	$('#nameConfirm').css('display', 'none');
	$('.nickName input').remove();
	$('.nickName').text(nickname);
	$('#nickprofile').mousemove(function() {
		$("#nickName").css("display", "block");
	}).mouseleave(function() {
		$("#nickName").css("display", "none");
	})
}

function nickNameInput() {
	$('#nameConfirm').css('display', 'none');
	var modifyname = $('.nickName input').val();
	$('.nickName input').remove();
	$('.nickName').text(modifyname);
	$('#nickprofile').mousemove(function() {
		$("#nickName").css("display", "block");
	}).mouseleave(function() {
		$("#nickName").css("display", "none");
	})
	changeName(modifyname);
}

// 修改性别
function genderShow() {
	gender = $('.gender').text();
	console.log(gender);
	$("#select_id").append("<option value='Value'>Text</option>");
	$('#gender').css('display', 'none');
	$('#genderConfirm').css('display', 'block');
	$('.gender').html("<select></select>");
	$(".gender select").append("<option value='" + 0 + "'>保密</option>");
	$(".gender select").append("<option value='" + 1 + "'>男</option>");
	$(".gender select").append("<option value='" + 2 + "'>女</option>");
	$(".gender select").append("<option value='" + 3 + "'>其他</option>");
	$('.gender select').css({
		"width": "200px",
		"height": "26px",
		"outline": "none",
		"font-size": "16px",
		"padding-left": "10px"
	});
	if (gender == '保密') {
		$('.gender select').val(0);
	} else if (gender == '男') {
		$('.gender select').val(1);
	} else if (gender == '女') {
		$('.gender select').val(2);
	} else if (gender == '其他') {
		$('.gender select').val(3);
	}
}

function genderHidden() {
	$('#genderConfirm').css('display', 'none');
	$('.gender select').remove();
	$('.gender').text(gender);
	$('#genderprofile').mousemove(function() {
		$("#gender").css("display", "block");
	}).mouseleave(function() {
		$("#gender").css("display", "none");
	})
}

function genderInput() {
	$('#genderConfirm').css('display', 'none');
	var modifygender = $('.gender select').val();
	if (modifygender == 0) {
		$('.gender').text("保密");
	} else if (modifygender == 1) {
		$('.gender').text("男");
	} else if (modifygender == 2) {
		$('.gender').text("女");
	} else if (modifygender == 3) {
		$('.gender').text("其他");
	}
	$('.gender select').remove();
	$('#genderprofile').mousemove(function() {
		$("#gender").css("display", "block");
	}).mouseleave(function() {
		$("#gender").css("display", "none");
	})

	changeGender(modifygender);
}



// 修改用户名
function modifyName(name) {
	$('.nickName').text(name);
}

function modifyGender(gender) {
	$('.gender').text(gender);
}
