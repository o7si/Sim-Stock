
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


// 修改钱包页面展示信息
function modifyWallet(json) {
	$('#wallet-id span').text("No." + json.data.wallet.id)
	$('#wallet-balance span').text("余额：" + (json.data.wallet.balance).toFixed(2) + " 元")
}

// 修改股票持有情况
function modifyHoldList(json) {
	var arr = json.data.holds
	if(arr.length < 10){
		for(var i = 0; i < arr.length; i ++ ) {
			$('#stock-show').append("<div class='appendDiv' id=" + parseInt(i+1) + ">股票编号：<div class='jmpPublic' onclick='jmpSim("+ arr[i].sid +")'>" + arr[i].sid +"</div><br><div>持有数量："+ arr[i].number + "  股</div><br></div>")
		}
	}else {
			console.log("error")
	}
}

function jmpSim(res) {
	console.log(res)
	localStorage.setItem('simId',res);
	window.location.href = "sim.html";
}