$(document).ready(function(){
	//搜索条件控制
	
	//展示订单控制
	$('.updown').click(function(){
		if($(this).hasClass('curr')){
			$(this).removeClass('curr');
			$(this).text('收起');
		}else{
			$(this).addClass('curr');
			$(this).text('展开');
		}
		
		$(this).parents('.order_one').children('table').toggle(); 
		$(this).parents('.order_one').children('.o_total').toggle();
	});
});