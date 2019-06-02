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


function userfocus() {
	// alert("获得焦点");
	console.log("获得焦点");
}

// 用户名输入框 失去焦点时调用用户名检测函数
function userblur() {
	// 是否需要判断合法还在商议
	usercheck();
}

// 判断用户名是否合法
function usercheck() {
	var checkusername = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
	var reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"); 
	var userName = document.getElementById("registerUser").value;
	if (!checkusername.test(userName)) {
		if(userName.length <8){
			alert("用户名不足8位");
		}else if(/^[0-9]+$/.test(userName)){
			alert('全部是数字');
		}else if (/^[a-zA-Z]+$/.test(userName)){
			alert('全部是英文');
		}else if (reg.test(userName))
			alert('用户名特殊字符');
		}else { 
			console.log("注册成功");
			return;
		}
}


function pswfocus() {
	// alert("获得焦点");
	console.log("获得焦点");
}

function pswblur() {
	var repassWord = document.getElementById("repassWord").value;
	if(repassWord.length > 1){
		repassworkcheck();
		passwordcheck();	
	}else{
	passwordcheck();	
	}
}

// 判断密码是否合法
function passwordcheck() {
	var checkpassword = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]\w{7,15}$/;
	var reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"); 
	var registerpsw = document.getElementById("registerpassWord").value;
	if (!checkpassword.test(registerpsw)) {
		if(registerpsw < 6){
			alert("密码不足6位");
			document.getElementById("registerpassWord").focus();
		}else if(/^[0-9]+$/.test(registerpsw)){
			alert('密码强度：低');
		}else if (/^[a-zA-Z]+$/.test(registerpsw)){
			alert('密码强度：低');
		}else if (reg.test(registerpsw))
			alert('密码强度：高');
		}else {
		console.log("注册成功");
		return;
	}
}

// 密码完成度提示
function checkValue() {
        $('#note1').css('color', /[a-z]/.test(this.value) && /[A-Z]/.test(this.value) ? 'green' : 'black');
        $('#note2').css('display', /[\d~!@#\$%^&\*\(\)_]/.test(this.value) ? 'green' : 'black');
        $('#note3').css('color', this.value.length >= 8 ? 'green' : 'black');
    }
    $('#registerpassWord').bind({input:checkValue,propertychange:checkValue})
		

function repswfocus() {
	// alert("获得焦点");
	console.log("获得焦点");
}

function repswblur() {
	repassworkcheck();
}

// 重新输入密码核对
function repassworkcheck() {
	console.log("重新输入密码框");
	var registerpsw = document.getElementById("registerpassWord").value;
	var repassWord = document.getElementById("repassWord").value;
	if(registerpsw === repassWord){
		alert("1");
	}else{
		alert("2");
	}
}


showsign();
hiddensign();
resigiterlogin();
