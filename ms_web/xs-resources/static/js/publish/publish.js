var publish={};
publish.publish_index=function(obj){
	$(obj).attr("disabled",true);
	$(obj).attr("value","正在出版，请稍后...");
	var url=sysGlobal.constants.SYS_CONTEXTPATH+"/sys/publish/publish_index?format=json";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"JSON",
		success:function(data){
			if(data.success){
				$(obj).attr("disabled",false);
				$(obj).attr("value","出版成功");
			}
		},
		error:function(data){
			
		},
		complete:function(){
			$(obj).attr("disabled",false);
			$(obj).attr("value","出版首页");
		}
	})
}
publish.publish_public=function(obj){
	$(obj).attr("disabled",true);
	var btnValue=$(obj).attr("value");
	$(obj).attr("value","正在出版，请稍后...");
	var url=sysGlobal.constants.SYS_CONTEXTPATH+"/sys/publish/publish_public?format=json";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"JSON",
		data:{"templateName":$(obj).attr("templateName"),"groupName":$(obj).attr("groupName")},
		success:function(data){
			if(data.success){
				$(obj).attr("disabled",false);
				$(obj).attr("value","出版成功");
			}
		},
		error:function(data){
			
		},
		complete:function(){
			$(obj).attr("disabled",false);
			$(obj).attr("value",btnValue);
		}
	})
}