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
	$('#asideLeft_small').css('display','none');
	$('#asideLeft_big').css('display','block');
	$('#article').css('width','86%');
}

function closeMenu() {
	$('#asideLeft_small').css('display','block');
	$('#asideLeft_big').css('display','none');
	$('#article').css('width','96.4%');
}

function clickperson() {
	$('.personDatabtn').click(function(){
		closeperson();
		$('.personData').css('display','block');
	})
	$('.personDeterminebtn').click(function(){
		closeperson();
		$('.personDetermine').css('display','block');
	})
	$('.personSafebtn').click(function(){
		closeperson();
		$('.personSafe').css('display','block');
	})
	$('.vipMemberbtn').click(function(){
		closeperson();
		$('.vipMember').css('display','block');
	})
	$('.personFriendbtn').click(function(){
		closeperson();
		$('.personFriend').css('display','block');
	})
}

function closeperson() {
	$('.personData').css('display','none');
	$('.personDetermine').css('display','none');
	$('.personSafe').css('display','none');
	$('.vipMember').css('display','none');
	$('.personFriend').css('display','none');
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

function nickNameShow() {
	$('#nickName').css('display','none');
	$('#nameConfirm').css('display','block');
	var nickname = $('.nickName').text();
	$('.nickName').html("<input/> ");
	$('.nickName input').css({"width":"200px","height":"26px","outline":"none","font-size":"16px","padding-left":"10px"});
	$('.nickName input').attr('max-length','20');
	$('.nickName input').val(nickname);
}

function nickNameHidden() {
	$('#nameConfirm').css('display','none');
	// $('.nickName').html("<div>"++"</div>");
}