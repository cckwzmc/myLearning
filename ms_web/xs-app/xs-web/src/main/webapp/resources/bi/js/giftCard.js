// 礼品卡列表js
function filterSpanShow(obj) {
	if ($("#filterS").css("display") == 'none') {
		$("#filterS").show();
		$(obj).text("精简筛选条件");
	} else {
		$("#filterS").hide();
		$("#begTime").val('');
		$("#endTime").val('');
		$("#amountInp").val('');
		$(obj).text("更多筛选条件");
	}
}
function query(action, opType, resCode, totalMoney, amount) {
	$("#condition").attr("value", action);
	$("#opType").attr("value", opType);
	$("#resCode").attr("value", resCode);
	$("#totalMoney").attr("value", totalMoney);
	$("#amount").attr("value", amount);
	$("#mainForm").submit();
}

function showCardDiv() {
	var h = '220px';
	$.ajax({
		type : "POST",
		url : UrlPathUtil.getXiuCommandDir()
				+ "GiftCardOperationCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&opType=errorNum",
		success : function rollBackResult(responseData) {
			var sObj = new String(responseData);
			var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
			var o = eval("(" + s + ")");
			if (o.resultCode == "1") {
				$('#MASK_tix').show();
				$('#vCardNum').val('');
				$('#vCardPass').val('');
				$("#activeCardBtn2").hide();
				$("#activeCardBtn1").show();
			} else if (o.resultCode == "-110" || o.errorCode == "2510") {
				top.location.href = UrlPathUtil.getXiuPortalLoginUrl();
			} else {
				$('#MASK_tix').hide();
				$('#tipsImg').attr('src',UrlPathUtil.getXiuAssetsDir()+ 'static/img/bg_Error.png');
				$('#card_submit_msg').html(o.resultMsg);
				$('#card_submit').show();
				centerDiv($('#card_submit'), {
					top : h
				});
			}
		}
	});
}

function activeCard(obj) {
	var offset = $(obj).offset();
	var h = '220px';
	var vCardNum = $('#vCardNum').val();
	var vCardPass = $('#vCardPass').val();
	if (vCardNum == '') {
		var str = '请输入礼品卡号<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
		showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		return false;
	}
	if (vCardPass == '') {
		var str = '请输入礼品卡密码<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
		showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		return false;
	}

	$.ajax({
		type : "POST",
		url : UrlPathUtil.getXiuCommandDir()
				+ "GiftCardOperationCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&opType=activeCard&vCardNum="
				+ vCardNum + "&vCardPass=" + vCardPass,
		success : function rollBackResult(responseData) {
			var sObj = new String(responseData);
			var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
			var o = eval("(" + s + ")");
			if (o.resultCode == "1") {
				$('#MASK_tix').hide();
				$('#tipsImg').attr('src',UrlPathUtil.getXiuAssetsDir() + 'static/img/bg_ok.png');
				$('#card_submit_msg').html('您的礼品卡已经激活成功，请查看');
				$('#card_submit').show();
				centerDiv($('#card_submit'), {
					top : h
				});

			} else if (o.resultCode == "-3") {
				// 操作失败提示失败原因
				var str = o.resultMsg+ '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
				$("#activeCardBtn1").hide();
				$("#activeCardBtn2").show();
			} else if (o.resultCode == "-110" || o.errorCode == "2510") {
				top.location.href = UrlPathUtil.getXiuPortalLoginUrl();
			} else if (o.resultCode == "-2") {
				var str = "您的礼品卡已被冻结，请联系&nbsp;<a onclick=\"this.newWindow = window.open('http://chat.live800.com/live800/chatClient/chatbox.jsp?companyID=77366&configID=113113&jid=1560839906&enterurl=http%3A%2F%2Fwww%2Ezoshow%2Ecom%2F&timestamp=1222675300890', 'chatbox77366','toolbar=0,scrollbars=0,location=0,menubar=0,resizable=1,width=570,height=424');this.newWindow.focus();this.newWindow.opener=window;return false;\" href=\"javascript:void(0)\" target=\"_self\">走秀客服</a><br>&nbsp;&nbsp;&nbsp;<a href=\"javascript:hideTipDiv();\" >确定</a>";
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
			} else {
				// 操作失败提示失败原因
				var str = o.resultMsg+ '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
			}
		}
	});
}

function ValidateSpecialCharacter() {
	var code;
	if (document.all) { // 判断是否是IE浏览器
		code = window.event.keyCode;
	} else {
		code = arguments.callee.caller.arguments[0].which;
	}
	var character = String.fromCharCode(code);
	var txt = new RegExp("^[0-9a-zA-Z]+$");
	if (code != 8 && !txt.test(character)) {
		if (document.all) {
			window.event.returnValue = false;
		} else {
			arguments.callee.caller.arguments[0].preventDefault();
		}
	}
}

function checkNum(obj) {
	if (isNaN(obj.value)) {
		obj.value = "";
		alert("面值输入有误！");
	}
}

function checknum(obj) {
	obj.value = obj.value.replace(/[^\w\.\/]/ig, '');
}

// 礼品卡详情js
function showCardDiv() {
	var h = '220px';
	$.ajax({
		type : "POST",
		url : UrlPathUtil.getXiuCommandDir()
				+ "GiftCardOperationCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&opType=errorNum",
		success : function rollBackResult(responseData) {
			var sObj = new String(responseData);
			var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
			var o = eval("(" + s + ")");
			if (o.resultCode == "1") {
				$('#MASK_tix').show();
				$('#vCardNum').val('');
				$('#vCardPass').val('');
				$("#activeCardBtn2").hide();
				$("#activeCardBtn1").show();
			} else if (o.resultCode == "-110" || o.errorCode == "2510") {
				top.location.href = UrlPathUtil.getXiuPortalLoginUrl();
			} else {
				$('#MASK_tix').hide();
				$('#tipsImg').attr('src',UrlPathUtil.getXiuAssetsDir()+ 'static/img/bg_Error.png');
				$('#card_submit_msg').html(o.resultMsg);
				$('#card_submit').show();
				centerDiv($('#card_submit'), {
					top : h
				});
			}
		}
	});
}

function activeCard(obj) {
	var offset = $(obj).offset();
	var h = '220px';
	var vCardNum = $('#vCardNum').val();
	var vCardPass = $('#vCardPass').val();
	if (vCardNum == '') {
		var str = '请输入礼品卡号<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
		showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		return false;
	}
	if (vCardPass == '') {
		var str = '请输入礼品卡密码<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
		showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		return false;
	}

	$.ajax({
		type : "POST",
		url : UrlPathUtil.getXiuCommandDir()
				+ "GiftCardOperationCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&opType=activeCard&vCardNum="
				+ vCardNum + "&vCardPass=" + vCardPass,
		success : function rollBackResult(responseData) {
			var sObj = new String(responseData);
			var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
			var o = eval("(" + s + ")");
			if (o.resultCode == "1") {
				$('#MASK_tix').hide();
				$('#tipsImg').attr('src',UrlPathUtil.getXiuAssetsDir() + 'static/img/bg_ok.png');
				$('#card_submit_msg').html('您的礼品卡已经激活成功，请查看');
				$('#card_submit').show();
				centerDiv($('#card_submit'), {
					top : h
				});

			} else if (o.resultCode == "-3") {
				// 操作失败提示失败原因
				var str = o.resultMsg+ '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
				$("#activeCardBtn1").hide();
				$("#activeCardBtn2").show();
			} else if (o.resultCode == "-110" || o.errorCode == "2510") {
				top.location.href = UrlPathUtil.getXiuPortalLoginUrl();
			} else if (o.resultCode == "-2") {
				var str = "您的礼品卡已被冻结，请联系&nbsp;<a onclick=\"this.newWindow = window.open('http://chat.live800.com/live800/chatClient/chatbox.jsp?companyID=77366&configID=113113&jid=1560839906&enterurl=http%3A%2F%2Fwww%2Ezoshow%2Ecom%2F&timestamp=1222675300890', 'chatbox77366','toolbar=0,scrollbars=0,location=0,menubar=0,resizable=1,width=570,height=424');this.newWindow.focus();this.newWindow.opener=window;return false;\" href=\"javascript:void(0)\" target=\"_self\">走秀客服</a><br>&nbsp;&nbsp;&nbsp;<a href=\"javascript:hideTipDiv();\" >确定</a>";
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
			} else {
				// 操作失败提示失败原因
				var str = o.resultMsg+ '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
				showTipDiv(str, offset.left - 10, offset.top - 60, 5);
			}
		}
	});
}

function ValidateSpecialCharacter() {
	var code;
	if (document.all) { // 判断是否是IE浏览器
		code = window.event.keyCode;
	} else {
		code = arguments.callee.caller.arguments[0].which;
	}
	var character = String.fromCharCode(code);
	var txt = new RegExp("^[0-9a-zA-Z]+$");
	if (code != 8 && !txt.test(character)) {
		if (document.all) {
			window.event.returnValue = false;
		} else {
			arguments.callee.caller.arguments[0].preventDefault();
		}
	}
}

function checknum(obj) {
	obj.value = obj.value.replace(/[^\w\.\/]/ig, '');
}