var Iarea = {}
/**
 * area setup
 */
var areaUrl="http://d.istyle.com/istyle-web";
Iarea.setup = function(areaId) {
	Iarea.areaId=areaId;
	$.ajax({
		type : "GET",
		url :areaUrl+"/area/topArea?format=json&callback?",
		dataType:"jsonp",
		jsonp:"callback",
		cache:true,
		success : function(data) {
			if(!!data&&!!data.data){
				Iarea.loadTopArea(data.data);
			}
		},
		error : function(data) {
		}
	})
}

Iarea.loadTopArea=function(areaData){
	var html='';
	html+='<select id="topArea" name="topArea" level="1">'+
 		'<option value="-1">请选择</option>';
	for(var i=0;i<areaData.length;i++){
		html+='<option value="'+areaData[i].id+'">'+areaData[i].name+'</option>';
	}
	html+='</select>';
	$("#"+Iarea.areaId).html(html);
	$("#topArea").bind("change",function(){
		var v=$("#topArea").val();
		if(v=='-1'){
			alert("请选择");
			return false;
		}
		Iarea.selectArea(this);
	});
}
Iarea.loadChangeArea=function(areaData,thisObj){
	var v=$(thisObj).val();
	if(v=='-1'){
		alert('请选择')
		return false;
	}
	var l=$(thisObj).attr("level");
	var level=parseInt(l);
	Iarea.clearLevel(level);
	var html='';
	html+='<select id="area_'+(level+1)+'" name="area_'+(level+1)+'" level="'+(level+1)+'">'+
 		'<option value="-1">请选择</option>';
	for(var i=0;i<areaData.length;i++){
		html+='<option value="'+areaData[i].id+'">'+areaData[i].name+'</option>';
	}
	html+='</select>';
	$(html).insertAfter(thisObj);
	$("#area_"+(level+1)).bind("change",function(){
		var v=$("#area_"+(level+1)).val();
		if(v=='-1'){
			alert("请选择");
			return false;
		}
		Iarea.selectArea(this);
	});
}
/**
 * 清楚leve>params+1的select
 */
Iarea.clearLevel=function(level){
	var select=$("#"+Iarea.areaId).find("select");
	if(select.length>1){
		for(var i=0;i<select.length;i++){
			var s=parseInt($(select[i]).attr("level"));
			if(s>=(level+1)){
				$(select[i]).remove();
			}
		}
	}
}
Iarea.selectArea=function(thisObject){
	$.ajax({
		type : "GET",
		url :areaUrl+"/area/childrenArea?format=json&id="+$(thisObject).val()+"&callback?",
		dataType:"jsonp",
		jsonp:"callback",
		cache:true,
		success : function(data) {
			if(!!data&&!!data.data&&data.data.length>0){
				Iarea.loadChangeArea(data.data,$(thisObject));
			}
		},
		error : function(data) {
			
		}
	})
}

Iarea.selectedArea=function(){
	var areaId="";
	$("#"+Iarea.areaId).find("select").each(function(){
		if($(this).val().length>areaId.length){
			areaId=$(this).val();
		}
	});
	if(!areaId||areaId==''||areaId=='-1'){
		areaId="";
	}
	return areaId;
}