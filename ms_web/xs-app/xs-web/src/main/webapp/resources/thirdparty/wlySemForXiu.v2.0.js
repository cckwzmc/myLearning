/*
(function(){
var wly_customer_name = 'xiu.com';
var wly_primary_domain = 'xiu.com';
var wly_conv_selectors = ['#user_buy_btn'];
var wly_goco_selectors = ['#submit_order_btn'];
var wly_conow_selectors = [];
var wly_tracedayssrc_days = 30

function wly_purify_sku_url() {
  return /http:\/\/[-.0-9a-zA-Z\/]+\/[^.]+.shtml/.exec(location.href)[0];
}

function wly_sku_recoed() {
  return 1;
}

function wly_sku_available() {
  return 1;
}

function wly_sku_page() {
  return (null != /product\/[0-9]+\.shtml/.exec(location.href));
}

function wly_cart_page() {
  return (null != /ConfirmOrder/.exec(location.href));
}

function wly_action_page() {
  return (null != /OrderSubmitFinishCmd/.exec(location.href));
}

function wly_sku_id() {
  var tmp = /product\/[0-9]+/.exec(location.href);
  if (tmp != null) {
    tmp = tmp[0].substring("product/".length, tmp[0].length);
    if (tmp) {
      return parseInt(tmp, 10);
    }
  }

  tmp = /id=[0-9]+/.exec(location.href);
  if (tmp != null) {
    return tmp[0].substring(3, tmp[0].length);
  }
  return '';
}


function wly_sku_detail() {
  if ($('#goodsInfoBox .p_title').length &&
      $('#Zoomer img').length &&
      $('#goodsInfoBox .col1 span:eq(0)').length &&
      $('#positionBox a').length &&
      /goods/.test($('#Zoomer img').attr('src'))) {
        var o = new Object;
        o.id = wly_sku_id();
        o.url = wly_purify_sku_url();
        o.title=$.trim($('#goodsInfoBox .p_title').text());
        o.img=$('#Zoomer img').attr('src');
        o.price = $.trim($('#goodsInfoBox .col1 span:eq(0)').text());
        o.orgprice= /[0-9.]+/.exec($('#goodsInfoBox .col1 span:eq(1)').text())[0];
        o.available = wly_sku_available();
        o.recoed = wly_sku_recoed();
        o.category = '';
        $('#positionBox a').each(function() {
          o.category += $(this).text() + '\t';
        });
        return o;
  }
  return null;
}


function wly_orderlist() {
  var orders = new Object();
  orders.list = new Array();
  orders.total_price = /[.0-9]+/.exec($('span#show_price_now').text())[0];
  $('table.tab_list tbody tr').each(function(i) {
      if ($(this).children('td').length == 0) return;
    
      var o = new Object;
      var tmp = $(this).children('td:eq(1)').find('a').attr('href');
      tmp = /\d+\.shtml/.exec(tmp)[0];
      tmp = /\d+/.exec(tmp)[0];
      o.id = parseInt(tmp, 10);
      o.orgprice = $.trim( $(this).children('td:eq(2)').text() );
      o.price = $.trim( $(this).children('td:eq(4)').find('font').text() );
      o.count = $.trim( $(this).children('td:eq(5)').text() );
    
      orders.list.push(o);
    });
  
  // orders.coway = $.trim($('#selectPay_idv').text());
  return orders;
}

function wly_actinfo() {
  var o = new Object();
  o.acttype='checkout';
  o.orderid=$.trim($('div.form_suc font.cor:eq(0)').text());
  // o.coway=$('dd:first table tr:last td:eq(2)').text();
  o.price=$.trim($('div.form_suc font.cor:eq(1)').text());
  return o;
}











//var wly_server_addr  = 'http://120.132.144.11/';
var wly_server_addr=(("https:" == document.location.protocol) ? "https://120.132.144.11/" : "http://120.132.144.11/");
var wly_crawler_serv = wly_server_addr + 'data?req=sku';
var wly_order_serv   = wly_server_addr + 'data?req=order';
var wly_conv_serv    = wly_server_addr + 'data?req=conv';
var wly_error_serv   = wly_server_addr + 'data?req=error';
var wly_reco_serv    = wly_server_addr + 'tuijian?req=reco';
var wly_cobuy_serv   = wly_server_addr + 'cobuy?req=reco';
var wly_trace_serv   = wly_server_addr + 'data?req=trace';
var wly_actsuc_serv  = wly_server_addr + 'data?req=actsuc';
var wly_cid_serv     = wly_server_addr + 'wlycid?req=cid';
var wly_sid = '';


(function($){var escapeable=/["\\\x00-\x1f\x7f-\x9f]/g,meta={'\b':'\\b','\t':'\\t','\n':'\\n','\f':'\\f','\r':'\\r','"':'\\"','\\':'\\\\'};$.toJSON=typeof JSON==='object'&&JSON.stringify?JSON.stringify:function(o){if(o===null){return'null';}
var type=typeof o;if(type==='undefined'){return undefined;}
if(type==='number'||type==='boolean'){return''+o;}
if(type==='string'){return $.quoteString(o);}
if(type==='object'){if(typeof o.toJSON==='function'){return $.toJSON(o.toJSON());}
if(o.constructor===Date){var month=o.getUTCMonth()+1,day=o.getUTCDate(),year=o.getUTCFullYear(),hours=o.getUTCHours(),minutes=o.getUTCMinutes(),seconds=o.getUTCSeconds(),milli=o.getUTCMilliseconds();if(month<10){month='0'+month;}
if(day<10){day='0'+day;}
if(hours<10){hours='0'+hours;}
if(minutes<10){minutes='0'+minutes;}
if(seconds<10){seconds='0'+seconds;}
if(milli<100){milli='0'+milli;}
if(milli<10){milli='0'+milli;}
return'"'+year+'-'+month+'-'+day+'T'+
hours+':'+minutes+':'+seconds+'.'+milli+'Z"';}
if(o.constructor===Array){var ret=[];for(var i=0;i<o.length;i++){ret.push($.toJSON(o[i])||'null');}
return'['+ret.join(',')+']';}
var name,val,pairs=[];for(var k in o){type=typeof k;if(type==='number'){name='"'+k+'"';}else if(type==='string'){name=$.quoteString(k);}else{continue;}
type=typeof o[k];if(type==='function'||type==='undefined'){continue;}
val=$.toJSON(o[k]);pairs.push(name+':'+val);}
return'{'+pairs.join(',')+'}';}};$.evalJSON=typeof JSON==='object'&&JSON.parse?JSON.parse:function(src){return eval('('+src+')');};$.secureEvalJSON=typeof JSON==='object'&&JSON.parse?JSON.parse:function(src){var filtered=src.replace(/\\["\\\/bfnrtu]/g,'@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,']').replace(/(?:^|:|,)(?:\s*\[)+/g,'');if(/^[\],:{}\s]*$/.test(filtered)){return eval('('+src+')');}else{throw new SyntaxError('Error parsing JSON, source is not valid.');}};$.quoteString=function(string){if(string.match(escapeable)){return'"'+string.replace(escapeable,function(a){var c=meta[a];if(typeof c==='string'){return c;}
c=a.charCodeAt();return'\\u00'+Math.floor(c/16).toString(16)+(c%16).toString(16);})+'"';}
return'"'+string+'"';};})(jQuery);

if ( ! window['JSON']) {
    JSON = {};
    JSON.stringify = $.toJSON;
}

function wly_setcookie( name, value, expires, path, domain, secure ) {
  var today = new Date();
  today.setTime( today.getTime() );
  if ( expires ) {
    expires = expires * 1000 * 60 * 60 * 24;
  }
  var expires_date = new Date( today.getTime() + (expires) );
  document.cookie = name+'='+escape( value ) +
      ( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) + //expires.toGMTString()
      ( ( path ) ? ';path=' + path : '' ) +
      ( ( domain ) ? ';domain=' + domain : '' ) +
      ( ( secure ) ? ';secure' : '' );
}

function wly_getcookie(name)  {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for(var i=0;i < ca.length;i++)  {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1,c.length);
    if (c.indexOf(nameEQ) == 0)
      return c.substring(nameEQ.length,c.length);
  }
  return null;
}

function wly_cid() {   
  var id = wly_getcookie('wlycid');
  return (id == null ? '': id);
}

function wly_uid() {
  var id = wly_getcookie('wlyuid');
  return (id == null ? '': id);
}

function wly_report_error(e, funcname) {
  var req_url = wly_error_serv
      + '&errsrc='   + funcname
      + '&wlycid=' + wly_cid()
      + '&wlyuid='  + wly_uid()
      + '&customername=' + wly_customer_name
      + '&url='    + encodeURIComponent(location.href)
      + '&refer='  + encodeURIComponent(document.referrer)
      + '&trasrc=' + wly_trasrc()
      + '&broinfo=' + encodeURIComponent(navigator.appVersion)
      + '&edesc=' + encodeURIComponent(e.toString())
      + '&tracedayssrc=' + wly_tracedayssrc();
  
  $.getScript(req_url);
}

function wly_craw_actinfo() {
  var o = wly_actinfo();
  var req = wly_actsuc_serv 
      + '&acttype=' + o.acttype
      + '&actprice=' + o.price
      + '&actinfo=' + encodeURIComponent(JSON.stringify(o))
      + '&wlycid=' + wly_cid()
      + '&wlyuid='  + wly_uid()
      + '&customername=' + wly_customer_name
      + '&url=' + encodeURIComponent(location.href)
      + '&refer=' + encodeURIComponent(document.referrer)
      + '&trasrc=' + wly_trasrc()
      + '&tracedayssrc=' + wly_tracedayssrc();
  
  $.getScript(req);
}

function wly_validate_sku(item) {
    if (item == null ) {
	return false;
    } else if (item.img == null || item.price == null || item.title == null || item.url == null) {
	return false;
    } else if (item.img.length == 0 || item.price.length == 0 ||item.title.length == 0 || item.url.length == 0) {
	return false;
    } else {
	return true;
    }
}

function wly_craw_sku() {
  window.wly_get_detail_values_handler = setInterval(function() {
    var o = wly_sku_detail();
    if (o) {
      if (!wly_validate_sku(o)) {
    	  wly_report_error('','invalidate sku info');
      } else if (o.title == null || o.price == null || o.img == null) {
        wly_report_error('', 'cannot get sku info');
      } else {
        var sku_detail_str = encodeURIComponent(JSON.stringify(o));
        var req_url = wly_crawler_serv
            + '&skuid='  + wly_sku_id()
            + '&detail=' + sku_detail_str
            + '&recoed=' + o.recoed
            + '&wlycid=' + wly_cid()
            + '&wlyuid='  + wly_uid()
            + '&customername=' + wly_customer_name
            + '&url='    + encodeURIComponent(o.url)
            + '&refer='  + encodeURIComponent(document.referrer);
        $.getScript(req_url);
      }
      clearInterval(window.wly_get_detail_values_handler);
    }
  }, 1000);
}


function wly_prepare_recodata(data) {
  wly_sid = data.sid;
  recoitems = new Array();
  for (var i = 0; i < data.items.length-1; ++i) {
    var item = data.items[i];
    if (false == wly_validate_sku(item)) {
      continue;
    }

    if (item.url.indexOf('?') != -1) {
      item.url += '&';
    } else {
      item.url += '?';
    }
    
    item.url += ('wlyid=' + wly_sid);    
    recoitems.push(item);
  }
  
  return recoitems;
}

function wly_get_reco_result(callback, item_req, type) {
  var req_url = wly_reco_serv
      + '&trigger=' + wly_sku_id()
      + '&skuid='    + wly_sku_id()
      + '&wlycid='   + wly_cid()      
      + '&customername=' + wly_customer_name
      + '&url='      + encodeURIComponent(location.href)
      + '&refer='    + encodeURIComponent(document.refer)
      + '&trasrc=' + wly_trasrc()
      + '&type='     + type
      + '&wlyuid='    + wly_uid()
      + '&callback=' + callback      
      + '&item_req=' + item_req
      + '&recotype=coview'
      + '&tracedayssrc=' + wly_tracedayssrc();
  $.getScript(req_url);
}

function wly_get_cobuy_result(callback, item_req, type) {
  var req_url = wly_cobuy_serv
      + '&trigger=' + wly_sku_id()
      + '&skuid='    + wly_sku_id()
      + '&wlycid='   + wly_cid()      
      + '&customername=' + wly_customer_name
      + '&url='      + encodeURIComponent(location.href)
      + '&refer='    + encodeURIComponent(document.refer)
      + '&trasrc='   + wly_trasrc()
      + '&type='     + type
      + '&wlyuid='    + wly_uid()
      + '&callback=' + callback
      + '&item_req=' + item_req
      + '&recotype=cobuy'
      + '&tracedayssrc=' + wly_tracedayssrc();
  $.getScript(req_url);
}

function wly_craw_order() {
  var order = wly_orderlist();
  var req_url = wly_order_serv 
      + '&orderlist=' +encodeURIComponent(JSON.stringify(order.list))
      + '&totalprice=' + order.total_price
      + '&wlycid=' + wly_cid()
      + '&wlyuid='  + wly_uid()      
      + '&customername=' + wly_customer_name
      + '&url='    + encodeURIComponent(location.href)
      + '&refer='  + encodeURIComponent(document.referrer)
      + '&trasrc=' + wly_trasrc()
      + '&tracedayssrc=' + wly_tracedayssrc();
  $.getScript(req_url);
}

function wly_trace() {
  var req_url = wly_trace_serv
      + '&wlycid=' + wly_cid()
      + '&wlyuid='  + wly_uid()
      + '&customername=' + wly_customer_name
      + '&url='    + encodeURIComponent(location.href)
      + '&refer='  + encodeURIComponent(document.referrer)
      + '&trasrc=' + wly_trasrc()
      + '&tracedayssrc=' + wly_tracedayssrc();
  $.getScript(req_url);
}

function wly_conv_monitor() {
  for (var i= 0; i < wly_conv_selectors.length; ++i) {
    var selector = wly_conv_selectors[i];
    $(selector).click(function(event) {
        try {
          var req_url = wly_conv_serv
              + '&wlycid=' + wly_cid()
              + '&wlyuid=' + wly_uid()
              + '&customername=' + wly_customer_name
              + '&url=' + encodeURIComponent(location.href)
              + '&refer=' + encodeURIComponent(document.referrer)
              + '&trasrc=' + wly_trasrc()
              + '&tracedayssrc=' + wly_tracedayssrc();
          
          $.getScript(req_url);
        } catch(e) {
          wly_report_error(e, 'wly_conv_monitor');
        }
      });
  }
}

function wly_trasrc() {
  return wly_getcookie("wly_trasrc") == null ? '' : wly_getcookie("wly_trasrc");
}

function wly_tracedayssrc() {
  return wly_getcookie("wly_tracedayssrc") == null ? '' : wly_getcookie("wly_tracedayssrc");
}

function wly_check_wlycid() {
  if (wly_getcookie('wlycid') == null) {
    $.getScript(wly_cid_serv + '&url=' + encodeURIComponent(location.href));
  }
}

function wly_check_wlyuid() {
  if (wly_getcookie('wly_uid') == null) {
    if (/[\&\?]wlyid=[0-9a-fA-F]+-[0-9a-fA-F]+/.test(location.href)) {
      var tmp = /[\&\?]wlyid=[0-9a-fA-F]+-[0-9a-fA-F]+/.exec(location.href)[0];
      var uid = tmp.split('-')[1];
      wly_setcookie("wly_uid", uid, 0, '/', '.' + wly_primary_domain);
    }
  }
}

function wly_update_traffic_source() {
  if (null == wly_getcookie("wly_trasrc")) {
    wly_setcookie("wly_trasrc", encodeURIComponent(document.referrer+'&&'+document.location.href), 0, '/', '.'+wly_primary_domain);
  } 
}

function wly_update_tracedayssrc() {
  if (null == wly_getcookie("wly_tracedayssrc") || ! /xiu\.com/.test(document.referrer)) {
    wly_setcookie("wly_tracedayssrc", encodeURIComponent(document.referrer+'&&'+document.location.href), wly_tracedayssrc_days, '/', '.'+wly_primary_domain);
  }
}

function wly_on_detail_page() {
  try {
    wly_craw_sku();
    wly_conv_monitor();
  } catch (e) {
    wly_report_error(e, 'wly_on_detail_page');
  }
}

function wly_on_cart_page() {
  try {
    for (var i = 0; i < wly_goco_selectors.length; ++i) {
      var selector = wly_goco_selectors[i];
      $(selector).click(function(event) {
          try {
            wly_craw_order();
          } catch (e) {
            wly_report_error(e, 'goco');
          }
        });
    }
  } catch (e) {
    wly_report_error(e, 'wly_on_cart_page');
  }
}

function wly_on_registry_page() {
  try {
  } catch(e) {
    wly_report_error(e, 'wly_on_registry_page');
  }
}


function wly_mon_actsuc() {
  try {
    wly_craw_actinfo();
  } catch(e) {
    wly_report_error(e, 'wly_send_sucact');
  }
}

function wly_dispatch() {
  try {
    if (wly_sku_page()) {
      wly_on_detail_page();
    } else if (wly_cart_page()) {
      wly_on_cart_page();
    } else if (wly_action_page()) {
      wly_mon_actsuc();
    }
  } catch(e) {
    wly_report_error(e, 'wly_dispatch');
  }
}
    
$(function() {
    try {
      wly_check_wlycid();
      wly_check_wlyuid();
      wly_update_traffic_source();
      wly_update_tracedayssrc();
      wly_trace();
      wly_dispatch();
    } catch (e) {
      wly_report_error(e, 'tratrace');
    }
  });
})();
*/