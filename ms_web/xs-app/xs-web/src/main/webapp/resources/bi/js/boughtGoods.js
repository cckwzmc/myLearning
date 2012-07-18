function shareProduct(goodsId,goodsSn,selltype){
    window.open(UrlPathUtil.getPortalCmdURL("shareCmd") + "&goodsSn="+goodsSn+"&selltype="+selltype+"&goodsId="+goodsId,"_blank");
}

//加入购物袋
function add_order(goods_sn,sku_id,sku_code,obj){    
    var offset = $(obj).offset();        
    var url = UrlPathUtil.getPortalCmdURL("XOrderItemAddCmd")+"&sellType=5&method=new&goods_sn=" + goods_sn;
        url = url+'&inputQuantity=1&catentryId='+sku_id+'&part_number='+sku_code+'&jsoncallback=?';      
    $.ajax({
         type: "POST",
         url: url,
         dataType: 'jsonp',
         jsonp: 'jsoncallback',
         success: function(result) {
            var data=result[0];            
             if(data.failure != null){              
                showTipDiv("出错了，错误信息："+data.failure,offset.left-40,offset.top-48,3);
             }else if(data.successful != null){             
                showTipDiv(data.successful,offset.left-40,offset.top-48,3);
                window.top.location.href=UrlPathUtil.getPortalCmdURL("XOrderItemDisplayCmd")+'&sellType=5';
             }
         },
         error : function() {
 		 }
    });
    
}

//加入收藏
function addInterest(catentryId,obj){
	var url =UrlPathUtil.getPortalCmdURL("XInterestItemAddCmd") +"&catentryId="+catentryId+"&jsoncallback=?";
	alert(url);
    $.ajax({
        type: 'POST',
        url : url,
        dataType: 'jsonp',
        jsonp: 'jsoncallback',
        success: function (data) {
            var str = data[0];
            if(str.seccessfull=='Y'){
                //alert("已成功加入收藏夹");
                var offset = $(obj).offset();
                showTipDiv('加入收藏夹成功!',offset.left-40,offset.top-48,3);
            } else{
                //alert(data.message);
                var offset = $(obj).offset();
                showTipDiv(str.message,offset.left-40,offset.top-48,3);
            }
        },
        error : function() {
        	showTipDiv('加入收藏夹失败!',offset.left-40,offset.top-48,3); 
        }
    });
    
}