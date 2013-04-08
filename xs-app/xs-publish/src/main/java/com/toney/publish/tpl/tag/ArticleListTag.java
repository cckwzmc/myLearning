package com.toney.publish.tpl.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.NumberUp;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.StringUtils;

import com.toney.core.dao.ArticleQueryModel;
import com.toney.core.model.ArticleModel;
import com.toney.publish.tpl.utils.FreemarkerUtils;
import com.toney.publish.tpl.utils.FreemarkerUtils.InvokeType;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ArticleListTag extends AbstractArticleListTag{

	public static final String TAG_NAME="art_list";
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException,
			IOException {
		InvokeType type = FreemarkerUtils.getInvokeType(params);
		if(InvokeType.body==type&&body!=null){
			setTagParams(params);
			List<ArticleModel> list=this.articleManager.getArticleList(getOrderby(),getRow(),getChannelIds(),getArctag());
			if(CollectionUtils.isNotEmpty(list)){
				Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
						params);
				paramWrap.put(TAG_NAME, DEFAULT_WRAPPER.wrap(list));
				Map<String, TemplateModel> origMap = FreemarkerUtils
						.addParamsToVariable(env, paramWrap);
				body.render(env.getOut());
				FreemarkerUtils.removeParamsFromVariable(env, paramWrap, origMap);
			}
		}
//		env.getOut().write("abbcc");
//		body.render(env.getOut());
		
	}
	@SuppressWarnings("rawtypes")
	private void setTagParams(Map params) {
		if(MapUtils.isEmpty(params)){
			return ;
		}
		if(params.containsKey("orderby")){
			this.setOrderby(params.get("orderby").toString());
		}		
		if(params.containsKey("row")&&org.apache.commons.lang.StringUtils.isNumeric(params.get("row").toString())){
			this.setRow(NumberUtils.toInt(params.get("row").toString()));
		}		
		if(params.containsKey("channelIds")){
			this.setChannelIds(params.get("channelIds").toString());
		}		
		if(params.containsKey("arctag")){
			this.setArctag(params.get("arctag").toString());
		}		
	}

}
