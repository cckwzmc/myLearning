var Icat = {}
/**
 * category setup
 */
var IcatUrl="http://d.istyle.com/istyle-web";
Icat.setup = function(catId) {
	Icat.catId=catId;
	$.ajax({
		type : "GET",
		url :IcatUrl+"/category/topCategory?format=json&callback?",
		dataType:"jsonp",
		jsonp:"callback",
		cache:true,
		success : function(data) {
			if(!!data&&!!data.data){
				Icat.loadTopCategory(data.data);
			}
		},
		error : function(data) {
			
		}
	})
}


Icat.loadTopCategory=function(data){
	var html='';
	html+='<select id="topCat" name="topCat" level="1">'+
 		'<option value="-1">请选择</option>';
	for(var i=0;i<data.length;i++){
		html+='<option value="'+data[i].id+'">'+data[i].catName+'</option>';
	}
	html+='</select>';
	$("#"+Icat.catId).html(html);
	$("#topCat").bind("change",function(){
		var v=$("#topCat").val();
		if(v=='-1'){
			alert("请选择");
			return false;
		}
		Icat.selectCat(this);
	});
}

Icat.loadChildrenCategory=function(data,thisObj){
	var v=$(thisObj).val();
	if(v=='-1'){
		alert('请选择')
		return false;
	}
	var l=$(thisObj).attr("level");
	var level=parseInt(l);
	Icat.clearLevel(level);
	var html='';
	html+='<select id="cat_'+(level+1)+'" name="cat_'+(level+1)+'" level="'+(level+1)+'">'+
 		'<option value="-1">请选择</option>';
	for(var i=0;i<data.length;i++){
		html+='<option value="'+data[i].id+'">'+data[i].catName+'</option>';
	}
	html+='</select>';
	$(html).insertAfter(thisObj);
	$("#cat_"+(level+1)).bind("change",function(){
		var v=$("#cat_"+(level+1)).val();
		if(v=='-1'){
			alert("请选择");
			return false;
		}
		Icat.selectCat(this);
	});
}

/**
 * 清楚leve>params+1的select
 */
Icat.clearLevel=function(level){
	var select=$("#"+Icat.catId).find("select");
	if(select.length>1){
		for(var i=0;i<select.length;i++){
			var s=parseInt($(select[i]).attr("level"));
			if(s>=(level+1)){
				$(select[i]).remove();
			}
		}
	}
}

Icat.selectCat=function(thisObj){
	$.ajax({
		type : "GET",
		url :IcatUrl+"/category/childrenCategory?format=json&id="+$(thisObj).val()+"&callback?",
		dataType:"jsonp",
		jsonp:"callback",
		cache:true,
		success : function(data) {
			if(!!data&&!!data.data&&data.data.length>0){
				Icat.loadChildrenCategory(data.data,$(thisObj));
			}
		},
		error : function(data) {
			
		}
	})
}