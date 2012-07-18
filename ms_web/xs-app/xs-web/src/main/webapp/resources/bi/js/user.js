$(document).ready(function(){
	  
	// "修改密码"表单验证
	$('#modifyPasswordForm').validate({
		event : "submit",
		rules : {
			oldPassword : {
				required : true,
				byteRangeLength : [ 6, 16 ]
			},
			newPassword : {
				required : true,
				byteRangeLength : [ 6, 16 ]
			},
			userpassword2 : {
				required : true,
				equalTo : "#newPassword"
			}
		},
		messages : {
			oldPassword : {
				required : '请设置旧密码！',
				byteRangeLength : '旧密码范围在6~16位之间，可由字母，数字和符号组成！'
			},
			newPassword : {
				required : '请设置新密码！',
				byteRangeLength : '新密码范围在6~16位之间，可由字母，数字和符号组成！'
			},
			userpassword2 : {
				required : '请再输入一次密码！',
				equalTo : '您两次输入的账号密码不一致！'
			}
		},
		submitHandler : modifyPasswordFormSubmit,
		errorPlacement: function(error, element) {
			var em = element.parent("td").find('.cl');
			if(em.length) em.hide();
			error.appendTo( element.parent("td"));
		}
	});
  

		
	// "个人信息"验证表单
	$('#modifyPersonInfoForm').validate({
		event : "submit",
		rules : {
			email : {
				required : true,
				maxlength : 100,
				email : true
			},
			custName : {
				required : true,
				byteRangeLength : [ 2, 20 ]
			},
			petName : {
				byteRangeLength : [ 2, 20 ]
			},
			mobile:{required:true, mobile:true},
			interest : {
				maxlength : 500
			}
		},
		messages : {
			email : {
				required : '请输入邮箱！',
				maxlength : 'Email长度不能大于100！',
				email : '邮箱格式不正确！'
			},
			custName : {
				required : '请输入您的姓名！',
				byteRangeLength : '姓名只可输入2－20个字节！(注：一个汉字两个字节)'
			},
			petName : {
				byteRangeLength : '昵称只可输入2－20个字节！(注：一个汉字两个字节)'
			},
			mobile:{required:'请输入您的手机号码！', mobile:'手机格式错误！'},
			interest : {
				maxlength : '兴趣爱好栏不要超出500个字节！'
			}
		},
		submitHandler : modifyPersonInfoFormSubmit,
		errorPlacement: function(error, element) {
			var em = element.parent("td").find('.cl');
			if(em.length) em.hide();
			error.appendTo( element.parent("td"));
		}
	});
  
  

});


/**
 * ============================="修改密码"功能===========================================
 */

//"修改密码" 表单submit
function modifyPasswordFormSubmit(){
	  var $form = $("#modifyPasswordForm");
	  var offset = $("#modifyPasswordSubmitBu").offset();
		var params = $form.serialize();
		$.ajax({
			type : "POST",
			url : "modifyPassword?format=json",
			data: params,
			dataType : "text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0"){
							showTipDiv('<font color=#F26521>修改成功!</font>',offset.left-10,offset.top-50,2);
							$form[0].reset();
						}else if(objs.scode == "-1"){
							showTipDiv('<font color=#F26521>修改失败，返回信息：'+objs.data+'</font><br>&nbsp;&nbsp;&nbsp;<a href="javascript:hideTipDiv();" >确定</a>',offset.left-10,offset.top-90,5);
						}
					}
				}
			},
			error : function() {
				alert('连接异常！');
			},
			complete : function() {
				$form.data("send",false);
			}
		});
}









/**
 * ============================="个人信息"功能===========================================
 */
//"个人信息" 表单submit
function modifyPersonInfoFormSubmit(){
	  var $form = $("#modifyPersonInfoForm");
	  var offset = $("#modifyPersonInfoSubmitBu").offset();

		if($("#workTypeStyle").val()=="T"){
			$("input:radio[name='workType']:checked").val($("#workTypeOther").val());
		}
		$.xiupop( {
		    title : '',
	        content : "正在提交，请稍侯......",   
	        center: true,
	        hasBg:true,
	        time:0
	        }		    
	    );
		var params = $form.serialize();
		jQuery.ajax({
			type : "POST",
			url : "modifyPersonInfo?format=json",
			data : params,
			dataType :"text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0"){
							showTipDiv('<font color=#F26521>修改成功!</font>',offset.left-10,offset.top-50,2);							
							setTimeout(function(){
								top.location.href = UrlPathUtil.getXiuPortalMyHomeUrl();
						    },2000);
						}else if(objs.scode == "-1"){
							$.closepop(); 
							showTipDiv('<font color=#F26521>修改失败，错误信息：'+objs.data+'</font>',offset.left-10,offset.top-50,2);
						}
					}
				}
			},
			error : function() {
				$.closepop();
				alert('连接异常！');
			},
			complete : function() {
				$.closepop();//关闭弹出层
			}
		});
}


//获取省份select
function getProvince(province_id) {
	cleanSelect(province_id);	
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getProvinceList?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0"){
						for ( var i = 0; i < objs.data.length; i++) {
							jQuery("#"+province_id).append(
								"<option value='" + objs.data[i].paramCode + "'>" + objs.data[i].paramDesc + "</option>"
							);
						}
					}
				}
			}
		},
		error : function() {
			alert("error");
		}
	});
}

//通过省获取市select
function getRegion(provinceCode, region_id) {
	cleanSelect(region_id);
	if("-1"!=provinceCode){
		jQuery.ajax({
			type : "GET",
			url :UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getListByParamTypeAndParentCode.do?format=json&parentCode="+provinceCode,
			dataType : "text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0"){
							for ( var i = 0; i < objs.data.length; i++) {
								jQuery("#"+region_id).append(
									"<option value='" + objs.data[i].paramCode + "'>" + objs.data[i].paramDesc + "</option>"
								);
							}
						}
					}
				}
			},
			error : function() {
				alert("getRegion error");
			}
		});
	}
}

//通过市获取区县select
function getCity(regionCode, city_id) {
	cleanSelect(city_id);
	if("-1"!=regionCode){
		jQuery.ajax({
			type : "GET",
			url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getListByParamTypeAndParentCodeForArea.do?format=json&cityCode="+regionCode,
			dataType : "text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0"){
							for ( var i = 0; i < objs.data.length; i++) {
								jQuery("#"+city_id).append(
									"<option value='" + objs.data[i].paramCode + "'>" + objs.data[i].paramDesc + "</option>"
								);
							}
						}
					}
				}
			},
			error : function() {
				alert("getCity error");
			}
		});
	}
}

//通过select的id清空select
function cleanSelect(selectId) {
	jQuery("#"+selectId).empty();
	jQuery("#"+selectId).append("<option value='-1'>请选择</option>");	
}

