window.onload = function() {
	var time = setInterval(function() {
		onRequest();
	}, 1000);
	getWallet();
	holdStock();
	getHoldList();
	changeaddCurrent();
	changeredCurrent();
}

var Stocktariff;

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


// 获取单只股票Id
function getSingle() {
	var json = {
		"stockId": parseInt(localStorage.getItem('simId')),
	};
	return json;
}
// 获取单只股票信息
// function getSingleData() {
// 	$.ajax({
// 		url: "/Sim-Stock/stock/getSingleData",
// 		contentType: "application/json;charset=UTF-8",
// 		data: JSON.stringify(getSingle()),
// 		dataType: "json",
// 		type: "post",
// 		success: function(res) {
// 			// 调用书写数据函数
// 			trade(res);
// 			simWrite(res);
// 		},
// 		error: function() {}
// 	});
// }

// 获取请求股票数据
function getData() {
	var json = {
		"stockId": parseInt(localStorage.getItem('simId')),
		"recordNumber": 192
	};
	return json;
}

// 向后台请求数据
function onRequest() {
	$.ajax({
		url: "/Sim-Stock/market/getLateList",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getData()),
		dataType: "json",
		type: "post",
		success: function(res) {
			// console.log("正在请求数据");
			if (res.data.markets.size > 0) {
				Stocktariff = res.data.markets[0].postPrice;
			}
			rightWrite(res);
			drawStock(res);
			setData(res);
		},
		error: function() {

		}
	})
}

// 绘制股票图
function drawStock(res) {
	// console.log(res.data.markets)
	var arr = res.data.markets;
	if (arr == null) {
		return;
	}
	var maxValue = arr[0].postPrice;
	var minValue = arr[0].postPrice;
	for (var i = 1; i < arr.length; i++) {
		minValue = Math.min(minValue, arr[i].postPrice);
		maxValue = Math.max(maxValue, arr[i].postPrice);
	}

	minValue -= (maxValue - minValue) * 0.1;
	maxValue += (maxValue - minValue) * 0.1;

	var canvas = document.getElementById('canvas');
	var context = canvas.getContext("2d");
	// 获得数组的长度并且获得x轴变化量
	var xChange = 1000 / arr.length
	var xBegin = 10;
	var height = [];
	for (var i = 0; i < arr.length; i++) {
		var num = arr[i].postPrice;
		var point = 600 - ((num - minValue) / (maxValue - minValue) * 600)
		height[i] = point;
	}

	// console.log('before' + height);

	height = height.reverse();
	// console.log('after' + height);
	context.clearRect(0, 0, 1000, 600);

	context.beginPath()
	for (var i = 0; i < height.length - 1; ++i) {
		context.moveTo(0, 10);
		context.lineTo(1000, 10);
		context.moveTo(0, 150);
		context.lineTo(1000, 150);

		context.moveTo(0, 450);
		context.lineTo(1000, 450);
		context.moveTo(0, 590);
		context.lineTo(1000, 590);
	}
	context.closePath()
	context.lineWidth = 3;
	context.strokeStyle = "#ccc";
	context.stroke();
	context.beginPath()
	for (var i = 0; i < height.length - 1; ++i) {
		context.moveTo(0, 300);
		context.lineTo(1000, 300);
	}
	context.closePath()
	context.strokeStyle = "greenyellow";
	context.stroke();
	context.beginPath()
	for (var i = 0; i < height.length - 1; ++i) {
		context.moveTo((i * xChange) + 10, height[i])
		context.lineTo(((i + 1) * xChange) + 10, height[i + 1])
	}
	context.closePath()
	context.lineWidth = 2;
	context.strokeStyle = "#609aff";
	context.stroke();
}

function setData(res) {
		var arr = res.data.markets;
		var maxValue = arr[0].postPrice;
		var minValue = arr[0].postPrice;
		for (var i = 1; i < arr.length; i++) {
			minValue = Math.min(minValue, arr[i].postPrice);
			maxValue = Math.max(maxValue, arr[i].postPrice);
		}
		var mid = (maxValue + minValue) / 2;
		var top = (maxValue + mid) / 2;
		var foot = (mid + minValue) / 2;

		$('#maxvalue').text(maxValue.toFixed(2))
		$('#top').text(top.toFixed(2))
		$('#mid').text(mid.toFixed(2))
		$('#foot').text(foot.toFixed(2))
		$('#minvalue').text(minValue.toFixed(2))
}

function simWrite(res) {
	$('#stockName').text(res.data.stock.name)
	$("#stockTime").text("No." + res.data.stock.id);
}

function rightWrite(res) {
		var num = (res.data.markets[0].changePrice);
		var percent = (res.data.markets[0].percent * 100).toFixed(2) + '%'
		if (num > 0) {
			$('#changePrice').css('color', 'red')
			$('#stockPercent').css('color', 'red')
			$('#changePrice').html('<i>变化值：</i>+' + num.toFixed(2));
			$('#stockPercent').html('<i>变化率：</i>+' + percent)
		} else {
			$('#changePrice').css('color', '#3c850f')
			$('#stockPercent').css('color', '#3c850f')
			$('#changePrice').html('<i>变化值：</i>' + num.toFixed(2));
			$('#stockPercent').html('<i>变化率：</i>' + percent)
	}
}

// 交易函数
function trade(res) {
	// console.log(res)
	$('#hold em').text(res.data.stock.hold);
	$('#prevalue em').text((res.data.stock.price).toFixed(2))
	$('#marketvalue em').text((res.data.stock.marketValue).toFixed(2))
	var date = new Date(parseInt(res.data.stock.appear));
	$("#appear em").text(date.getFullYear() + "-" + parseInt(date.getMonth() + 1) + "-" + date.getDate());
}



function getToken() {
	var json = {
		"token": document.cookie,
	}
	return json;
}

// 获取钱包信息
function getWallet() {
	$.ajax({
		url: "/Sim-Stock/wallet/getData",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getToken()),
		dataType: "json",
		type: "post",
		success: function(res) {
			walletCount(res);
		},
		error: function() {}
	})
}

// 查看钱包 获取最大购买量
function walletCount(res) {
	$('#myMoney em').text((res.data.wallet.balance).toFixed(2))
	// var x = manyStock((res.data.wallet.balance).toFixed(2))
	var x = hold;
	var y = price;
	if (x < y) {
		$('#maxAdd i').text(x)
	} else {
		$('#maxAdd i').text(y)
	}
}

var hold = 0;
var price = 0;
//  最多购买量
function holdStock() {
	$.ajax({
		url: "/Sim-Stock/stock/getSingleData",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getSingle()),
		dataType: "json",
		type: "post",
		success: function(res) {
			console.log("tader",res)
			hold = res.data.stock.hold;
			price = (res.data.stock.price);
			trade(res);
			simWrite(res);
		},
		error: function() {}
	});
}

// 获取个人拥有的股票数
function getHoldList() {
	$.ajax({
		url: "/Sim-Stock/hold/getHold",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getHoldJson()),
		dataType: "json",
		type: "post",
		success: function(res) {
			maxSoldWrite(res)
		},
		error: function() {}
	})
}

function getHoldJson() {
	var simId = parseInt(localStorage.getItem('simId'))
	var json = {
		"token": document.cookie,
		"stockId": simId
	}
	return json;
}

function maxSoldWrite(res) {
	if (res.data.hold == null || res.data.hold == 0) {
		$('#maxReduce i').text("0")
	} else {
		var temp = res.data.hold.number;
		console.log(res)
		$('#maxReduce i').text(temp)
	}

}

// 购买股票输入框部分
var num = 1;
var reduce = 1;

// 增加购买按钮
function addStock() {
	var x = $('#stockInput').val();
	if (x == "") {
		x = 0;
	}
	if (x < hold) {
		$('#stockInput').val(parseInt(x) + parseInt(num))
	} else {
		$('#stockInput').val(parseInt(x))
	}
}

// 减少购买按钮
function reduceStock() {
	var x = $('#stockInput').val();
	if (x == "") {
		x = 0;
	}
	if (x <= 0) {
		$('#stockInput').val(parseInt(x))
	} else {
		$('#stockInput').val(parseInt(x) - parseInt(num))
	}
}

// 增加售出按钮
function addSold() {
	var x = $('#soldInput').val();
	if (x == "") {
		x = 0;
	}
	$('#soldInput').val(parseInt(x) + parseInt(reduce));
}

// 减少售出按钮
function reduceSold() {
	var x = $('#soldInput').val();
	if (x == "") {
		x = 0;
	}
	if (x <= 0) {
		$('#soldInput').val(parseInt(x))
	} else {
		$('#soldInput').val(parseInt(x) - parseInt(reduce))
	}
}

// 改变增加时被选中的等级
function changeaddCurrent() {
	var test = function() {
		num = $(this).val();
		console.log(num)
		$('#addStock button').attr("id", "")
		$(this).attr("id", "addCurrent");
	};
	$('.addNum').bind("click", test);
}

// 改变减少时被选中的等级
function changeredCurrent() {
	var test = function() {
		reduce = $(this).val();
		console.log($(this).val())
		$('#reduceStock button').attr("id", "")
		$(this).attr("id", "redCurrent");
	};
	$('.redNum').bind("click", test);
}


// 过滤input中的其他字符
function ValidateNumber(e, pnumber) {
	if (!/^\d+$/.test(pnumber)) {
		$(e).val(/^\d+/.exec($(e).val()));
	} else if (pnumber >= hold) {
		$(e).val(hold);
	}
	return false;
}

function soldValidateNumber(e, pnumber) {
	if (!/^\d+$/.test(pnumber)) {
		$(e).val(/^\d+/.exec($(e).val()));
	} else if (pnumber >= hold) {
		$(e).val(hold);
	}
	return false;
}

// 购买股票
function buyStockJson() {
	var json = {
		"token": document.cookie,
		"password": 123456,
		"stockId": parseInt(localStorage.getItem('simId')),
		"number": buyNumber
	};
	return json;
}
// 购买股票请求数据
function buyStock() {
	$.ajax({
		url: "/Sim-Stock/transaction/buy",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(buyStockJson()),
		dataType: "json",
		type: "post",
		success: function(res) {
			alert("购买成功");
			getHoldList();
			getWallet();
		},
		error: function() {
			alert("购买失败")
		}
	});
	// window.location.reload()
}

// 售出购买
function buyNow() {
	buyNumber = parseInt($('#stockInput').val())
	buyStock();
}

function soldNow() {
	soldNumber = parseInt($('#soldInput').val())
	soldStock();
}

function soldStockJson() {
	var json = {
		"token": document.cookie,
		"password": 123456,
		"stockId": parseInt(localStorage.getItem('simId')),
		"number": soldNumber,
	};
	return json;
}

function soldStock() {
	$.ajax({
		url: "/Sim-Stock/transaction/sell",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(soldStockJson()),
		dataType: "json",
		type: "post",
		success: function(res) {
			alert("售出成功")
			getHoldList();
			getWallet();
		},
		error: function() {
			alert("售出失败")
		}
	});
	// window.location.reload()
}
