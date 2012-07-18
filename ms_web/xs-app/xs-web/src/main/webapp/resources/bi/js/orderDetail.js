function reClick(index) {
    if ($("#closeFlag" + index).val() == 0) {
        $("#tablebox" + index).hide();
        $("#aimg" + index).find("img").attr({ src: "${xiuAssetsDir}static/css/default/img/userCenter/block.gif", alt: "展开" })
        $("#closeFlag" + index).val('1');
    }
    else {
        $("#tablebox" + index).show();
        $("#aimg" + index).find("img").attr({ src: "${xiuAssetsDir}static/css/default/img/userCenter/closed.gif", alt: "收起" })
        $("#closeFlag" + index).val('0');
    }
}