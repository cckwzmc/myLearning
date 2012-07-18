				var bookId = $('body').attr("bookId");
				var showForceLoginPage = $('body').attr("showForceLoginPage");
				// 用户已登录
				if (user) {
					$("#qzdl").hide();
					$.unblockUI();
					document.ondblclick=autoScroll.start;
					document.onmousedown=autoScroll.stop;
					
					$(document)
						.keyup(
							function(event) {
								if (event.keyCode == $.ui.keyCode.ENTER) {
									document.location = "http://book.zongheng.com/showchapter/" + bookId + ".html";
								}
							});
							
					if($.cookie("justLogin")=="true"){
						$.cookie("justLogin","",{expires: -1, path: '/', domain: '.zongheng.com'});
						if(showForceLoginPage=="showForceLoginPage"){
							existInShelf();
						}
					}
				}
				// 显示强制页面
				else if(showForceLoginPage == "showForceLoginPage"){
					$.blockUI({ 
			             message: $('#qzdl'), 
			             css:{top:'20%',left:'20%',border:'0',cursor:'auto',backgroundColor:'transparent',display:''}
				    });
				    document.body.style.overflow="hidden";
					document.getElementsByTagName("html")[0].style.overflow="hidden";	
					document.ondblclick=autoScroll.stop;
					document.onmousedown=autoScroll.stop;		
					
					$(document)
						.keyup(
							function(event) {
								if (event.keyCode == $.ui.keyCode.ENTER) {
									return;
								}
							});	 
					var date = new Date();
					date.setTime(date.getTime() + (1 * 60 * 1000));// 保留一分钟
					$.cookie("justLogin","true",{expires: date, path: '/', domain: '.zongheng.com'});
				}
				
				function existInShelf(){
					var url = '/ajax/isExistInShelf.do';
					var params = {
						bookId : bookId
					};
					$.ajax( {
						type : "POST",
						url : url,
						data : params,
						success : function(data) {
							if (data) {
								if (data.mes) {
									alert(data.mes);
									return false;
								}else if(data.existInShelf){
								}else{
									if (confirm('确定收藏本书么?')) {
										putInShelf();
									}
								}
							}
						},
						dataType : 'json',
						async : false
					})
				}

				function putInShelf() {
					var url = '/ajax/bookshelf.do';
					var params = {
						bookId : bookId
					};
					$.ajax( {
						type : "POST",
						url : url,
						data : params,
						success : function(data) {
							if (data) {
								if (data.mes) {
									alert(data.mes)
								}
							}
						},
						dataType : 'json',
						async : false
					})
				}