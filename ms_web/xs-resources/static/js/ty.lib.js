function lazy_onload(type, src)
{
	if (type == "js")
		jQuery("head").append(jQuery("<script/>").attr("src", src).attr("type", 'text/javascript'));
	if (type == "css")
		jQuery("head").append(jQuery("<link/>").attr("href", src).attr("type", 'text/css').attr('rel', 'stylesheet'));
}