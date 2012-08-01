<!--左边我的走秀 开始-->
  
<div class="my_contrlModules">
    <h3 class="my_zShowTitle"><a href=""  hidefocus="true">我的走秀<b></b></a></h3>
    ﻿<dl class="show_listContrl" id="listContrl">
  <dt>个人信息管理</dt>
  <dd>
<a href="${rc.getContextPath()}/user/showPersonInfo"  hidefocus="true">个人资料</a>
    <a href="${rc.getContextPath()}/user/modifyPassword"  hidefocus="true">修改密码</a>

    <a href="${rc.getContextPath()}/address/list"  hidefocus="true">收货地址</a>
  </dd>
  <dt>交易管理</dt>

  <dd>
  	<a href="${rc.getContextPath()}/order/list?order" hidefocus="true"> 订单管理</a>
    <a href="${rc.getContextPath()}/boughtGoods/list" hidefocus="true"> 购买过的商品</a>
    <!--<a href="order_center.shtml" target="ifm" hidefocus="true"> 订单中心</a>-->
    <!--<a href="javascript:void(0);"  hidefocus="true"> 在线支付</a>-->
  </dd>
  <dt>账户管理</dt>

  <dd>
   	<a href="${rc.getContextPath()}/virtualAccount/list?queryRecord=AccountChangeRecord&condition=1" hidefocus="true">虚拟帐户管理</a>
    <a href=""  hidefocus="true"> 优惠卡/劵</a>
    <a href="${rc.getContextPath()}/point/showMyPoint" hidefocus="true"> 我的积分</a>
    <a href="" hidefocus="true"> 我的礼品卡</a>

  </dd>
  <dt>购物管理</dt>
  <dd>
    <a href=""  hidefocus="true" target="_blank"> 购物袋</a> 
    <a href=""  hidefocus="true" >收藏夹</a>
  </dd>
  <dt>个人应用管理</dt>

  <dd>
    <a href="${rc.getContextPath()}/stationLetter/list?queryRecord=all" hidefocus="true"> 站内信</a>
    <a href="" hidefocus="true" >好友邀请</a>   
    <a href="" hidefocus="true"> 消息订阅</a>
   <!-- <a href="myapp_goodsTalk.shtml" target="ifm" hidefocus="true"> 商品评论</a>
    <a href="myapp_msgManager.shtml" target="ifm" hidefocus="true"> 留言管理</a>-->
  </dd>

  <dt>服务中心</dt>
  <dd>
  	<a href="" hidefocus="true"> 退换货申请</a>
  	
  	<a href="${rc.getContextPath()}/goodsRefund/list" hidefocus="true"> 退换货记录</a>
    <a href="${rc.getContextPath()}/feedback/list?feedbackType=3" hidefocus="true"> 投诉/咨询/建议</a>

   <!-- <a href="backGoodsApp.shtml"  hidefocus="true"> 退换货</a>-->
    <!--<a href="server_log.shtml" target="ifm" hidefocus="true"> 账户日志</a>-->
    <!--   <a href="javascript:void(0);"  hidefocus="true"> 退款申请</a>-->
  </dd>
  <!--<h3>
    <a href="javascript:void(0);"  hidefocus="true">退出</a>
  </h3>-->

</dl>

  </div>
  <!--左边我的走秀 结束-->
<script type="text/javascript" src="${rc.getContextPath()}/resources\portal\js\myxiuMenu.js"></script>