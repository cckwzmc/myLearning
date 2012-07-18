function cancleflow(obj1,obj2){
	
	var tips="";  
	var flag = false;
	
	var tips="";  
	var flag = false;
	
     jQuery.ajax({
            type : "GET",
            url : UrlPathUtil.XIU_PORTAL_CONTEXTPATH+"/returnChangeList/destory/"+obj1 + "?format=json",
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
                                	
//                                	$("#"+obj2.id).attr("disabled", true);
//                                	 document.mainForm.submit();
//                                	 return;
                                	 window.location.href="http://localhost:8888/portal/goodsRefund/list";
								   
                               }else{
                            	   tips ="系统繁忙，取消申请没有成功，请稍后再试！！";
								   flag = false;
                               }
                           }
                        }
                    }
                    offset = $("#"+obj2.id).offset();
        	        showTipDiv(tips,offset.left-20,offset.top-50,2);
        	        return ;
              },
             error : function()
             {
            	 flag = true;
          	     offset = $("#"+obj2.id).offset();
				 showTipDiv("不好意思系统繁忙，请稍后再试！",offset.left,offset.top-35,3);	
				 return;	
             }
       }); 
}