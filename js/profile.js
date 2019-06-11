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
}

function closeMenu() {
	$('#asideLeft_small').css('display','block');
	$('#asideLeft_big').css('display','none');
}
