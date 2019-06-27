window.onload = function() {
	// 加载完成后自动调用钱包信息
	getWallet();
	// 加载完成后自动获取开户信息
	onRequest();
	// 记载完成后获取持有股票情况
	getHoldList();
}


function getToken() {
	var json = {
		"token": document.cookie,
	}
	return json;
}

function onRequest() {
	$.ajax({
		url: "/Sim-Stock/wallet/openState",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getToken()),
		dataType: "json",
		type: "post",
		success: function(data) {},
		error: function() {}
	})
}

function onBtn() {
	$.ajax({
		url: "/Sim-Stock/wallet/open",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getToken()),
		dataType: "json",
		type: "post",
		success: function(data) {},
		error: function() {}
	})
}

// 获取钱包信息
function getWallet() {
	$.ajax({
		url: "/Sim-Stock/wallet/getData",
		contentType: "application/json;charset=UTF-8",
		async: false,
		data: JSON.stringify(getToken()),
		dataType: "json",
		type: "post",
		success: function(res) {
			modifyWallet(res)

		},
		error: function() {}
	})
}

// 获取股票持有情况
function getHoldList() {
	$.ajax({
		url: "/Sim-Stock/hold/getHoldList",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(getToken()),
		dataType: "json",
		type: "post",
		success: function(res) {
			modifyHoldList(res)
		},
		error: function() {
		}
	});
}

