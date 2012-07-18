$(document).ready(function(){
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH + "/feedback/show?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0")
					{
			    		alert(objs.data)
					}
				}
			}
		},
		error : function() {
		}
	});
});