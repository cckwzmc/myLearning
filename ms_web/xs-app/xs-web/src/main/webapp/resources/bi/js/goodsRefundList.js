var globObj = null;
//-取消 功能按钮
function refunFlow(refundItemFlowCode,obj){
	
	var confirmMsg = confirm("请确认是否取消此次申请") ;

    if(!confirmMsg){
	       return;
	  }
	
	var tips="";  
	var flag = false;
	
     jQuery.ajax({
            type : "GET",
            url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/returnChangeList/destory/"+refundItemFlowCode + "?format=json",
            dataType : "text",
            success : function(data, textStatus) 
            {
                    if (isNaN(data)) 
                    {
                       var objs =  $.parseJSON(data);
                       if (objs != null)
                        {
                           if(objs.scode == "0")
                           {//alert(objs.data);
                                if(objs.data == "1"){
                                	
                                	$("#"+obj.id).attr("disabled", true);
                                	 document.mainForm.submit();
                                	 return;
								   
                               }else{
                            	   tips ="系统繁忙，取消申请没有成功，请稍后再试！！";
								   flag = false;
                               }
                           }
                        }
                    }
                    offset = $("#"+obj.id).offset();
        	        showTipDiv(tips,offset.left-20,offset.top-50,2);
        	        return ;
              },
             error : function()
             {
            	 flag = true;
          	     offset = $("#"+obj.id).offset();
				 showTipDiv("不好意思系统繁忙，请稍后再试！",offset.left,offset.top-35,3);	
				 return;	
             }
       }); 
  }

//-登记
function registExpressOrder(orderId,returnFlowId) {
	
	//alert(orderId+"----"+returnFlowId);
	
		var djstr1 ="";
		var djstr2="";
		var djstr3 ="";
		var djstr4="";
		var djstr5="";
		var djstr6="";
		
		
		var flag = true;
		var tips ="";
		
		djstr1= "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"loginbill\" style=\"display:\"\";\">";
		djstr2= "<tr> <td  align=\"right\" style=\"width: 70px;\">快递公司 ：</td> <td  align=\"left\" style=\"width: 138px;\">";
		djstr3= "<input type=\"text\" title=\"支持拼音模糊查询\" id=\"companyName\" name=\"companyName\" value=\"\"   autocomplete=\"off\" style=\"width: 138px;\"  /> </td></tr>";
		djstr5="<tr> <td  align=\"right\"  style=\"width: 70px;\">快递单号 ：</td> <td  align=\"left\" style=\"width: 138px;\"> <input type=\"text\" name=\"expressOrder\" id=\"expressOrder\" size=\"20\" style=\"width: 138px;\"/></td> </tr>";
		djstr6="<tr> <td colspan=2 align=\"center\"  ><input type=\"button\" name=\"登记快递单号\" value=\"登记快递单号\" id=\"buttion\" onclick=\"registExpressOrderSubmit()\"/></td> </tr> ";
		djstr4= "<tr id=\"dj\" style=\"display:'';\"  >  <td  align=\"center\" colspan=\"2\"   ><font color=\"red\">Tips: 快递公司输入支持模糊查询匹配，也可以自己输入。</font></td></tr></table>";

		var str = djstr1+djstr2+djstr3+djstr5+djstr6+djstr4; 
		
		$('#refundItemFlowDOId').val(returnFlowId);
        $('#refundItemFlowDOOrderId').val(orderId);
	//?     $("#companyName").val("");
    //?     $("#expressOrder").val("");
	 	 
	     $.xiupop({
			 title:'登记快递单号',
			 content: str,
			 contentCss:'relative',
			 hasBg:true,
			 center:true,
			 width:'400px',
			 height:'200px',
			 
			 callback:function(){
				 	$('#companyName').unbind("keydown").autocomplete({
	                     serviceUrl: UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/returnChangeList/saveCompanyQueryB" + "?format=json&jsoncallback=?",   //"ddd？？？"+Math.random(),
		                 minChars: 1, // Minimum request length for triggering autocomplete
		                 autoSubmit:false
	                 });
	          }
	     });
}

//-登记  登记快递单号
function registExpressOrderSubmit() {

	var data="";
	var temp ="";
    var companyName = encodeURIComponent(document.getElementById("companyName").value);
    var expressOrder = encodeURIComponent(document.getElementById("expressOrder").value);
    var refundFlowId = $('#refundItemFlowDOId').val();
    var orderId = $('#refundItemFlowDOOrderId').val();
    //alert(companyName+"---"+expressOrder+ "--"+refundFlowId+"--"+orderId);
    //验证数据是否为空
    if (companyName == "" || companyName == "请换关键字查询!!") {
        alert("请完整填写上述内容");
        return false;
     }
     else if (expressOrder == "") {
         alert("请完整填写上述内容");
        return false;
     }
    
     var  actionUrl = UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/returnChangeList/saveCompany/"+refundFlowId + "?format=json&orderId="+orderId+"&companyName="+companyName+"&expressOrderCode="+expressOrder;
 
	var flag = -1;
	var tips = "";
	$("#showtips").html("");
	
	jQuery.ajax({
        type : "GET",
        url : actionUrl,
        dataType : "text",
        success : function(data, textStatus) {
        	//alert(data);
 	        	if(isNaN(data)){
 	        		 var objs =  $.parseJSON(data);
 	        		 if(objs != null){
 	        			  if(objs.scode=="0"){
 	        				  if(objs.data=="1"){
 			                 	     $("#"+globObj).attr("disabled", true);
 			                 	  	 tips="恭喜你登记快递信息成功！！";
 			                 	  	 document.mainForm.submit();
 			                 	  	 return;
 			                 	     //flag = 0;
 	                 	       }else{
 	                     	     tips="不好意思系统繁忙，登记失败，请稍后再试！！";
 	                             flag = 1
 	     					    alert("登记失败！！");
 	                           }
 	        			  }else{
 	        	            	 tips="不好意思系统繁忙，登记失败，请稍后再试！！";
 	        				     flag = 2;
 	        	            }
 	        		 }else{
 	                	 tips="不好意思系统繁忙，登记失败，请稍后再试！！";
 	    			     flag = 2;
 	                }
 	        		
 	        	}else{
 		           	 tips="不好意思系统繁忙，登记失败，请稍后再试！！";
 				     flag = 2;
 	            }
          },
        error : function()
        {
	    	 flag = true;
	  	     offset = $("#"+obj.id).offset();
			 showTipDiv("不好意思系统繁忙，请稍后再试！",offset.left,offset.top-35,3);	
			 return;	
        }
        });  

}




