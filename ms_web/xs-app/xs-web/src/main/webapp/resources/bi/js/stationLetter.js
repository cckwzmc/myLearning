$(document).ready(function(){
	var $form = $("#sendLetterForm");  
	$($form).validate({   
		event:"submit",
		errorElement:"span",
			rules:{   
				title:{required:true,rangelength:[0,80]},
				content:{required:true,rangelength:[0,500]}
			},   
			messages: {
				title:{required:'请输入主题！', rangelength:'主題最大长度为80字符，请正确输入！'},
				content:{required:'请输入內容！', rangelength:'内容最大长度为500字符，请正确输入！'},
			},
			submitHandler: function(){
				var params = $form.serialize();
				var offset = $("#sendLetter").offset();
				
				$.ajax({
					type : "POST",
					url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/stationLetter/create?format=json",
					data: params,
					dataType : "text",
					success : function(data, textStatus) {
						if (isNaN(data)) {              
							var objs =  $.parseJSON(data);
							if (objs != null) {
								if(objs.scode == "0")
								{
									if(objs.data == '1'){
										xiuTips("站内信息发送成功！",offset.left,offset.top-50,3);
										$('#sendLetter').hide();
										$('#sendLetter2').show();
										setTimeout(function(){ 
								    		location.href = UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/stationLetter/list?queryRecord=all";
										},3000);
					            	}
								}else{
									xiuTips("站内信息发送失败！",offset.left,offset.top-50,3);
				            	}
							}
						}
					},
					error : function() {
					}
				});
		  }
	});
});

$(document).ready(function(){
    //提交表单
    $("#sendLetter").click(function(){
        $("#sendLetterForm").submit();
    });
});

function findLetters(lettersId){
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/stationLetter/show/" + lettersId + "?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0")
					{
			    		$("#"+lettersId+"_Tr").show();
				        $("#"+lettersId+"_Td").html(objs.data);
					}
				}
			}
		},
		error : function() {
		}
	});
}

function deleteLetters(lettersId, obj){
	if(confirm("确定删除该短消息?")){
		var offset = $(obj).offset();
		jQuery.ajax({
			type : "POST",
			url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/stationLetter/destory/" + lettersId + "?format=json",
			dataType : "text",
			success : function(data, textStatus) {
				if (isNaN(data)) {
					var objs =  $.parseJSON(data);
					if (objs != null) {
						if(objs.scode == "0")
						{
							if(objs.data == '1'){
				    			xiuTips("站内信删除成功！",offset.left,offset.top-45,3);
			            	}else{
			            		xiuTips("站内信删除失败！",offset.left,offset.top-45,3);
			            	}
							setTimeout(function(){
								//刷新页面
								window.location.reload();
							},3000);
						}else{
		            		xiuTips("站内信删除失败！",offset.left,offset.top-45,3);
		            	}
					}
				}
			},
			error : function() {
			}
		});
	}
}
