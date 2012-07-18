function validForm(obj){
	var flag = true;
	$(obj).find("[datatype]").each(function(){
		$(this).val($.trim($(this).val()));
		var chooseone = $(this).attr("chooseone");//处理二�1�7�一
		var datatype = $(this).attr("datatype");
		if(typeof(chooseone) != "undefined"){
			if($(this).val()=="" && $("#"+chooseone).val() == ""){ //两个都是穄1�7
				$(this).parent().children("label:first").hide();
				$(this).parent().children("label:last").text($(this).attr("nullmsg"));
				$(this).parent().children("label:last").show();
				clearErrorMsg($(this));
				$(this).css("borderColor","red");
				 				
				flag = false;
			}else if($(this).val()!="" && $("#"+chooseone).val() != ""){ //两�1�7�都不为穄1�7
				
				if(!regcheck(datatype,$(this).val())){
					$(this).parent().children("label:first").hide();
					$(this).parent().children("label:last").text($(this).attr("errormsg"));
					$(this).parent().children("label:last").show();
					clearErrorMsg($(this));
					$(this).css("borderColor","red");
					flag = false;
				}else{
					$(this).parent().children("label:first").show();
					$(this).parent().children("label:last").hide();
					$(this).css("borderColor","");
				}
			}else if($(this).val()!="" && $("#"+chooseone).val() == ""){ //如果此不为空,比为穄1�7
				if(!regcheck(datatype,$(this).val())){
					$(this).parent().children("label:first").hide();
					$(this).parent().children("label:last").text($(this).attr("errormsg"));
					$(this).parent().children("label:last").show();
					clearErrorMsg($(this));
					$(this).css("borderColor","red");
					flag = false;
				}else{
					$(this).parent().children("label:first").show();
					$(this).parent().children("label:last").hide();
					$(this).css("borderColor","");
				}
				$("#"+chooseone).parent().children("label:first").show();
				$("#"+chooseone).parent().children("label:last").hide();
				$("#"+chooseone).css("borderColor","");
			}
		}else{
			if($(this).attr("nullmsg")!=""){//不能为空
				if($(this).val()==""){
					$(this).parent().children("label:first").hide();
					$(this).parent().children("label:last").text($(this).attr("nullmsg"));
					$(this).parent().children("label:last").show();
					clearErrorMsg($(this));
					$(this).css("borderColor","red");
					flag = false;
				}else if(!regcheck(datatype,$(this).val())){
					$(this).parent().children("label:first").hide();
					$(this).parent().children("label:last").text($(this).attr("errormsg"));
					$(this).parent().children("label:last").show();
					clearErrorMsg($(this));
					$(this).css("borderColor","red");
					flag = false;
				}else{
					$(this).parent().children("label:first").show();
					$(this).parent().children("label:last").hide();
					$(this).css("borderColor","");
				}
			}else if($(this).val()!=""){
				if(!regcheck(datatype,$(this).val())){
					$(this).parent().children("label:first").hide();
					$(this).parent().children("label:last").text($(this).attr("errormsg"));
					$(this).parent().children("label:last").show();
					clearErrorMsg($(this));
					$(this).css("borderColor","red");
					flag = false;
				}else{
					$(this).parent().children("label:first").show();
					$(this).parent().children("label:last").hide();
					$(this).css("borderColor","");
				}
			}
		}
	});
	
	return flag;
}

function clearErrorMsg(obj){
	
	obj
	.focus(function(){
		$(this).parent().children("label:first").show();
		$(this).parent().children("label:last").hide();
		$(this).css("borderColor","");	
	})
	
}
function regcheck(type,gets){
    var reg;
	switch(type){
		case "*":
			return true;
		case "*6-16":
			reg= /[^\s]{6,16}/;
			gets = gets.replace(/[^\x00-\xff]/g,"**");
			if(gets.length>16){
				return false;
			}
			return reg.test(gets);
		case "*2-20":
			reg= /[^\s]{2,20}/;
			gets = gets.replace(/[^\x00-\xff]/g,"**");
			if(gets.length>20){
				return false;
			}
			return reg.test(gets);
		case "n":
			return !isNaN(gets);
		case "s":
			return isNaN(gets);
		case "s6-18":
			reg= /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{6,18}$/;
			return reg.test(gets);
		case "*4-20":                            //收货人名秄1�7
			reg= /^1[3458]{1}[0-9]{9}$/; //不能为手朄1�7
			if(reg.test(gets)){
				return false;
			}
			reg = /^[^\|"'<>@#*\$]*$/;  //不能为特殊符叄1�7
			if(!reg.test(gets)){
				return false;
			}
			reg= /[^\s]{4,20}/;
			gets = gets.replace(/[^\x00-\xff]/g,"**");
			if(gets.length>20){
				return false;
			}
			return reg.test(gets);
		case "post":
			reg= /^[0-9]{6}$/;
			return reg.test(gets);
		case "mobile":
			reg= /^1[3458]{1}[0-9]{9}$/;
			return reg.test(gets);
		case "email":
			reg = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
			return reg.test(gets);
		case "selnot-1":
			if(gets == "-1")
				return false;
			else 
				return true;
		case "*4-60":
			reg= /[^\s]{4,60}/;
			gets = gets.replace(/[^\x00-\xff]/g,"**");
			if(gets.length>60){
				return false;
			}
			return reg.test(gets);
		case "*4-120":
			reg= /[^\s]{4,120}/;
			gets = gets.replace(/[^\x00-\xff]/g,"**");
			if(gets.length>120){
				return false;
			}
			return reg.test(gets);
		case "codeMum":
			reg = /^[\d]{3,4}$/;
			return reg.test(gets);
			
		case "phone":
			reg = /^[\d]{7,8}$/;
			return reg.test(gets);
		default:
			return false;
	}
}

function onbulerCheck(obj){
	var flag = true;
	var nosameTo = $(obj).attr("nosame");//处理两�1�7�不各1�7
	if(typeof(nosameTo) != "undefined"){
		if($("#"+nosameTo).val()==$(obj).val() && $(obj).val()!=""){
			$(obj).parent().children("label:first").hide();
			$(obj).parent().children("label:last").text($(obj).attr("nosamemsg"));
			$(obj).parent().children("label:last").show();
			$(obj).css("borderColor","red");
			flag = false;
			//$(obj).focus();
			return flag;
		}else{
			$(obj).parent().children("label:first").show();
			$(obj).parent().children("label:last").hide();
			$(obj).css("borderColor","");
			flag = true;
		}
	}
	var sameto = $(obj).attr("sameto");//处理两�1�7�相各1�7
	if(typeof(sameto) != "undefined"){
		if($("#"+sameto).val()!=$(obj).val()){
			$(obj).parent().children("label:first").hide();
			$(obj).parent().children("label:last").text($(obj).attr("sametomsg"));
			$(obj).parent().children("label:last").show();
			$(obj).css("borderColor","red");
			flag = false;
			//$(obj).focus();
			return flag;
		}else{
			$(obj).parent().children("label:first").show();
			$(obj).parent().children("label:last").hide();
			$(obj).css("borderColor","");
			flag = true;
		}
	}
	return flag;
}

function onbulerCheckArea(obj){
	var flag = true;
	var toolong = $(obj).attr("toolong");//处理两�1�7�不各1�7
	if(typeof(toolong) != "undefined"){
		var tempvalue = $(obj).val().replace(/[^\x00-\xff]/g,"**");
		if($(obj).val()!="" && tempvalue.length>500){
			$(obj).parent().children("label:first").hide();
			$(obj).parent().children("label:last").text($(obj).attr("toolongmsg"));
			$(obj).parent().children("label:last").show();
			$(obj).css("borderColor","red");
			flag = false;
			$(obj).focus();
			return flag;
		}else{
			$(obj).parent().children("label:first").show();
			$(obj).parent().children("label:last").hide();
			$(obj).css("borderColor","");
			flag = true;
		}
	}
	return flag;
}

function checkThis(obj){
	var flag = true;
 
	var datatype = $(obj).attr("datatype");
	if(typeof(datatype) != "undefined"){
		if(!regcheck(datatype,$(obj).val())){
			$(obj).parent().children("label:first").hide();
			$(obj).parent().children("label:last").text($(obj).attr("errormsg"));
			$(obj).parent().children("label:last").show();
			$(obj).focus();
			$(obj).css("borderColor","red");
			flag = false;
		}else{
			$(obj).parent().children("label:first").show();
			$(obj).parent().children("label:last").hide();
			$(obj).css("borderColor","");
		}
	}
	return flag;
}

function onbulerCheckDisplay(obj){
	var flag = true;
	if($('#indentagentTr').css("display")!='none'){
		if($(obj).val()!=''){
			var datatype = $(obj).attr("displaydatatype");
			if(typeof(datatype) != "undefined"){
				if(!regcheck(datatype,$(obj).val())){
					$(obj).parent().children("label:first").hide();
					$(obj).parent().children("label:last").text($(obj).attr("errormsg"));
					$(obj).parent().children("label:last").show();
					$(obj).focus();
					$(obj).css("borderColor","red");
					flag = false;
				}else{
					$(obj).parent().children("label:first").show();
					$(obj).parent().children("label:last").hide();
					$(obj).css("borderColor","");
				}
			}
		}
	}
	return flag;
}

function changeDefaultCssForm(obj){
	$(obj).find("[datatype]").each(function(){
		$(this).parent().children("label:first").show();
		$(this).parent().children("label:last").hide();
		$(this).css("borderColor","");
	});
	$(obj).find("[displaydatatype]").each(function(){
		$(this).parent().children("label:first").show();
		$(this).parent().children("label:last").hide();
		$(this).css("borderColor","");
	});
}
function trimTxt(txt)
{
   return txt.replace(/(^\s*)|(\s*$)/g, "");
}

//非法字符过滤
function is_forbid(temp_str)
{
    temp_str=trimTxt(temp_str);
	temp_str = temp_str.replace('*',"@");
	temp_str = temp_str.replace('--',"@");
	temp_str = temp_str.replace('/',"@");
	temp_str = temp_str.replace('+',"@");
	temp_str = temp_str.replace('\'',"@");
	temp_str = temp_str.replace('\\',"@");
	temp_str = temp_str.replace('$',"@");
	temp_str = temp_str.replace('^',"@");
	temp_str = temp_str.replace('.',"@");
	//temp_str = temp_str.replace('#',"@");
	//temp_str = temp_str.replace('(',"@");
	//temp_str = temp_str.replace(')',"@");
	//temp_str = temp_str.replace(',',"@");
	temp_str = temp_str.replace(';',"@");
	temp_str = temp_str.replace('<',"@");
	temp_str = temp_str.replace('>',"@");
	//temp_str = temp_str.replace('?',"@");
	temp_str = temp_str.replace('"',"@");
	temp_str = temp_str.replace('=',"@");
	temp_str = temp_str.replace('{',"@");
	temp_str = temp_str.replace('}',"@");
	//temp_str = temp_str.replace('[',"@");
	//temp_str = temp_str.replace(']',"@");
	var forbid_str=new String('@,%,~,&');
	var forbid_array=new Array();
	forbid_array=forbid_str.split(',');
	for(i=0;i<forbid_array.length;i++)
	{
		if(temp_str.search(new RegExp(forbid_array[i])) != -1)
		return false;
	}
	return true;
}
