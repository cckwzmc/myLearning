$(document).ready(function(){
	var $form = $("#sendFeedbackForm");  
	$($form).validate({   
		event:"submit",
		errorElement:"span",
			rules:{   
				feedback_title:{required:true,rangelength:[0,80]},
				feedback_content:{required:true,rangelength:[0,500]}
			},            
			messages: {
				feedback_title:{required:'请输入主题！', rangelength:'主題最大长度为80字符，请正确输入！'},
				feedback_content:{required:'请输入內容！', rangelength:'内容最大长度为500字符，请正确输入！'},
			},
			submitHandler: function(){
				var params = $form.serialize();
				var offset = $("#sendFeedback").offset();
				var feedbackType=$("#feedback_type").val();
				$.ajax({
					type : "POST",
					url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/feedback/create?format=json",
					data: params,
					dataType : "text",
					success : function(data, textStatus) {
						if (isNaN(data)) {              
							var objs =  $.parseJSON(data);
							if (objs != null) {
								if(objs.scode == "0")
								{
									if(objs.data){
										xiuTips("用户反馈信息发送成功！",offset.left,offset.top-50,3);
										$('#sendFeedback').hide();
										$('#sendFeedback2').show();
										setTimeout(function(){ 
								    		location.href = UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/feedback/list?feedbackType="+feedbackType;
										},3000);
					            	}
								}else{
									xiuTips("用户反馈回复信息发送失败！",offset.left,offset.top-50,3);
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
    $("#sendFeedback").click(function(){
        $("#sendFeedbackForm").submit();
    });
   
});

$("#sendReply").click(function(){
	var offset = $("#sendReply").offset();
	var feedbackId=$("#feedbackId").val();
	var title=$("#title").val();
	var content=$("#content").val();
	var feedbackType=$("#feedbackType").val();
	if(content == ""){
		xiuTips("内容必须填写！",offset.left,offset.top-50,2);
		$("#content").focus();
		return false;
	}
	if(content.length > 500){
		xiuTips("内容最大长度为500字，请正确输入！",offset.left,offset.top-50,2);
		$("#content").focus();
		return false;
	}
	jQuery.ajax({
		type : "POST",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/feedback/createReply/" + feedbackId + "/" + content + "?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0")
					{
						if(objs.data){
							xiuTips("用户反馈回复信息发送成功！",offset.left,offset.top-50,3);
							$('#sendReply').hide();
							$('#sendReply2').show();
							setTimeout(function(){ 
					    		location.href = UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/feedback/replyList?feedbackId="+feedbackId+"&title="+title+"&feedbackType="+feedbackType;
							},3000);
		            	}
					}else{
						xiuTips("用户反馈回复信息发送失败！",offset.left,offset.top-50,3);
	            	}
				}
			}
		},
		error : function() {
		}
	});
	return false;  
});