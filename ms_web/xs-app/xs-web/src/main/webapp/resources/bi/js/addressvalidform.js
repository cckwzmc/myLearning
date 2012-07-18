$(document).ready(function(){

	$('#createAddressForm').validate({   
		  event:"submit",
		  rules:{   
			  rcverName:{required:true,rangelength:[4,20]},
			  provinceCode: {required:true,min:0},
			  regionCode: {required:true,min:0},
			  cityCode: {required:true,min:0},
			  addressInfo:{required:true,rangelength:[0,70]},
			  postCode: {required:true,min:0}
		  },   
		  errorPlacement: function(error, element) {
				var em = element.parent("td").find('.cl');
				if(em.length) em.hide();
				error.appendTo( element.parent("td"));
			},
		  messages: {
			  rcverName:{rangelength:'收货人必须为4-20个字符！',required:'收货人必须填写！'} ,
			  provinceCode:{min:'所属省份不能为空！',required:'请选择所属省！'},
			  regionCode:{min:'所属市 不能为空！',required:'请选择所属市！'},
			  cityCode:{min:'所属区县不能为空！',required:'请选择所属区县！'},
			  addressInfo:{rangelength:'输入的汉字长度不能大于35,字母长度不能够大于70！',required:'请输入详细地址！'},
			  postCode:{min:'邮编不能为空！',required:'请填写正确的邮编！'}
		  }
		  });
		var v_provinceCode =  $("#provinceCodeTemp").val();
		var v_regionCode =  $("#regionCodeTemp").val();
		var v_cityCode =  $("#cityCodeTemp").val();
		if("" != v_provinceCode)
		{
			initParams(v_provinceCode,v_regionCode,v_cityCode);
		}else
		{
			getProvince();
		}
  
});


