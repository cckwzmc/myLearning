<!DOCTYPE html >
<html>
<head>	
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<head>		
<meta name="description" content="走秀网，全球品牌网络旗舰店，中国第一大国际时尚品牌线上零售机构。一折起销售正品国际品牌女装、男装、奢侈品、化妆品、运动、休闲、箱包、鞋、家居、配饰等商品，支持货到付款，7天无条件退换货保障。" />
<meta name="keywords" content="网上购物,网上商城,货到付款,名品打折网,奢侈品,LV,GUCCI,PRADA,CK,兰蔻,雅诗兰黛,倩碧,耐克,阿迪达斯,李宁,ZARA,百丽,走秀网,走秀网官网" />
<title>走秀网-全球品牌网络旗舰店：正品，支持货到付款，7天无条件退换货保障！</title>
<link href="${rc.getContextPath()}/resources/portal/css/default/global.css" rel="stylesheet" type="text/css">
<link href="${rc.getContextPath()}/resources/portal/css/default/layout.css" rel="stylesheet" type="text/css">
<style type="text/css">
.bfd_img_logo{float:right;margin-bottom:8px; text-align:right; height:13px; width:100px; border:0; background-image:url(images/logo.gif); display:block;}
</style>

<script type="text/javascript" src="${rc.getContextPath()}/resources/portal/js/global.js"></script>

</head>
<body>
<#include "/common/myxiu_head.ftl" >
<!-- 用户中心 开始-->
<div class="userCenterDiv rela">
<div class="case">你的位置：<a href=" %>">我的走秀</a></div>
  <!--左边我的走秀 开始-->
   <#include "/common/myxiu_menu.ftl" >
  <!--左边我的走秀 结束-->
  
  
  <!--右边模块 开始-->
   <#include "/cms/user/index_right.shtml">
  <!--右边模块 结束-->

  <div class="user_fristConetents user_w">
    <h2 class="user_welInfo"><span title="">admin   欢迎您！</span><span class="user_showloginTime">上一次登录时间：2012-03-17</span></h2>
    <div class="user_order_detaillbi">
      <dl class="u_order_dl">
        <dt>订单提醒：</dt>
        <dd><a href="" hidefocus="true">待处理订单（<span>a1</span>）</a></dd>
        <dt>我的收藏：</dt>
        <dd><a href=""  hidefocus="true">降价商品（<span>a2</span>）</a>
        	<a href=""  hidefocus="true">促销商品（<span>a3</span>）</a>
        	<a href=""  hidefocus="true">新到货商品（<span>a4</span>）</a></dd>
        <dt>我的消息：</dt>
        <dd><a href="" hidefocus="true">短消息（<span>6条未读</span>）</a>
        	<a href="" hidefocus="true">咨询回复（<span>7条未读</span>）</a></dd>
        <dt>账户余额：</dt>
        <dd style="word-break : keep-all; overflow: hidden;">
        	<a href="" hidefocus="true"><span class="y_money"><fmt:formatNumber value="90 * 0.01}" pattern="0.00" /></span></a>
            <a href="" hidefocus="true" style="width: 240px">我的积分：<span  style="color:#f8242f; font-weight:bold;">98</span></a>
        </dd>
        <dt>优惠券/卡：</dt>
        <dd><a href=""  hidefocus="true"><span>6</span></a></dd>
      </dl>
    </div>
    <div class="userBrowseGoods">
      
      <div id="banner6_UserHistory">
      <!-- 
      <h2>您可能对以下商品感兴趣</h2>
      <ul class="Browse_ul">
        <li> <a href="#" class="Bro_GoodsImg"><img src="http://ui.xiu.com/static/img/adv/user_c_i_27.jpg" /></a>
          <p class="Bro_txt"><a href="#">春装新款正品纯 舒适自然... </a></p>
          <b class="bro_money">435</b> </li>
        <li> <a href="#" class="Bro_GoodsImg"><img src="http://ui.xiu.com/static/img/adv/user_c_i_27.jpg" /></a>
          <p class="Bro_txt"><a href="#">春装新款正品纯 舒适自然... </a></p>
          <b class="bro_money">435</b> </li>
        <li> <a href="#" class="Bro_GoodsImg"><img src="http://ui.xiu.com/static/img/adv/user_c_i_27.jpg" /></a>
          <p class="Bro_txt"><a href="#">春装新款正品纯 舒适自然... </a></p>
          <b class="bro_money">435</b> </li>
        <li> <a href="#" class="Bro_GoodsImg"><img src="http://ui.xiu.com/static/img/adv/user_c_i_27.jpg" /></a>
          <p class="Bro_txt"><a href="#">春装新款正品纯 舒适自然... </a></p>
          <b class="bro_money">435</b> </li>
        <li> <a href="#" class="Bro_GoodsImg"><img src="http://ui.xiu.com/static/img/adv/user_c_i_27.jpg" /></a>
          <p class="Bro_txt"><a href="#">春装新款正品纯 舒适自然... </a></p>
          <b class="bro_money">435</b> </li>
      </ul>
       -->
      </div>   
      <h2>最近浏览的商品</h2>
      <ul class="Browse_ul">
      	
	<li> <a target="_blank"  href="" class="Bro_GoodsImg"><img src="" onerror="this.src='http://www.xiu.com//static/img/default/default.50_50.jpg'"/></a>
	  <p class="Bro_txt"><a target="_blank"  href="" onerror="this.src='http://www.xiu.com/static/img/default/default.50_50.jpg'">test</a></p>
	  <b class="bro_money">wewe</b> </li>

      </ul>
    </div>
  </div>
  <!--内容显示区 结束-->
  <span  class="sl"></span> <span  class="rs"></span> 
  
</div>
<!-- 用户中心 结束-->
<!-- 页脚 开始 -->
 	 <#include "/common/myxiu_footer.ftl" >
<!-- 页脚 结束 -->
<!-- 用户反馈 开始 -->
<#include "/resources/advice/improveComment.ftl" >
<!-- 用户反馈 结束 -->
</body>

<!--百分点 JS API-->
<script type="text/javascript">
var $getId=function(o){return('string'==typeof(o))?document.getElementById(o):o;},$show=function(o){return o.style.display='block';},$hide=function(o){return o.style.display='none';},$setClassName=function(o,on,out){if(o.className==on){o.className=out}else{o.className=on}},$setDisplay=function(o,b,n){if(o.style.display==b){o.style.display=n}else{o.style.display=b}},$setTxt=function(o,b,n){if(o.innerHTML==b){o.innerHTML=n}else{o.innerHTML=b}},$getEvent =function(event){return event || window.event;},$getTarget=function(){return $getEvent(e).target || $getEvent(e).scrElement;},forEach=function(a,fun ){if(a.forEach){a.forEach(fun);}else{for(var i=0,len=a.length;i<len;i++){fun.call(this, a[i],i);}}},attachEvent=function(elements,Event,fun){if(elements.addEventListener){elements.addEventListener(Event,fun,false);}else if(elements.attachEvent){elements.attachEvent('on'+Event,fun);}else{elements['on'+Event]=fun;}},setStyle=function(o,atrr,val){return o.style[atrr]=val;},getcurStyle=function(e){return e.currentStyle || document.defaultView.getComputedStyle(e,null);},$createElements=function(o){return document.createElement(o);},
$setcss=function(o,arr){for(var i in arr){setStyle(o,i,(parseInt(arr[i])+'px'));}},
$Show_tallyDiv_info = function(tag, Module, cls) {
	var tag = $getId(tag).getElementsByTagName("span")[0],
	module = $getId(Module),
	cls = $getId(cls);
	attachEvent(tag,'click',function(){
		var span = module.getElementsByTagName("span")[0] ;
		$setDisplay(module, 'block', 'none');
		$setClassName(tag, 'on', 'out');
		attachEvent(span,'click',function(){
			$hide(module);
			tag.className = 'out';
		});
	});
},$Showmodules = function(clickElems, showElemes) {
	var clickElem = $getId(clickElems),
	showEleme = $getId(showElemes);
	var ElemensgetA = clickElem.getElementsByTagName("a")[0];
	attachEvent(ElemensgetA,'click',function(){
		$setDisplay(showEleme, 'none', 'block');
		$setClassName(ElemensgetA, 'on', 'out');
	});
};

//用户中心
function listContrls(id,tabs){
	this.id = $getId(id);
	this.tabsId = $getId(tabs);
	this.contrlTabs();
}

listContrls.prototype ={
	//显隐菜单
	contrlTabs:function(){
		var getelementsid = this.id ,elmentslenths;
		var cantrlselementsdt = getelementsid.getElementsByTagName("dt");
		var cantrlselementsdd = getelementsid.getElementsByTagName("dd");
		for(elmentslenths = cantrlselementsdt.length -1 ; elmentslenths >=0; elmentslenths --){
			var elementscreatespan = document.createElement("span");elementscreatespan.className = 'up';
			cantrlselementsdt[elmentslenths].appendChild(elementscreatespan);
			(function(elmentslenths,elementscreatespan){
				var elements_dts = cantrlselementsdt[elmentslenths];
				elements_dts.onclick = function(){
					$setDisplay(cantrlselementsdd[elmentslenths],'none','block');
					$setClassName(elementscreatespan,'up','down');
				}
			})(elmentslenths,elementscreatespan)
		}
	},
	//tabs切换
	variableTabs:function(elementsEvents){
		var variableTabs = this.tabsId,variable,visilength,visi=0;
		var variableTabsGetelementsli = variableTabs.getElementsByTagName("li");
		for(variable = variableTabsGetelementsli.length -1 ;variable >= 0 ; variable --){
			(function(variable,visilength,visi){
				var variableTabsGetelementslis = variableTabsGetelementsli[variable];
				var visibilitydl = $getId(variableTabs.id+variable);
				var visibilitydt = visibilitydl.getElementsByTagName("dt");
				for(visilength = visibilitydt.length-1;visilength>= 0 ;visilength--){
					(function(visilength){
						var visibilitydts = visibilitydt[visilength].getElementsByTagName("a")[visi];
						visibilitydts['on'+elementsEvents] = function(){
							var visibilitydd = visibilitydl.getElementsByTagName("dd")[visilength]
							$setDisplay(visibilitydd,'none','block');
							$setTxt(this,'\u6536\u8d77','\u5c55\u5F00');
						}
					})(visilength);
				}
				variableTabsGetelementslis['on'+elementsEvents] = function(){
					var visibilityElementsDiv = $getId(variableTabs.id+variable);
					listContrls.prototype.visibilityTabs(variableTabsGetelementsli,variableTabs,visi);
					this.getElementsByTagName("a")[0].className ='on';
					$show(visibilityElementsDiv);
				}
			})(variable,visilength,visi);
		}
	},
	visibilityTabs:function(variableTabsGetelementsli,variableTabs,visi){
		for(var variable = variableTabsGetelementsli.length -1 ;variable >= 0 ; variable --){
			var visibilityElementsDiv = $getId(variableTabs.id+variable);
			variableTabsGetelementsli[variable].getElementsByTagName("a")[visi].className ='';
			$hide(visibilityElementsDiv);
		}
	}
}
var it = new listContrls("listContrl",'tabs');
it.variableTabs('click');
</script>
<script type="text/xjs">
bfdload();
function bfd(){

		window.bfd_onload = bfdStart();	
	}
	
/********** 向百分点推荐引擎提交推荐请求 **********/
function bfdload(){

	if(!document.getElementById('banner6_UserHistory')) return false;
	try{
		
		XJS.execScript("http://www.baifendian.com/api/js/bfd-jsapi-3.0.min.js", bfd);
	}catch(e){
			XJS.dprint('百分点接口有误');
			}
}

function bfdStart(){
	// "public2"是百分点推荐引擎公开的演示账户，在正式使用时请替换为您的账户名称
	var client = new brs.Client($('#bfdAccount').attr("value"));//bfdtest207
	var p = new brs.PackedRequest();	
	// 设置要返回的商品的个数
	var item_num=5;
	// 获取当前用户的user_id，字符串类型
	var user_id = $.cookie('xiu.login.userId')!= null ? $.cookie('xiu.login.userId') :'';
	// 获取当前用户的session_id，字符串类型
	var session_id = $.cookie('sessionID');
	//var user_id = "bfd_test_user1";
	//var session_id = "session1";
	// 如果当前用户是匿名用户（判断匿名用户的逻辑，需要您按照自己网站的特点进行修改），其user_id用session_id代替
	if (typeof(user_id) == 'undefined' || user_id == '' ){
		user_id = session_id;
	}

	/********** 向百分点推荐引擎请求推荐结果(推荐请求部分) **********/
	p.recUH = new brs.RecByUserHistory(user_id, session_id,item_num);
	client.invoke(p, "cb_recommend");

}

// 回调函数：用于处理推荐请求的返回结果。即,将推荐结果展示在推荐栏中，您可以根据需要修改
function cb_recommend(json) {
	
	var result_UH = json.recUH;
		var code_UH = result_UH[0];
		if (code_UH == 0) {
			var req_id_UH = result_UH[1];
			var item_info_UH= result_UH[2];
			if (Object.prototype.toString.apply(item_info_UH) === '[object Array]') {
				var obj_UH = document.getElementById("banner6_UserHistory");
				onGetGoodsInfo(item_info_UH, obj_UH,req_id_UH);
			}
		}
}
function onGetGoodsInfo(data_all, obj,rec_id){
	var html = '<h2>您可能感兴趣的商品</h2>';
	var ul = document.createElement("ul");
	ul.className = "Browse_ul";
	obj.innerHTML=html;
	obj.appendChild(ul);
	var show_num=(data_all.length>5)?5:data_all.length;
	for(i=0;i<show_num;i++){
		template(data_all[i],ul,rec_id)
	}
	var bfd_logo=document.createElement("a");
	bfd_logo.href="http://www.baifendian.com";
	bfd_logo.target="_blank";
	bfd_logo.className="bfd_img_logo";
	obj.appendChild(bfd_logo);
}
function template(data_one, ul,rec_id){
	var li = document.createElement("li");
	html='';
	html += '<a class="Bro_GoodsImg" href="'+data_one[2]+'?req_id='+rec_id+'" target="_blank"><img src="'+data_one[3]+'" onerror="this.src=\'http://www.xiu.com/static/img/default/default.50_50.jpg\'"/></a>';
	html += '<p class="Bro_txt"><a href="'+data_one[2]+'?req_id='+rec_id+'" target="blank">'+data_one[1]+'</a></p>';
	html += '<b class="bro_money">'+data_one[4]+'</b>';
	li.innerHTML = html;
	ul.appendChild(li);
}
</script>

</html>

