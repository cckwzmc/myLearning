<#macro p page totalpage totalrecord=0 maxsteps=6>  
    <#assign ipage=page?string("##")?number>  
    <#if maxsteps <= 0>  
        <#assign maxsteps=5>  
    </#if>  
    <#assign offset = ((ipage - 1) / maxsteps)?int>  
    <#assign offsetLast = ((totalpage - 1) / maxsteps)?int>
       
    <#-- url附加参数的判断 -->  
    <#assign requestParams = "">  
    <#if (Parameters?? && Parameters?keys?size >0)>
    	<#list Parameters?keys as paramKey>
    		<#if (paramKey!="page")>
        		<#assign requestParams = requestParams+'&' +paramKey+"="+ Parameters[paramKey]?url("iso-8859-1")>
        	</#if>
        </#list>
    </#if>  
    <#-- 总记录-->
   <a class="disabled">总计&nbsp; ${totalrecord!''} &nbsp;条记录</a>  <a class="disabled">共&nbsp;  ${totalpage} &nbsp;页</a>  
    <#-- 首页 -->  
    <#if ipage gt 1>
        <a href="?page=1${requestParams}">&lt;&lt;</a>
    <#else>  
        <a class="disabled">&lt;&lt;</a>  
    </#if>  
    <#-- 前组-->  
    <#if totalpage gt 1>
	    <#if offset gt 0>
	        <a href="?page=${(offset * maxsteps)?string("##")}${requestParams}">…</a>
	    <#else>  
	      <!--  <a>…</a>   -->
        </#if> 
     </#if>    
    <#-- 当前组中的页号-->  
    <#if (offset + 1) * maxsteps < totalpage>  
        <#assign pagelist = (offset + 1) * maxsteps>  
    <#else>  
        <#assign pagelist = totalpage>  
    </#if>  
    <#if ipage gt 0 && ipage lte totalpage>  
        <#list (offset * maxsteps + 1)..pagelist as num>  
            <#if ipage != num>
                <a href="?page=${num?string("##")}${requestParams}">${num?string("##")}</a>
            <#else>  
                <a class="current">${num?string("##")}</a>  
            </#if>  
        </#list>  
    </#if>  
    <#-- 下组 -->  
    <#if totalpage gt 1>
	    <#if offset lt offsetLast>
	        <a href="?page=${((offset + 1) * maxsteps + 1)?string("##")}${requestParams}">…</a>
	    <#else>  
	      <!--  <a>…</a>   -->
	    </#if>  
	</#if>    
    <#-- 尾页 -->  
    <#if ipage lt totalpage>
        <a href="?page=${totalpage?string("##")}${requestParams}">&gt;&gt;</a>
    <#else>  
        <a>&gt;&gt;</a>  
    </#if>  
    <#-- 前一页 -->  
    <#if ipage gt 1>
        <a href="?page=${(ipage - 1)?string("##")}${requestParams}">上一页</a>
    <#else>  
        <a class="上一页">上一页</a>  
    </#if>  
    <#-- 后一页 -->  
    <#if ipage lt totalpage>
        <a href="?page=${(ipage + 1)?string("##")}${requestParams}">下一页</a>
    <#else>  
        <a class="下一页">下一页</a>  
    </#if>  
</#macro>