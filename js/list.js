var numPage = 5;
var curPageNumber;
var minPage;
var maxPage;
window.onload = function() {
	onRequest();
}



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

function onRequest() {
	$.ajax({
		url: "/Sim-Stock/stock/getList?curPageNumber=1&pageSize=" + numPage,
		contentType: "application/json;charset=UTF-8",
		type: "get",
		success: function(data) {
			countNum(data)
			addArray(data)
			curPageNumber = data.data.page.curPageNumber;
		},
		error: function() {}
	});

}

function countNum(num) {
	maxPage = num.data.page.maxPageNumber;
	minPage = num.data.page.minPageNumber;
	while (maxPage >= minPage) {
		$('#simPage').append('<a>' + minPage + '</a>')
		minPage++;
	}
	$('#simPage').prepend('<span>上一页</span>')
	$('#simPage').append('<span>下一页</span>')
	$('#simPage').append('第<input onkeyup="return ValidateNumber($(this),value)"></input>页')
	$('#simPage').append('<p onclick="jumpInput()">跳转</p>')
	bindRequest(num);
}

function jumpInput() {
	var jmp = $('#simPage input').val();
	if (jmp.length > 0) {
		jumpRequest(jmp);
	} else {
	}
}

function ValidateNumber(e, pnumber) {
	if (!/^\d+$/.test(pnumber)) {
		$(e).val(/^\d+/.exec($(e).val()));
	} else if (pnumber >= maxPage) {
		$(e).val(maxPage);
	}
	return false;
}

function jumpRequest(req) {
	$.ajax({
		url: "/Sim-Stock/stock/getList?curPageNumber=" + req + "&pageSize=" + numPage,
		contentType: "application/json;charset=UTF-8",
		type: "get",
		success: function(data) {
			curPageNumber = data.data.page.curPageNumber;
			addArray(data)
		},
		error: function() {}
	})
	$('#simPage input').val("");
}

function bindRequest(num) {
	minPage = num.data.page.minPageNumber;
	maxpage = num.data.page.maxPageNumber;
	$('#simPage a').click(function() {
		$.ajax({
			url: "/Sim-Stock/stock/getList?curPageNumber=" + $(this).text() + "&pageSize=" + numPage,
			contentType: "application/json;charset=UTF-8",
			type: "get",
			success: function(data) {
				curPageNumber = data.data.page.curPageNumber;
				addArray(data)
			},
			error: function() {}
		})
	})
	$('#simPage span').click(function() {
		console.log("上一页", curPageNumber)
		if (parseInt(curPageNumber) > minPage) {
			var upPage = parseInt(curPageNumber - 1)
			console.log(upPage)
		} else {
			var upPage = minPage
			return;
		}
		var pageButton = $(this).text();
		if (pageButton == "上一页") {
			$.ajax({
				url: "/Sim-Stock/stock/getList?curPageNumber=" + upPage + "&pageSize=" + numPage,
				contentType: "application/json;charset=UTF-8",
				type: "get",
				success: function(data) {
					addArray(data)
				},
				error: function() {}
			})
			curPageNumber = curPageNumber - 1;
		}
	})
	$('#simPage span').click(function() {
		console.log("下一页", curPageNumber)
		if (parseInt(curPageNumber) < maxPage) {
			var downPage = parseInt(curPageNumber + 1)
			console.log(downPage)
		} else {
			var downPage = maxPage
			return;
		}
		var pageButton = $(this).text();
		if (pageButton == "下一页") {
			$.ajax({
				url: "/Sim-Stock/stock/getList?curPageNumber=" + downPage + "&pageSize=" + numPage,
				contentType: "application/json;charset=UTF-8",
				type: "get",
				success: function(data) {
					addArray(data)
				},
				error: function() {}
			})
			curPageNumber = curPageNumber + 1;
		}
	})
}

function getSim(data) {
	console.log(data);
	localStorage.removeItem('simId');
	localStorage.setItem('simId', data);
	window.location.href = "sim.html";
}

function addArray(data) {
	$('#simList').remove()
	var arr = data.data.page.data;
	$('#simListBg').append('<div id="simList"></div>')
	for (var i = 0; i < arr.length; i++) {
		$('#simList').append('<div class="simPublic" id="' + (i + 1) + '" onclick = "getSim(' + arr[i].id + ')"><span>股票编号：' +
			arr[i].id + '</span> <br> <span>股票名字：' + arr[i].name +
			'</span> <br> <span>股票价值：' + parseInt(arr[i].marketValue).toFixed(2)  + '</span> <br> <span>股票单价：' + parseInt(arr[i].price).toFixed(2)+
			'</span> <br> <span>股票总数：' + arr[i].total + '</span> <br> <span>持有数量：' + arr[i].hold + '</span> <br> <span>股票售出：' + arr[i].sold + '</span></div>')
	}
}
