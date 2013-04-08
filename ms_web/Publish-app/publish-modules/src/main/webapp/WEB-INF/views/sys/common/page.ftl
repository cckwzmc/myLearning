<#if page.totalCount gt 0 >
<script type="text/javascript">
  	function tosubmits(p){
  	  	if(!/\d+/g.test(p))
  	  	  	p = 1;
		document.getElementById("pageNo").value=p;
		document.getElementById("mainForm").submit();
  	}
  	function topage(){
  	  	var toPage =  document.getElementById("toPage").value;
  		if(!/\d+/g.test(toPage))
  			toPage = 1;
  		document.getElementById("pageNo").value=toPage;
  		document.getElementById("mainForm").submit();
  	}
  	function resetPageNo(){
  		document.getElementById("pageNo").value=1;
  	}
</script>
 <input name="pageNo" id="pageNo" type="hidden" value="${page.pageNo }"/>
 <input name="totalCount" id="totalCount" type="hidden" value="${page.totalCount }"/>
 <div class="page_div">
       <ul class="page_ul">
		<li class="l_page">
		    <div>总计 ${page.totalCount} 条 记录</div>
		    <div style="margin-left: 5px">共 ${page.totalPages} 页</div> 
		</li>
		<li class="page_num">
			<#if  page.isHasPre >			
				 <a href="#" onclick="tosubmits('1');"> << </a>&nbsp;
			</#if>			
			<#if page.pageNo gt 2 >
				...
			</#if>
			
			<#list page.showMinPage..page.showMaxPage as i> 				
					<#if page.pageNo==i >
						<a href="#" onclick="tosubmits(${i});"><span style="color: #FF6701;font-weight: bold;">${i}</span></a>&nbsp;
					<#else>					
						<a href="#" onclick="tosubmits(${i});">${i}</a>&nbsp;
					</#if>					
			</#list>
			<#if page.totalPages gt (page.pageNo + 1) >
				...
			</#if>
			<#if page.isHasNext >
				<a href="#" onclick="tosubmits('${page.totalPages}');"> >> </a>&nbsp;
			</#if>
			</li>
			<li class="r_page">
				
					<#if page.isHasPre >
						<a href="#" onclick="tosubmits('${page.pageNo-1}');">上一页</a>
					<#else>					
						<div>上一页</div>
					</#if>
					&nbsp;								
					<#if page.isHasNext >
						<a href="#" onclick="tosubmits('${page.pageNo+1}');">下一页</a>
					<#else>
						<div>下一页</div>
					</#if>								
			</li>
	</ul>
 </div>
 </#if>