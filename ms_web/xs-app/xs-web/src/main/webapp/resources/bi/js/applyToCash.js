
//新增提现申请 银行卡信息
//修改提现申请 银行卡信息
function submitBankInfo(e){
	var bankAcctNo = $('#bankAcctNo').attr('value');
	var bankAcctNo2 = $('#bankAcctNo2').attr("value");
	var mobileValue = $('#mobile').attr("value");
	if($('#provinceSe').attr("value") == -1 || $('#citySe').attr("value") == -1){
		$('#bankAddressSpan').show();
		return;
	}else{
		$('#bankAddressSpan').hide();
	}

	// 银行名称
	if($('#bankName').attr("value") == -1){
		$('#bankNameSpan').show();
		return;
	}else{
		$('#bankNameSpan').hide();
	}

	// 分行名称
	if($('#bankNameBranch').attr("value") == ""){
		$('#branchSpan').show();
		return;
	}else{
		$('#branchSpan').hide();
	}
	
	//银行卡号
	if(bankAcctNo == ""){
		$('#bankAcctNoSpan').show();
		return;
	}else{
		$('#bankAcctNoSpan').hide();
	}
	if(bankAcctNo2 == "" || bankAcctNo != bankAcctNo2){
		$('#acctNoSpan').show();
		return;
	}else{
		$('#acctNoSpan').hide();
	}

	//开户姓名
	if($('#bankAcctName').attr("value") == ""){
		$('#bankAcctNameSpan').show();
		return;
	}else{
		$('#bankAcctNameSpan').hide();
	}
	
	//联系电话
	if(mobileValue == ""){
		$('#mobileSpan').show();
		return;
	}else{
		$('#mobileSpan').hide();
	}
	
	var canWithdrawalsAmount = $('#canWithdrawalsAmount').attr('value');
	
	var $setBankForm = $("#setBankForm"); 
	var params = $setBankForm.serialize();
	
	$.ajax({
		type : "POST",
		//提现申请银行卡设置或者修改
		url : "setBankAcctInfo?format=json", 
		data:params,
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						var offset = $(e).offset();
						xiuTips(objs.data,offset.left,offset.top-45,3);
						$('#submitBankBtn1').hide();
						$('#submitBankBtn2').show();
						setTimeout(function(){ //js延时3秒
							//提现申请银行卡设置成功后，自动跳转到提现申请界面
						    location.href = "http://localhost:8888/portal-web/virtualAccount/applyToCash?canWithdrawalsAmount=117.22";
						},3000);
					}else if(objs.scode == "-1"){
						alert(objs.data);
					}
				}
			}
		},
		error : function() {
			alert('连接异常！');
		}
	});
	    
	//为了不刷新页面,返回false.
    return false; 
}

//删除提现申请银行卡信息
function deleteBankAccount(bankAcctId){
	if(confirm("是否确定删除该账户银行记录?")){
		$.ajax({
			type : "POST",
			//删除提现申请银行卡信息
			url : "deleteBankAcctInfo/"+bankAcctId+"?format=json",
			dataType : "text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0"){
							alert("删除成功");
						}else if(objs.scode == "-1"){
							alert("删除失败");
						}
						
						//刷新页面
						window.location.reload();
					}
				}
			},
			error : function() {
				alert('连接异常！');
			}
		});
	}
}

//查询提现申请银行卡信息详情
function queryBankAccount(bankAcctId){
	$.ajax({
		type : "POST",
		//查询特定提现银行账号详情
		url : "findBankAcctDetailInfo/"+bankAcctId+"?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						var bankAcct = objs.data;
				    	
				    	// 省份城市联动
						//$("#provinceCode").attr("value",bankAcct.provinceCode);
					    //getCity();
					    //setTimeout(function(){ //js延时，等待getCity()返回信息
				    	//$("#cityCode").attr("value",bankAcct.cityCode);
					    //},200);
					    
						$("#provinceCode").attr("value","01");
						$("#cityCode").attr("value","0101");
						
		             	$('#bankAcctId').val(bankAcct.bankAcctId);
		             	$('#bankAcctNo').val(bankAcct.bankAcctNo);
		             	$('#bankAcctName').val(bankAcct.bankAcctName);
		             	$('#bankName').val(bankAcct.bankName);
		             	$('#mobile').val(bankAcct.mobile);
						$('#bankNameBranch').val(bankAcct.bankNameBranch);
					}else if(objs.scode == "-1"){
						alert("查询银行卡详情信息失败");
					}
				}
			}
		},
		error : function() {
			alert('连接异常！');
		}
	});
}

//提交提现申请
function submitApply(e){
	var bankNum = $("#bankInfo").find("tr").length - 2;
	var offset = $(e).offset();
	if(bankNum == 0){
		xiuTips("请添加银行账号！",offset.left,offset.top-45,2);
		return;
	}
	if($('#bankAcctInfo').attr("value") == ""){
		xiuTips("请选择银行！",offset.left,offset.top-45,2);
		return;
	}
	if($('#withdrawalsAmount').attr("value") == ""){
		xiuTips("请输入提现金额！",offset.left,offset.top-45,2);
		return;
	}

	if($('#xiu_channelId').val() == $('#channelId').val()){
		if($('#passWord').attr("value") == ""){
			xiuTips("请输入账户密码！",offset.left,offset.top-45,2);
			return;
		}
	}else{
		if(!checkMobilePhone(offset)){
			return;
		}
		
		var verificationCode = $("#verificationCode").val();
		if(verificationCode == "" || verificationCode == "请输入您手机上的验证码"){
			xiuTips("您没有输入验证码，请查看您的手机，输入正确的验证码！",offset.left,offset.top-65,2);
			return;
		}
	}

    $('#applyForm').ajaxSubmit({ 
	    success: function(data){ 
	    	var sObj = new String(data);
		    var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
		    var o = eval("(" + s + ")");
	    	if(o.resultCode == "0"){
				xiuTips("已成功提交提现申请！",offset.left,offset.top-45,3);
				$('#submitApplyBtn').hide();
				$('#submitApplyBtn2').show();
				setTimeout(function(){ //js延时3秒
			    	location.href = UrlPathUtil.getXiuCommandDir() + "VirtualAccountCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&queryParam=WithdrawalsRecord";
				},3000);
	    	}else if(o.resultCode == "1"){
				xiuTips("账户被冻结,不允许申请提现！",offset.left,offset.top-45,2);
	    		return;
	    	}else if(o.resultCode == "2"){
	    		$('#amountSpan').show();
	    		return;
	    	}else if(o.resultCode == "3"){
				xiuTips("密码输入错误！",offset.left,offset.top-45,2);
	    		return;
	    	}else if(o.resultCode == "4"){
				xiuTips("申请提现失败！",offset.left,offset.top-45,2);
	    		return;
	    	}else if(o.resultCode == "5"){
				xiuTips("您输入的验证码错误，请查看您的手机，输入正确的验证码！",offset.left,offset.top-65,2);
	    		return;
			}
	    }
	}); 
	//为了不刷新页面,返回false.
    return false; 
}

function setBankAccount(){
	//获取银行账号数量
	var bankNum = $("#bankInfo").find("tr").length - 2;
	if(bankNum >= 10){
		$('#bankSpan').show();
		$('#MASK_tix').hide()
		return;
	}else{
		$('#MASK_tix').show()
	}

	// 添加银行账户时重置form表单数据
	$('#setBankForm').each(function(){  
         this.reset();  
	});
}

function checkMoney(money){
	var amount = $('#canWithdrawalsAmount').attr('value');

	if(money == 0 || checkNum(money) == 0 || eval(money) > eval(amount)){
		$('#amountSpan').show();
		$('#submitApplyBtn').hide();
		$('#submitApplyBtn2').show();
	}else{
		$('#amountSpan').hide();
		$('#submitApplyBtn').show();
		$('#submitApplyBtn2').hide();
	}
}

function checkMobilePhone(offset){
	var mobile = $('#orderMobile').val();
	var reg= /^1[3458]{1}[0-9]{9}$/;
	if(mobile == "" || mobile == "请出示能下订单所留手机号"){
		xiuTips("您没有输入手机号码，请输入正确的手机号！",offset.left,offset.top-65,2);
		return false;
	}
	if(!reg.test(mobile)){
		xiuTips("您输入的手机号错误，请输入正确的手机号！",offset.left,offset.top-65,2);
		return false;
	}
	return true;
}

function sendVerificationCode(){
	if(!checkMobilePhone()){
		return;
	}
	 $.ajax({
   	      type: "POST",
   	      async:false,
          url: UrlPathUtil.getXiuCommandDir() + "sendSMSCmd?langId=${WCParam.langId}&storeId=${WCParam.storeId}&catalogId=${WCParam.catalogId}&timeStamp=" + new Date().getTime(),
          data: "phoneNum="+$("#orderMobile").attr("value")+"&smsType=4",
          success: function(responseData){
              	var sObj = new String(responseData);
	            var s = sObj.substring(sObj.indexOf("/*") + 2, sObj.lastIndexOf("*/"));
	            var o = eval("(" + s + ")");
	            if(o.errorMessage){
              	    alert("发生异常:" + o.errorMessage);
              	    return;
                }
				if(o.result && o.result == "overTimes"){
            		alert("同一手机号24小时内最多获取3次验证码");
            		return;
            	} 
                if(o.result && o.result == "success"){// 短信发送成功
                	alert("短信发送成功！");
                } 
	      }
      });
}