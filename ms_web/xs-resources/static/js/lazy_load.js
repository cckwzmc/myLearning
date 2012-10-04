(function (a) {
	TONEY.IMAGELAZYLOAD = function (g) {
		if (g !== "") {
			var c = [],
			h = "",
			e = 0,
			f = !1,
			i = null,
			e = document.body.clientHeight && document.documentElement.clientHeight ? document.body.clientHeight < document.documentElement.clientHeight ? document.body.clientHeight : document.documentElement.clientHeight : document.body.clientHeight > document.documentElement.clientHeight ? document.body.clientHeight : document.documentElement.clientHeight,
			j = e * 2,
			k = function () {
				for (var b = g.split(","), d = 0; d < b.length; d++) {
					var c = b[d];
					c.slice(-4) !=
					" img" && (b[d] = a.trim(c) + " img")
				}
				h = b.join(",")
			},
			l = function () {
				a(h).each(function () {
					(a(this).attr("src") === void 0 || a(this).attr("src") === "" || a(this).attr("src").indexOf("loading") !== -1) && (a(this).attr("ori-src") !== "" || a(this).attr("ori-src") !== void 0) && c.push(this)
				})
			};
			(function () {
				k();
				l();
				c.length !== 0 && (i = setInterval(function () {
							c.length === 0 && clearInterval(i);
							if (!f) {
								f = !0;
								for (var b = 0; b < c.length; b++) {
									var d = a(c[b]);
									d.offset().top < a(window).scrollTop() + j + e && d.offset().top > a(window).scrollTop() - j && (d.attr("src",
											d.attr("ori-src")), c.splice(b, 1), b--)
								}
								f = !1
							}
						}, 100))
			})()
		}
	}
})(jQuery);