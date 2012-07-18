

//我要充值 弹出充值卡充值输入框
function applyToVCdivShow() {
	var h = '220px';
	$.ajax({
		type : "POST",
		//检查 我要充值 按钮是否被禁用
		url : "checkResourcePwdErrorCountValidate?format=json", 
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						$('#VCardNum').val('');
						$('#VCardPass').val('');
						//$("#checkCodeImg").attr("src", UrlPathUtil.getXiuCommandDir() + 'kaptcha.jpg?random='+(new Date().getTime()));
						$('#applyToVCdiv').show();
						centerDiv($('#applyToVCdiv'),{relative:false,position:'fixed',top:h});
					}else if(objs.scode == "-1"){
						$('#applyToVCdiv').hide();
						$('#tipsImg').attr('src', UrlPathUtil.getXiuAssetsDir() + 'static/img/bg_Error.png');
						$('#card_submit_msg').html(objs.data);
						$('#card_submit').show();
						centerDiv($('#card_submit'),{top:h});
					}
				}
			}
		},
		error : function() {
			alert('连接异常！');
		}
	});
}

//充值卡充值 确定按钮
function chargeVCardNum(obj) {
	var offset = $(obj).offset();
	var h = '220px';
	var vCardNum = $('#VCardNum').val();
	var vCardPass = $('#VCardPass').val();
	// var checkCode = $('#checkCode').val();
	
	if (vCardNum == '') {
		var str = '请输入充值卡号<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
        //showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		xiuTips(str,offset.left,offset.top-45,3);
		return false;
	}
	if (vCardPass == '') {
		var str = '请输入充值卡密码<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
        //showTipDiv(str, offset.left - 10, offset.top - 60, 5);
		xiuTips(str,offset.left,offset.top-45,3);
		return false;
	}
	
	$.ajax({
		type : "POST",
		//激活充值卡 同时检查充值卡是否被冻结 过期 是否被使用过
		url : "checkRechargeIsCanUse?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						$.ajax({
							type : "POST",
							//通过绑定的充值卡给当前用户的虚拟账户进行充值
							url : "addVirtualAccountMoney?format=json",
							dataType : "text",
							success : function(data, textStatus) {
								if (isNaN(data)) {
									var objs2 =  $.parseJSON(data);
									if (objs2 != null) {
										if(objs2.scode == "0"){
											$('#applyToVCdiv').hide();
											$('#tipsImg').attr('src', UrlPathUtil.getXiuAssetsDir() + 'static/img/bg_ok.png');
								 			$('#card_submit_msg').html('已经充值到你的虚拟账户不可提现余额中，请查看你的虚拟账户');
								 			$('#card_submit').show();
								 			centerDiv($('#card_submit'),{top:h});
										}else{
											$('#applyToVCdiv').hide();
											$('#tipsImg').attr('src', UrlPathUtil.getXiuAssetsDir() + 'static/img/bg_Error.png');
								 			$('#card_submit_msg').html('出错啦！'+objs2.date);
								 			$('#card_submit').show();
								 			centerDiv($('#card_submit'),{top:h});
										}
									}
								}
							},
							error : function() {
								alert('连接异常！');
							}
						});
					}else{
						//操作失败提示失败原因
						var str = objs.date + '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>';
						xiuTips(str,offset.left,offset.top-45,3);
						$("#submitBankBtn1").hide();
						$("#submitBankBtn2").show();
					}
				}
			}
		},
		error : function() {
			alert('连接异常！');
		}
	});
	
}


//检查虚拟账户是否可以申请提现
//满足可提现金额足够 同时用户账户正常（未冻结）才进行提现申请操作
function checkDrawMoney(e) {
	
	var offset = $(e).offset();
	var canWithdrawalsAmount = $('#canWithdrawalsAmount').val();
	
	//判断是否有足够的可提现金额
	if (canWithdrawalsAmount <= 0) {
		xiuTips("您没有可提现的金额！", offset.left, offset.top - 45, 5);
		return;
	}
	
	$.ajax({
		type : "POST",
		//异步获取当前用户账户状态 判断当前用户账户是否被冻结
		url : "checkIsFreezeUserAcct?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						//当前用户账户正常
						if (objs.data == "01") {
							location.href = 'http://localhost:8888/portal-web/virtualAccount/getBankAcctListInfo?canWithdrawalsAmount='+ canWithdrawalsAmount;
						} else {
							//当前用户账户被冻结
							xiuTips("您的虚拟账户已被冻结，不允许申请提现！", offset.left,offset.top - 65, 5);
							return;
						}
					}
				}
			}
		},
		
		error : function() {
			alert('连接异常！');
		}
	});
	
}


function changeCheckCode() {
	$("#checkCodeImg").attr("src",UrlPathUtil.getXiuCommandDir() + 'kaptcha.jpg?random='+ (new Date().getTime()));
}

function refresh(tabName, rechargeId) {
	// 刷新页面
	$('#pageNo').val(1);
	$('#condition').val(1);
	$('#queryRecord').val(tabName);
	$('#rechargeId').val(rechargeId);
	$('#mainForm').submit();
}

function cancelWithdrawals(drawId) {
	if (confirm("您确定要取消流水号为：" + drawId + "的提现申请吗?")) {
		$.ajax({
			type : "POST",
			url : UrlPathUtil.getXiuCommandDir()
					+ "CancelWithdrawalsCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&drawId="
					+ drawId + "&opType=cancel",
			success : function rollBackResult(responseData) {
				var sObj = new String(responseData);
				var s = sObj.substring(sObj.indexOf("/*") + 2, sObj
						.lastIndexOf("*/"));
				var o = eval("(" + s + ")");
				if (o.billStatus != 0) {
					alert("流水号为" + drawId + "的提现申请记录状态为：" + o.billStatusDesc
							+ "，不能再取消申请提现。");
				} else {
					if (o.resultCode == "1") {
						alert("提现申请取消成功！");
					} else {
						alert("提现申请取消失败！");
					}
				}
				// 刷新页面
				window.location.reload();
			}
		});
	}
}

//确定按钮 回车事件处理
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

//充值卡输入框 输入内容校验
function checknum(obj) {
	obj.value = obj.value.replace(/[^\w\.\/]/ig, '');
}
