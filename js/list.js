var numPage = 3;
var curPageNumber;
var minPage;
var maxPage;
window.onload = function() {
	onRequest();
}

function onRequest() {
	$.ajax({
		url: "/Sim-Stock/stock/getList?curPageNumber=1&pageSize="+numPage,
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
	$('#simPage').append('第<input></input>页')
	bindRequest(num);
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
		console.log("上一页",curPageNumber)
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
		console.log("下一页",curPageNumber)
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


function addArray(data) {
	$('.simList').remove();
	var arr = data.data.page.data;
	$('#simListBg').append('<div class="simList"></div>')
	for (var i = 0; i < arr.length; i++) {
		$('.simList').append('<div class="simId">股票编号:' + arr[i].id + '</div>');
		$('.simList').append('<div class="simName">股票名称：' + arr[i].name + '</div>');
		$('.simList').append('<div class="simValue">股票市值：' + arr[i].marketValue + '</div>');
		$('.simList').append('<div class="simsimPrice">单股售价：' + arr[i].price + '</div>');
		$('.simList').append('<div class="simTotal">股票总数：' + arr[i].total + '</div>');
		$('.simList').append('<div class="simHold">公司持有数：' + arr[i].hold + '</div>');
		$('.simList').append('<div class="simAppear">上市时间：' + arr[i].appear + '</div>');
		$('.simList').append('<br>');
	}
}
