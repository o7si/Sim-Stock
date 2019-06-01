// 注册登录界面
var signscreen = document.getElementById('signScreen');

function showSign() {
	var signclick = document.getElementById('navSign');
	var resigiterform = document.getElementById('registerForm');
	var loginform = document.getElementById('loginForm');
	signclick.onclick = function() {
		signscreen.style.display = 'block';
		resigiterform.style.display = 'none';
		loginform.style.display = 'block';
	}
}
// 隐藏登录显示注册
function hiddenSign() {
	var exitsign = document.getElementById('exitSign');
	var resigiterform = document.getElementById('registerForm');
	var loginform = document.getElementById('loginForm');
	exitsign.onclick = function() {
		signscreen.style.display = 'none';
		loginform.reset();
		resigiterform.reset();

	}
}
// 隐藏注册显示登陆
function resigiterlogin() {
	var resigiterbutton = document.getElementById('registerButton');
	var signbutton = document.getElementById('signButton');
	var resigiterform = document.getElementById('registerForm');
	var loginform = document.getElementById('loginForm');
	resigiterbutton.onclick = function() {
		loginform.style.display = 'none';
		loginform.reset();
		resigiterform.style.display = 'block';
	}
	signbutton.onclick = function() {
		loginform.style.display = 'block';
		resigiterform.style.display = 'none';
		resigiterform.reset();
	}
}


function Userfocus() {
	// alert("获得焦点");
	console.log("获得焦点");
}

function Userblur() {
	// alert("失去焦点");
	console.log("失去焦点");
}

showSign();
hiddenSign();
resigiterlogin();
