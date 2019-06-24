// 注册登录界面
var signscreen = document.getElementById('signScreen');

function showsign() {
	var signclick = document.getElementById('navSign');
	var resigiterform = document.getElementById('registerForm');
	var loginform = document.getElementById('loginForm');
	signclick.onclick = function() {
		signscreen.style.display = 'block';
		resigiterform.style.display = 'none';
		loginform.style.display = 'block';
	}
	document.getElementById('registerMessage').innerHTML ="";
}
// 隐藏登录显示注册
function hiddensign() {
	var exitsign = document.getElementById('exitSign');
	var resigiterform = document.getElementById('registerForm');
	var loginform = document.getElementById('loginForm');
	exitsign.onclick = function() {
		signscreen.style.display = 'none';
		loginform.reset();
		resigiterform.reset();
		deleteregisterimg();
		deleteregisterusertip();
		deleteregisterpswtip();
		deleteregisterrepswtip();
		document.getElementById('registerMessage').innerHTML ="";
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
		deleteregisterimg();
		deleteregisterusertip();
		deleteregisterpswtip();
		deleteregisterrepswtip();
		document.getElementById('registerMessage').innerHTML ="";
		document.getElementById('loginMessage').innerHTML ="";
	}
}


function userfocus() {
	console.log("获取焦点");
}

// 用户名输入框 失去焦点时调用用户名检测函数
function userblur() {
	// 是否需要判断合法还在商议
	usercheck();
}

// 获取焦点时 使提示信息消失
function loginuserfocus() {
	document.getElementById('falseLogin').innerHTML = "";
}

// 判断用户名是否合法
function usercheck() {
	var checkusername = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
	var reg = new RegExp("[^[A-Za-z0-9]+$]");
	var userimg = document.getElementById('userImg');
	var userName = document.getElementById("registerUser").value;
	if (userName.length < 8) {
		addregisterimg('userimg', 'img/registerFalse.png');
		addregistertip('userTip', '用户名为8到18位');
	} else if (/^[0-9]+$/.test(userName)) {
		addregisterimg('userimg', 'img/registerFalse.png');
		addregistertip('userTip', '用户名至少包含一位英文');
	} else if (/^[a-zA-Z]+$/.test(userName)) {
		addregisterimg('userimg', 'img/registerFalse.png');
		addregistertip('userTip', '用户名至少包含一位数字');
	} else if (!checkusername.test(userName)) {
		addregisterimg('userimg', 'img/registerFalse.png');
		addregistertip('userTip', '用户名禁止使用特殊字符');
	} else {
		addregisterimg('userimg', 'img/loading.gif');
		isExist();
		document.getElementById("registerIn").disabled = true;
		deleteregisterusertip();
		return;
	}
}

function pswfocus() {
	console.log("获得焦点");
}

function pswblur() {
	var repassWord = document.getElementById("repassWord").value;
	if (repassWord.length > 1) {
		repassworkcheck();
		passwordcheck();
	} else {
		passwordcheck();
	}
}

// 判断密码是否合法
function passwordcheck() {
	var checkpassword = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]\w{7,15}$/;
	var reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var registerpsw = document.getElementById("registerpassWord").value;
	if (!checkpassword.test(registerpsw)) {
		if (registerpsw.length < 6) {
			addregisterimg('registerpassWordimg', 'img/registerFalse.png');
			addregistertip('registerpassWordTip', '密码为6到16位');
		} else if (/^[0-9]+$/.test(registerpsw)) {
			addregisterimg('registerpassWordimg', 'img/registerWarning.png');
			addregistertip('registerpassWordTip', '密码强度低');
		} else if (/^[a-zA-Z]+$/.test(registerpsw)) {
			addregisterimg('registerpassWordimg', 'img/registerWarning.png');
			addregistertip('registerpassWordTip', '密码强度低');
		} else if (/^[0-9a-zA-Z]+$/.test(registerpsw) || /^[A-Za-z0-9]+$/.test(registerpsw)) {
			addregisterimg('registerpassWordimg', 'img/registerWarning.png');
			addregistertip('registerpassWordTip', '密码强度中');
		} else if (reg.test(registerpsw)) {
			addregisterimg('registerpassWordimg', 'img/registerTrue.png');
			addregistertip('registerpassWordTip', '密码强度高');
		} else {
			addregisterimg('registerpassWordimg', 'img/loading.gif.png');
			deleteregisterpswtip();
			return;
		}
	}
}


function repswfocus() {
	console.log("获得焦点");
}

function repswblur() {
	repassworkcheck();
}

// 重新输入密码核对
function repassworkcheck() {
	var registerpsw = document.getElementById("registerpassWord").value;
	var repassWord = document.getElementById("repassWord").value;
	if (registerpsw === repassWord) {
		addregisterimg('repassWordimg', 'img/registerTrue.png');
		deleteregisterrepswtip();
	} else {
		addregisterimg('repassWordimg', 'img/registerFalse.png');
		addregistertip('repassWordTip', '两次密码输入不一致');
	}
}

// 刷新提醒图片
function addregisterimg(obj, way) {
	var img = document.getElementById(obj);
	img.src = way;
	img.style.width = "20px"
	img.style.height = "20px"
	img.style.display = "block";
};

// 清除提醒图片
function deleteregisterimg(obj) {
	var imguser = document.getElementById('userimg');
	var imgrepsw = document.getElementById('registerpassWordimg');
	var imgrerepsw = document.getElementById('repassWordimg');
	imguser.style.display = "none";
	imgrepsw.style.display = "none";
	imgrerepsw.style.display = "none";
}

// 添加提示文字
function addregistertip(obj, sentence) {
	var newdiv = document.getElementById(obj);
	newdiv.innerHTML = sentence;
};

//清除提醒文字
function deleteregisterusertip() {
	$('#userTip').empty();
}

function deleteregisterpswtip() {
	$('#registerpassWordTip').empty();
}

function deleteregisterrepswtip() {
	$('#repassWordTip').empty();
}

function checkregister() {
	var rUser = $("#registerUser").val();
	var repsw = $("#registerpassWord").val();
	var rere = $("#repassWord").val();
	if (rUser.length > 0 && repsw.length > 0 && rere.length > 0) {
		if (repsw == rere) {
			document.getElementById("registerIn").disabled = false;
		}
	}
}

function checklogin() {
	var userName= $("#userName").val();
	var passWord = $("#passWord").val();
	if (userName.length > 0) {
		if (passWord.length > 0){
			document.getElementById("signIn").disabled = false;
		}
	}else {
		document.getElementById("signIn").disabled = true;
	}
}


showsign();
hiddensign();
resigiterlogin();
