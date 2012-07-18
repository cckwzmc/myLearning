$(function() {

	var adListener = function()
	{
		var adPosition = jQuery(this).attr("adPosition");
		var adGroup = jQuery(this).attr("adGroup");

		if(adPosition != null && adGroup != null)
		{
			setTimeout(function(){
				(new Image).src = "http://ad.zongheng.com/avote.php?ap=" + adPosition;
				(new Image).src = "http://log.zongheng.com/advisit/"+ adGroup + "/" + adPosition;
				}, 1000
			);
		}
	};

	jQuery(".adClickCss").each(function(){

		var nodeName = this.nodeName.toLowerCase();

		if(nodeName != "object")
		{
			jQuery(this).click(adListener);
		}
		else
		{
			jQuery(this).mousedown(adListener);
		}
	});
});