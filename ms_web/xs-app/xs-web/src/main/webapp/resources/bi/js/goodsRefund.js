$(document).ready(function() {
	

	
	var type = $("#type").val();

	var type = $("#type").val();

	var reson = $(":radio:checked").val();

	//$("#trhuan_huo").hide();
	//$("#trhuan_huo1").hide();

	if (reson == "尺码偏小") {
		//changdx
		//radio1_2
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='black'>*</font>问题描述：");

	} else if (reson == "尺码偏大") {
		global = 2;
		$("#p1").html("凭证：<font color='black' >*</font>");
		$("#p2").html("问题描述：<font color='black'>*</font>");
		$("#changdx").html(value);
		;

	} else if (reson == "客户原因") {

		$("#p1").html("凭证：<font color='black' >*</font>");
		$("#p2").html("问题描述：<font color='red'>*</font>");

	} else if (reson == "商品质量原因") {
		$("#p1").html("<font color='red' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");

	} else if (reson == "发错货") {
		$("#p1").html("<font color='red' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");

		if (type == "2") {
			$("#span_5").html("对不起，由于我们的失误，给你带来购物体验不好，实在抱歉。");
		}
	} else if (reson == "图片与实物不符合") {
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='black'>*</font>问题描述：");
	} else {
		$("#p1").html("<font color='red' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");
	}
	if (type == "2") {
		$("#btchangRe1").hide();
		$("#btchangRe2").hide();
		$("#btchangRe3").hide();
	} else {
		$("#btchangRe1").show();
		$("#btchangRe2").show();
		$("#btchangRe3").show();
	}

});
function changreflow() {

	$("#type").val("2");
	$("#thsq").html("换货申请");
	$("#radio1_2").hide();
	$("#radio_3").hide();
	$("#radio_4").hide();
	$("#radio_5").hide();
	$("#radio_6").hide();
}
function refundForReturn(index) {
	$("#type").val(index);
	var div = document.getElementById("item_0");
	div.style.display = "";
	$("#changeBtn").attr("disabled", true);
	$("#refundmoreflag_0").html("退货详细");
}

function refundForChange(index) {
	$("#type").val(index);
	var div = document.getElementById("item_0");
	div.style.display = "";
	$("#rebackBtn").attr("disabled", true);
	$("#refundmoreflag_0").html("换货详细");
}

var global;

function radiocheck(value) {
	var type = $("#type").val();
	global = 0;

	if (type == "2") {
		$("#btchangRe1").hide();
		$("#btchangRe2").hide();
		$("#btchangRe3").hide();
	} else {

		$("#btchangRe1").show();
		$("#btchangRe2").show();
		$("#btchangRe3").show();

	}
	if (value == "尺码偏小") {
		//changdx
		//radio1_2
		global = 1;
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='black'>*</font>问题描述：");
		$("#changdx").html(value);
		;
		$("#radio1_2").show();
		$("#radio_3").hide();
		$("#radio_4").hide();
		$("#radio_5").hide();
		$("#radio_6").hide();
	} else if (value == "尺码偏大") {
		global = 2;
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='black'>*</font>问题描述：");
		$("#changdx").html(value);
		;
		// alert($("#changdx").html());
		$("#radio1_2").show();
		$("#radio_3").hide();
		$("#radio_4").hide();
		$("#radio_5").hide();
		$("#radio_6").hide();
	} else if (value == "客户原因") {
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");
		global = 3;
		$("#changdx").val("");
		$("#radio1_2").hide();
		$("#radio_3").show();
		$("#radio_4").hide();
		$("#radio_5").hide();
		$("#radio_6").hide();
	} else if (value == "商品质量原因") {
		global = 4;
		$("#p1").html("<font color='red' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");
		$("#changdx").val("");
		$("#radio1_2").hide();
		$("#radio_3").hide();
		$("#radio_4").show();
		$("#radio_5").hide();
		$("#radio_6").hide();
	} else if (value == "发错货") {
		global = 5;
		$("#p1").html("<font color='red' >*</font>凭证：");
		$("#p2").html("<font color='red'>*</font>问题描述：");
		$("#changdx").val("");
		if (type == "2") {
			$("#span_5").html("对不起，由于我们的失误，给你带来购物体验不好，实在抱歉。");
		}
		$("#radio1_2").hide();
		$("#radio_3").hide();
		$("#radio_4").hide();
		$("#radio_5").show();
		$("#radio_6").hide();
	} else if (value == "图片与实物不符合") {
		global = 6;
		$("#p1").html("<font color='black' >*</font>凭证：");
		$("#p2").html("<font color='black'>*</font>问题描述：");
		$("#changdx").val("");
		$("#radio1_2").hide();
		$("#radio_3").hide();
		$("#radio_4").hide();
		$("#radio_5").hide();
		$("#radio_6").show();
	}
}

function validateNum(num) {
	var inputrefundquantity = num;
	var canrefundquantity = document.getElementById("ckquantity").value;
	var reg = /^\d+$/;

	if (!reg.test(inputrefundquantity)) {

		document.getElementById("inputrefundquantitytitle_0").innerHTML = "输入有误请重新填写";
		return;
	}
	if (num == "") {
		document.getElementById("inputrefundquantitytitle_0").innerHTML = "输入的数量数量不能为空";

		return;
	}
	if (canrefundquantity - inputrefundquantity < 0) { //用户输入的数量大于可退的数量
		document.getElementById("inputrefundquantitytitle_0").innerHTML = "输入的数量大于可退的数量";
		$("#inputrefundquantity_0").val(num);
		return;
	} else if (inputrefundquantity == 0) {
		document.getElementById("inputrefundquantitytitle_0").innerHTML = "输入的数量数量不能为0";
	} else {
		document.getElementById("inputrefundquantitytitle_0").innerHTML = "";
	}
}

function checksubmit() {
	var size = document.getElementById("size").value;
	//alert($("#iframe_0",parent.document.body).contents().find("#proofs").val());
	//alert($(window.frames["iframe_0"].document).find("#proofs").val());
	$("#proofstr").val($(window.frames["iframe_0"].document).find("#proofs").val());
	//alert($("#proofstr").val());
	var canrefundquantity = document.getElementById("ckquantity").value;
	var inputrefundquantity = document.getElementById("inputrefundquantity_0").value;
	var reg = /^\d+$/;
	if (inputrefundquantity == "") {
		document.getElementById('inputrefundquantitytitle_0').innerHTML = "请输入数量";
		$("#span_qunt").html("<font color='red'>*数量：</font>");
		return;
	} else {
		$("#span_qunt").html("<font color='red'>*</font>数量：");
	}
	if (!reg.test(inputrefundquantity)) {

		document.getElementById("inputrefundquantitytitle_0").innerHTML = "输入有误请重新填写";
		return;
	}

	if (canrefundquantity - inputrefundquantity < 0
			|| isNaN(canrefundquantity - inputrefundquantity)) {
		document.getElementById('inputrefundquantitytitle_0').innerHTML = "超过可申请数量";
		//document.getElementById("item" ).style.display = "block";
		return;
	}

	if (inputrefundquantity == 0) {
		document.getElementById('inputrefundquantitytitle_0').innerHTML = "请输入数量";
		// document.getElementById("item_0").style.display = "block";
		return;
	} else {

	}
	//  var tempId = "detailList[0" + "].businessReason";
	var reason = document.getElementsByName("businessReason");
	if (!reason[0].checked && !reason[1].checked && !reason[2].checked
			&& !reason[3].checked && !reason[4].checked && !reason[5].checked) {
		document.getElementById("inputrefundbussinesreason").innerHTML = "请选择原因";
		//  document.getElementById("item_0").style.display = "block";
		return false;
	} else {
		document.getElementById("inputrefundbussinesreason").innerHTML = "";
	}

	var textareavalue = document.getElementById("description").value;
	//var patternTextarea = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	// var pattern = new patternTextarea("[`~!@#$^&*()=|{}\\[\\]<>/?~@#￥&*——|{}]");
	var patternTextarea = new RegExp("[`~!@#$^&*()=|{}\\[\\]<>/?~@#￥&*——|{}]");
	
	if ("请详细描述你的退/换货原因，可以加速您的退/换货进程"!=textareavalue&&patternTextarea.test(textareavalue)) {
		document.getElementById("descriptiontip_0").innerHTML = "详情描述不能包含特殊字符！";
		return false;
	} else {
		document.getElementById("descriptiontip_0").innerHTML = "";
	}
	
	if (global == 4 || global == 3 || global == 5) {

		if (textareavalue == ''
				|| textareavalue == '请详细描述你的退/换货原因，可以加速您的退/换货进程') {
			document.getElementById("descriptiontip_0").innerHTML = "您没有填写问题描述，会影响您的退货速度，请完善您的退货原因";
			$("#p2").html("<font color='red'>*问题描述：</font>");
			return false;
		} else {
			$("#p2").html("<font color='red'>*</font>问题描述：");
		}
	}
	if (global == 4 && $("#proofstr").val() == "" || global == 5
			&& $("#proofstr").val() == "") {
		$("#iframe_0").contents().find("#uploadtype").html("<font color=\"red\">请上传图片！！</font>");
		//$("#uploadtype").
		return;
	}

	//var regcontactName =  new RegExp("/^[\u2E80-\uFE4F]+$/") ;
	var regcontactName = new RegExp("^[\u4E00-\u9FA5]+$");

	var contactname = document.getElementById("contactName").value;
	if (contactname == '') {
		document.getElementById("input_contactName").innerHTML = "联系人不能为空";
		return false;
	} else if (!regcontactName.test(contactname)) {

		document.getElementById("input_contactName").innerHTML = "只能够输入汉字";
		return false;
	}

	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");

	if (pattern.test(contactname)) {
		document.getElementById("input_contactName").innerHTML = "联系人不能包含特殊字符";
		return false;
	} else {
		document.getElementById("input_contactName").innerHTML = "";
	}

	var contactPostCode = document.getElementById("contactPostCode").value;
	var pattern = /^[0-9]{6}$/;
	if (contactPostCode == '') {
		document.getElementById("input_contactPostCode").innerHTML = "邮编不能为空";
		return false;
	}
	var isTrue = pattern.test(contactPostCode);

	if (!isTrue) {
		document.getElementById("input_contactPostCode").innerHTML = "邮编为6位数字!";
		return false;
	} else {
		document.getElementById("input_contactPostCode").innerHTML = "";
	}

	var contactMobile = document.getElementById("contactMobile").value;
	var contactPhone = document.getElementById("contactPhone").value;

	var regMobile = /^0*(13|14|15|18)\d{9}$/;
	var patternPhone = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	/*    if ('' == contactMobile) {
	    document.getElementById("input_contactMobile").innerHTML = "手机号码不能为空!";
	}*/
	if ('' == contactPhone && '' == contactMobile) {
		document.getElementById("input_contactPhone").innerHTML = "<font color='red'>电话/手机不同时为空</font>";
		return false;
	} else {
		//手机号不为空
		if (contactMobile != '') {
			var isMobile = regMobile.test(contactMobile);
			if (!isMobile) {
				document.getElementById("input_contactMobile").innerHTML = "手机号码格式不正确!";
				return false;
			} else {
				document.getElementById("input_contactPhone").innerHTML = "<font color=''>电话/手机不同时为空</font>";
			}
		}

		if (contactPhone != '') {
			var isPhone = patternPhone.test(contactPhone);
			if (!isPhone) {
				document.getElementById("input_contactPhone").innerHTML = "<font color='red'>电话格式不对！</font>";
				return false;
			} else {
				document.getElementById("input_contactPhone").innerHTML = "<font color=''>电话/手机不同时为空</font>";
			}
		}

	}
	document.getElementById("input_contactMobile").innerHTML = "";
	var contactAddress = document.getElementById("contactAddress").value;
	if (contactAddress == '') {
		document.getElementById("input_contactAddress").innerHTML = "地址不能为空";
		return;
	} else {
		document.getElementById("input_contactAddress").innerHTML = "";
	}
	document.refundItemFlowForm.action = "creat";
	document.refundItemFlowForm.submit();
}
