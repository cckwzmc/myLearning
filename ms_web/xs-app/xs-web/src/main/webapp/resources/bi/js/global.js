// 封装函数
var $getId=function(o){var doc=document;return ('string'==typeof(o))?doc.getElementById(o):o;},$show=function(o){return o.style.display='block';},$hide=function(o){return o.style.display='none';},$setClassName=function(o,on,out){if(o.className==on){o.className=out}else{o.className=on}},$setDisplay=function(o,b,n){if(o.style.display==b){o.style.display=n}else{o.style.display=b}},$setTxt=function(o,b,n){if(o.innerHTML==b){o.innerHTML=n}else{o.innerHTML=b}};


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


/**
 * portal 公用的一些常量.
 */
var UrlPathUtil = {
		XIU_PORTAL_DOMAIN: 'portal.xiu.com',
		XIU_MY_DOMAIN: 'my.xiu.com',
		XIU_LOGIN_DOMAIN: 'login.xiu.com',
		XIU_SEARCH_DOMAIN: 'search.xiu.com',
		XIU_SEARCH_LIST_DOMAIN: 'list.xiu.com',
		XIU_HOME:'www.xiu.com',
		XIU_PORTAL_CONTEXTPATH:'/portal',
		XIU_WCS_LANGID:-7,
		XIU_WCS_STOREID:10154,
		XIU_WCS_CATALOGID:10101,
		
		getPortalCmdURL: function(cmdName) {
			return 'http://' +this.XIU_PORTAL_DOMAIN+'/business/'+cmdName+'?catalogId='+this.XIU_WCS_CATALOGID+'&langId='+this.XIU_WCS_LANGID+'&storeId='+this.XIU_WCS_STOREID;
		},
		
		getPortalDir: function() {
			return 'http://' +this.XIU_PORTAL_DOMAIN+'/business/';
		},
		
		getXiuCommandDir: function() {
			return '/business/';
		},
		
		getXiuPortalLoginUrl: function() {
			return 'https://' + this.XIU_LOGIN_DOMAIN + '/login';
		},

		getXiuPortalLoginDefUrl: function() {
			return 'https://' + this.XIU_LOGIN_DOMAIN + '/';
		},
		
		getXiuPortalRegUrl: function() {
			return 'https://' + this.XIU_LOGIN_DOMAIN + '/reg';
		},

		getXiuPortalRetrievePwdUrl: function() {
			return 'http://' + this.XIU_LOGIN_DOMAIN + '/retrivepwd';
		},
		
		getXiuPortalLoginActivateUrl: function(sn, userid) {
			sn = sn || '';
			userid = userid || '';
			return 'http://' + this.XIU_LOGIN_DOMAIN + '/activate?sn='+sn+'&userId='+userid;
		},

		getXiuPortalLoginResetPwdUrl: function(sn, userid) {
			sn = sn || '';
			userid = userid || '';
			return 'http://' + this.XIU_LOGIN_DOMAIN + '/resetpwd?sn='+sn+'&userId='+userid;
		},

		getXiuPortalHelpCenterUrl: function() {
			return 'http://' + this.XIU_PORTAL_DOMAIN + '/help/index.shtml';
		},

		getXiuPortalHelpProtocolUrl: function() {
			return 'http://' + this.XIU_PORTAL_DOMAIN + '/help/xieyi.shtml';
		},

		getXiuPortalMyHomeUrl: function() {
			return 'http://' + this.XIU_MY_DOMAIN + '/';
		},

		getXiuPortalMyFavoriteUrl: function() {
			return 'http://' + this.XIU_MY_DOMAIN + '/favorite';	
		},

	};
