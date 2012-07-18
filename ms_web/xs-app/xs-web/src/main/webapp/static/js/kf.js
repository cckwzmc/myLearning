document.write("<style type=\"text\/css\">");
document.write("html{_background-attachment:fixed;_background-image:url(n1othing.txt)}");
document.write(".kfwrap{position:fixed;left:-1px;bottom:30px;_position:absolute;_top:expression(documentElement.scrollTop+documentElement.clientHeight-188);_bottom:auto;height:158px;}");
document.write(".kf{background:url(http://static.zongheng.com/v2_0/images/kf/kftop.gif) no-repeat;width:118px;padding-top:55px;position:relative;display:none;}");
document.write(".kf em{position:absolute;display:block;width:15px;height:15px;left:96px;top:2px;cursor:pointer;}");
document.write(".kfc{background:url(http://static.zongheng.com/v2_0/images/kf/kfre.gif) repeat-y;padding-left:22px;}");
document.write(".kfc img{margin-top:5px;float:none;}");
document.write(".kfbot{background:url(http://static.zongheng.com/v2_0/images/kf/kfbot.gif) no-repeat;height:21px;width:118px;}");
document.write("<\/style>");
document.write("<div class=\"kfwrap\">");
document.write("<img id=\"kftp\" src=\"http://static.zongheng.com/v2_0/images/kf/zxkf.gif\" class=\"fl\" \/>");
document.write("<div class=\"kf fl\">");
document.write("<em></em>");
document.write("<div class=\"kfc\">");
document.write("<a id=\"qqbtn\" href=\"http://wpa.qq.com/msgrd?v=1&uin=965151179&site=qq&menu=yes\" onclick=\"return false;\"><img border=\"0\" src=\"http://wpa.qq.com/pa?p=2:965151179:41 &r=0.5382125197772324\" alt=\"点击这里给我发消息\" title=\"点击这里给我发消息\"></a>");
document.write("<a href=\"http://bbs.zongheng.com/forumdisplay.php?fid=22\" target=\"_blank\"><img src=\"http://static.zongheng.com/v2_0/images/kf/lt.jpg\" \/><\/a>");
document.write("<a href=\"mailto:zhkf@wanmei.com\" target=\"_blank\"><img src=\"http://static.zongheng.com/v2_0/images/kf/yx.jpg\" \/><\/a>"); 
document.write("<\/div>");
document.write("<div class=\"kfbot\"><\/div>");
document.write("<\/div>");
document.write("<\/div>");
jQuery(function(){
	jQuery("#kftp").css("cursor", "pointer").css("padding-right", "1px");
	jQuery("#kftp").mouseover(function(){jQuery(".kf").show();});
	jQuery(".kf").mouseover(function(){jQuery(".kf").show();});

	jQuery(".kfwrap").mouseout(function(){jQuery(".kf").hide();});
	jQuery(".kf em").click(function(){jQuery(".kf").hide();});

	jQuery("#qqbtn").click(function(){

		var iframe = document.getElementById("qqiframe");

		if(iframe == null)
		{
			var html = "<iframe id=\"qqiframe\"" +
				" src=\"about:blank\"" +
				" style=\"width: 0px; height: 0px; border: none; display: none;\"" +
				" frameborder=\"0\" scrolling=\"no\"></iframe>";

			jQuery(document.body).append(html);
			iframe = document.getElementById("qqiframe");
		}

		if(iframe != null)
		{
			iframe.src = "http://wpa.qq.com/msgrd?v=1&uin=965151179&site=qq&menu=yes";
		}
	});
});