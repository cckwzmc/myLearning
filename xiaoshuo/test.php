<?php
	echo "sdfadf";
	$body="  <img src='/090624/1244214222540-404932.jpg' alt='点击图片下一张' border='0' /><img src='/090624/12442142251F-419101.jpg' alt='点击图片下一张' border='0' /><img src='/090624/124421422C0-42GY.jpg' alt='点击图片下一张' border='0' /><img src='/090624/124421422PF-434647.jpg' alt='点击图片下一张' border='0' /><img src='/090624/124421422b0-443963.jpg' alt='点击图片下一张' border='0' /><img src='/090624/1244214231B0-453W1.jpg' alt='点击图片下一张' border='0' /><img src='/090624/1244214222540-404932.jpg' alt='点击图片下一张' border='0' /><img src='/090624/12442142335F-464214.jpg' alt='点击图片下一张' border='0' /><img src='/090624/12442142352Z-4K291.jpg' alt='点击图片下一张' border='0' /><img src='/090624/124421423I30-4T1S.jpg' alt='点击图片下一张' border='0' /><img src='/090624/1244214239250-491509.jpg' alt='点击图片下一张' border='0' />";
	$img_array = array();
	preg_match_all("/(src)=[\"|'| ]{0,}(http:\/\/([^>]*)\.(gif|jpg|png))/isU",$body,$img_array);
	print $img_array.length;
	
?>