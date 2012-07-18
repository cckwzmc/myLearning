function changeDefaultCssForm(obj) {
	$(obj).find("[datatype]").each(function() {
		$(this).parent().children("label:first").show();
		$(this).parent().children("label:last").hide();
		$(this).css("borderColor", "");
	});
	$(obj).find("[displaydatatype]").each(function() {
		$(this).parent().children("label:first").show();
		$(this).parent().children("label:last").hide();
		$(this).css("borderColor", "");
	});
}

function showAddDiv(obj){

	if($('#indentagentTr').css("display")=='none'){
		$('#indentagentTr').show();
	}else{
		$('#indentagentTr').hide();
	}
}

function createAddress(obj){
	
	var phone =  $('#phone').val();
	var mobile = $('#mobile').val()
	if("" == phone && "" == mobile)
	{
		$("#errorMobile").html("手机和固定电话请至少填写一项!");
		return false;
	}
	var opAddress = $('#opAddress').val(); //判断是修改还是新增	
	if(opAddress == "create"){ //新增
		$("form:first").attr("action",UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/address/create");
		$("form:first").submit();
	}else{
		$("form:first").attr("action", UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/address/update");
		$("form:first").submit();
	}
}

//删除收货地址
function deleteAddress(obj){
	var str = "您确定要删除吗？";
	if($(obj).parent().parent().parent().find("td:first").children("input[name='isDefault']").eq(0).val()=="Y")
	{
		str = "您确定要删除默认地址吗？";
	}
	var self = obj;
	
	str = str + '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="delConf" >确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="javascript:hideTipDiv()" >取消</a>';
	var offset = $(obj).offset();
	showTipDiv(str,offset.left-30,offset.top-60,3);
	
	$('#delConf').click(function(){
		deleteAddressConfirm(self);
	    hideTipDiv();
	});
}
function deleteAddressConfirm(obj){
	
	var addressId = $(obj).parent().parent().parent().children().eq(0).val();//获取地址ID
	var url = "destory/"+addressId;
	var str = "删除地址失败，非法操作！";
	jQuery.ajax({
		type : "POST",
		url : url+"?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode != "0")
					{
						if($(obj).parent().parent().parent().find("td:first").children("input[name='isDefault']").eq(0).val()=="Y")
						{
							str = "删除地址失败，非法操作！";
						}
						str = str + '<br>&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="delConf" >确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="javascript:hideTipDiv()" >取消</a>';
						var offset = $(obj).offset();
						showTipDiv(str,offset.left-30,offset.top-60,3);
						
						$('#delConf').click(function(){
						    hideTipDiv();
						});
					}else{
						 window.location = "list";
					}
				}
			}
		},
		error : function() {
			alert("error");
		}
	});
}

function makeAsMaster(addressId){
	$("form:first").attr("action", UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/address/update/"+addressId);
	$("form:first").submit();

}


//加载省 市 区 三级数据
function initParams(provinceCode,regionCode,cityCode){
	getProvince();
    setTimeout(function(){
	    $("#provinceCode").attr("value",provinceCode);
	    getCity();
	    setTimeout(function(){ //js延时，等待getCity()返回信息
	    	$("#regionCode").attr("value",regionCode);
	    	getArea();
	    	setTimeout(function(){ //js延时，等待getArea()返回信息
			    	$("#cityCode").attr("value",cityCode);
			    	getZip();
			    	setTimeout(function(){ //js延时，等待getZip()返回信息
			    		$("#postCode").val(address.postCode);
			    		$("#areaCode").val(address.areaCode);
			    	},200);
			    },200);
	    },200);
    },200);
}

/*******************************************************************************
 * Ajax function
 ******************************************************************************/

// 获取省份
function getProvince() {
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getProvinceList?format=json",
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs != null) {
					if(objs.scode == "0")
					{
						for ( var i = 0; i < objs.data.length; i++) {
							jQuery("#provinceCode").append(
									"<option value='"
											+ objs.data[i].paramCode + "'>"
											+ objs.data[i].paramDesc
											+ "</option>");
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

function getCity() {

	var provinceCode = jQuery("#provinceCode option:selected").val();
	jQuery.ajax({
		type : "GET",
		url :UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getListByParamTypeAndParentCode.do?format=json&parentCode="+provinceCode,
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);

				jQuery("#regionCode option").remove();
				jQuery("#regionCode").append("<option value='-1'>请选择</option>");
				jQuery("#cityCode option").remove();
				jQuery("#cityCode").append("<option value='-1'>请选择</option>");
				if (objs != null) {
					if(objs.scode == "0")
					{
						for ( var i = 0; i < objs.data.length; i++) {
							jQuery("#regionCode").append(
									"<option value='" + objs.data[i].paramCode
											+ "'>" + objs.data[i].paramDesc
											+ "</option>");
						}
					}
				}
			}
		},
		error : function() {
		}
	});
}

function getArea() {

	var cityCode = jQuery("#regionCode option:selected").val();
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getListByParamTypeAndParentCodeForArea.do?format=json&cityCode="+cityCode,
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);

				jQuery("#cityCode option").remove();
				jQuery("#cityCode").append("<option value='-1'>请选择</option>");
				if (objs != null) {
					if(objs.scode == "0")
					{
						for ( var i = 0; i < objs.data.length; i++) {
							jQuery("#cityCode").append(
									"<option value='" + objs.data[i].paramCode
											+ "'>" + objs.data[i].paramDesc
											+ "</option>");
						}
					}
				}

			}
		},
		error : function() {
		}
	});
}

function getZip() {
	var cityCode = jQuery("#cityCode option:selected").val();
	jQuery.ajax({
		type : "GET",
		url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/getParam/getAreaPostCode2Map.do?format=json&areaCode="+cityCode,
		dataType : "text",
		success : function(data, textStatus) {
			if (isNaN(data)) {
				var objs =  $.parseJSON(data);
				if (objs.data[0] != null) {
					if(objs.scode == "0")
					{
						$("#postCode").val(objs.data[0].postCode);
			    		$("#areaCode").val(objs.data[0].areaCode);
					}
				}
			}
		},
		error : function() {
		}
	});
}
