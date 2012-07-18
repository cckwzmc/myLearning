//XJS.execScript("${xiuAssetsDir}static/js/library/jquery_form.js");
/*+++++++++++++++++
+ 页面加载结束后绑定事件
++++++++++++++++++*/
$(document).ready(function(){	
	bingMobile();
	$("#sendMobileRand").bind('click', sendVerificationCode);
	$("#bingMobile").bind('click', function(){$('#bandMobieDiv').show(); });
	$("#bindBtn").bind('click', bindMobile);
	$("#cmfBtn").bind('click', subMessage);
	$("#cancleBtn").bind('click', cancelSubMessage);
})

/*+++++++++++++++++
+ 控制绑定手机号DIV层的显示
++++++++++++++++++*/
function bingMobile(){
	if($("#bindMobile").attr("value") == ""){
	    $('#mobileDiv').hide();
	    $('#bandMobieDiv').show();
	}else{
	    $('#mobileDiv').show();
	    $('#bandMobieDiv').hide();
	}
}

function reLoadPage(){
	setTimeout(function(){ 
		document.location.reload();
	},3000);
}

function subMessage(){
	var offset = $("#cmfBtn").offset();
    $.ajax({
            type: "POST",
            url: UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/msgSubscription/subOrCancle/1?format=json",
            dataType : "text",
            success: function(data, textStatus) {
        	    if (isNaN(data)) {
        	    	var objs =  $.parseJSON(data);
        	    	if (objs != null && objs.scode == "0") {
        	    		if(objs.data == true){
        	    			xiuTips("订阅成功！", offset.left, offset.top-45, 3);
        	    			reLoadPage();
        	    		}else{
        	    			xiuTips(objs.smsg, offset.left, offset.top-45, 3);
        	    		}
        	    	}
        	    }
            },
            error: function(){xiuTips("系统繁忙，请稍后再试吧！", offset.left, offset.top-45, 3)},//alert("系统繁忙，请稍后再试吧！");
       });
}

function cancelSubMessage(){
	var offset = $("#cancleBtn").offset();
	var str = '确定要取消订阅消息？<br>&nbsp;&nbsp;&nbsp;<a href="javascript:cmfCancleSubMessage();hideTipDiv();" >确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv()" >取消</a>';
	showTipDiv(str,offset.left,offset.top-45,3);
}

function cmfCancleSubMessage(){
	var offset = $("#cancleBtn").offset();
    $.ajax({
            type: "POST",
            url: UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/msgSubscription/subOrCancle/0?format=json",
            dataType : "text",
            success: function(data, textStatus) {
          	  if (isNaN(data)) {
        		  var objs =  $.parseJSON(data);
        		  if (objs != null && objs.scode == "0") {
        			  if(objs.data == true){
        				  xiuTips("取消订阅成功！",offset.left,offset.top-45,3);
        				  reLoadPage();
        			  }else{
        				  xiuTips(objs.smsg, offset.left, offset.top-45, 3);
        			  }
        		  }
        	  }
          },
          error: function(){xiuTips("系统繁忙，请稍后再试吧！", offset.left, offset.top-45, 3)},
    });
}

function bindMobile(){
    var verificationCode = $("#verificationCode").attr("value");
    var offset = $("#verificationCode").offset();
    if(!checkMobile()){
        return;
    }
    if(verificationCode == ""){
        xiuTips("请输入手机验证码！", offset.left, offset.top-45, 3);
        return;
    }
    offset = $("#bindBtn").offset();
    $.ajax({
        type: "POST",
        async:false,
        url: UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/msgSubscription/bindMobileNum/"+$("#mobile").val()+"/"+verificationCode+"/?format=json",
        dataType : "text",
        success : function(data, textStatus) {
      	  if (isNaN(data)) {
      		  var objs =  $.parseJSON(data);
      		  if (objs != null) {
      			  if(objs.data == true && objs.scode == "0"){
      				  xiuTips("恭喜您，绑定成功！", offset.left, offset.top-45, 3);
      				  reLoadPage();
      			  }else{
      				  xiuTips(objs.smsg, offset.left, offset.top-45, 4);
      			  }
      		  }
      	  }
        },
        error: function(){xiuTips("系统繁忙，请稍后再试吧！", offset.left, offset.top-45, 3)}
    });
 
}

function checkMobile(){
    var mobile = $("#mobile").attr("value");
    var reg= /^1[3458]{1}[0-9]{9}$/;
    if(mobile == "" || !reg.test(mobile)){
        //alert("请输入有效的手机号码！");
    	var offset = $("#mobile").offset();
        xiuTips("请输入有效的手机号码！", offset.left, offset.top-45, 3);
        $("#mobile").focus();
        return false;
    }
    if($("#bindMobile").attr("value") == mobile){
        alert("该手机号码已经绑定，请输入其他手机号码！");
        return false;
    }
    return true;
}

/*+++++++++++++++++
+ 发送短信
++++++++++++++++++*/
function sendVerificationCode(){
    if(!checkMobile()){
        return;
    }
    var offset = $("#send").offset();
    $.ajax({
          type: "POST",
          async:false,
          url: UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/smsmgr/getChkCode/"+$("#mobile").val()+"/3/?format=json",
          dataType : "text",
          success : function(data, textStatus) {
        	  if (isNaN(data)) {
        		  var objs =  $.parseJSON(data);
        		  if (objs != null) {
        			  if(objs.data == true  && objs.scode == "0"){
        				  xiuTips("短信已发送，请注意接收！", offset.left, offset.top-45, 3);
        			  }else{
        				  xiuTips(objs.smsg, offset.left, offset.top-60, 4);
        			  }
        		  }
        	  }
          },
          error: function(){xiuTips("系统繁忙，请稍后再试吧！", offset.left, offset.top-45, 3)}
      });
}