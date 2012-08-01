<#macro head> 
<!-- 公用头部 开始-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="走秀网，全球品牌网络旗舰店，中国第一大国际时尚品牌线上零售机构。一折起销售正品国际品牌女装、男装、奢侈品、化妆品、运动、休闲、箱包、鞋、家居、配饰等商品，支持货到付款，7天无条件退换货保障。" />
<meta name="keywords" content="网上购物,网上商城,货到付款,名品打折网,奢侈品,LV,GUCCI,PRADA,CK,兰蔻,雅诗兰黛,倩碧,耐克,阿迪达斯,李宁,ZARA,百丽,走秀网,走秀网官网" />
<title>走秀网-全球品牌网络旗舰店：正品，支持货到付款，7天无条件退换货保障！</title>
	<#include "/cms/head.shtml">
	<#include "/common/taglibs.ftl" >
	<script type="text/javascript" src="${rc.getContextPath()}/resources/portal/js/product_detail_jump.js"></script>
	<script type="text/javascript" src="${rc.getContextPath()}/resources/portal/js/global.js"></script>
	<link href="${rc.getContextPath()}/resources/portal/css/default/global.css" rel="stylesheet" type="text/css">
	<link href="${rc.getContextPath()}/resources/portal/css/default/layout.css" rel="stylesheet" type="text/css">
	<link href="${rc.getContextPath()}/resources/portal/css/default/userCenter.css" rel="stylesheet" type="text/css">
	<link href="${rc.getContextPath()}/resources/portal/css/Validform.css"/>" rel="stylesheet" type="text/css">
	<link href="${rc.getContextPath()}/resources/portal/css/default/shopGlobal.css" rel="stylesheet" type="text/css">
	<link href="${rc.getContextPath()}/resources/portal/css/default/checkInfo.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-9540643-5']);
	   _gaq.push(['_addOrganic', 'soso', 'w']);
	   _gaq.push(['_addOrganic', 'youdao', 'q']); 
	   _gaq.push(['_addOrganic', 'baidu', 'word']);
	   _gaq.push(['_addOrganic', 'sogou', 'query']);
	   _gaq.push(['_setDomainName', '.xiu.com']);
	   _gaq.push(['_setAllowHash', false]); 
	   _gaq.push(['_trackPageview']);
	   _gaq.push(['_trackPageLoadTime']);

	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
<!-- 公用头部 结束-->
	<#nested/>
</#macro> 


<!-- 内容显示区-->
<#macro body>
    <#nested>
    <#include "/cms/static/web/M_footer.html">
</#macro>


<#macro foot>
<!-- 页脚 开始 -->
<!-- 页脚 结束 -->
	<script type="text/javascript" src="http://t.agrantsem.com/js/agwebtracking_224.js"></script>
	<script type="text/javascript">
		//百度统计
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fa7d8179b6c0f352569916cae8928627a' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script type="text/javascript">
		var mvHost=(("https:" == document.location.protocol) ? "https://secure." : "http://static.");
		document.write(unescape("%3Cscript src='" + mvHost + "mediav.com/mv.js' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script type="text/javascript">
		try{
		var _mvsite=new $mvt.$site("m-90-0");
		_mvsite.$logConversion();
		}catch(err){}
	</script>
	<script type="text/javascript" src="${rc.getContextPath()}/resources/thirdparty/wlySemForXiu.v2.0.js"></script>
	<#nested>
</html>
</#macro> 