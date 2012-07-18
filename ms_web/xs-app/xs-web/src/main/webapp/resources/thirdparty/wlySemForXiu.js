var wly_customer_name = 'xiu.com';
var wly_primary_domain = 'xiu.com';
var wly_conv_selectors = ['#user_buy_btn'];
var wly_goco_selectors = ['#submit_order_btn'];
var wly_conow_selectors = [];
var wly_tracedayssrc_days = 30

function wly_purify_sku_url() {
  return /http:\/\/[-.0-9a-zA-Z\/]+\/[^.]+.shtml$/.exec(location.href)[0];
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