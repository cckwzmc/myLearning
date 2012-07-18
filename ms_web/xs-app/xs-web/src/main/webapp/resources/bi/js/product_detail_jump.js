function checkActivity(goodId, goodSn, selltype) {
	if(selltype==null||selltype==undefined||selltype==""){
		selltype='5';
	}
	var actionUrl = UrlPathUtil.getXiuCommandDir() +"PrdDetailURLProcessCmd?langId="
			+ storeIdInfo['langId'] + "&storeId=" + storeIdInfo['stordId']
			+ "&catalogId=" + storeIdInfo['catalogId'] + "&goodsId=" + goodId
			+ "&goodsSn=" + goodSn + "&selltype=" + selltype;
	$.ajax( {
				type : "POST",
				url : actionUrl,
				async : false,
				success : function(data) {
					if ($("#jump_url").length > 0) {
						$("#jump_url").remove();
					}
					var obj = $("<a  href='' target='_blank' id='jump_url' style='display:none'></a>");
					$('body').append(obj);
					if (data.url != "") {
						$("#jump_url").attr("href", data.url);
						if(document.all)   
					      {   
							  document.getElementById("jump_url").click();
					      }   
					      else   
					      {   
					          var evt = document.createEvent("MouseEvents");   
					          evt.initEvent("click", true, true);   
					          document.getElementById("jump_url").dispatchEvent(evt);   
					      }   
					}
				}
			});

}