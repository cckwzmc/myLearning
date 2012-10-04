package com.toney.crawler.collection.biz.impl;
import static com.toney.crawler.collection.constants.GlobalConstants.IS_DISABLED;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toney.crawler.collection.biz.CrawlerXmlManager;
import com.toney.crawler.collection.dao.CrawlerXmlDao;
import com.toney.crawler.collection.model.CrawlerXmlModel;
import com.toney.crawler.common.exception.BusinessException;

/**
 * @author toney.li
 *  抓取xml配置.
 */
@Service("crawlerXmlManager")
public class CrawlerXmlManagerImpl implements CrawlerXmlManager {
	private static final XLogger logger=XLoggerFactory.getXLogger(CrawlerXmlManagerImpl.class);
	
	@Autowired
	private CrawlerXmlDao crawlerXmlDao; 
	
	@Override
	public List<CrawlerXmlModel> getAll() throws BusinessException{
		return this.crawlerXmlDao.getAll();
	}

	@Override
	public List<CrawlerXmlModel> getEnabledAll() throws BusinessException{
		List<CrawlerXmlModel> list=this.getAll();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				CrawlerXmlModel model=list.get(i);
				if(model.getIsEnable()==IS_DISABLED){
					list.remove(i);
					i--;
				}
			}
		}
		return list;
	}
	
	/**
	 * @param cid 站点id
	 * @param type 抓取类型  list/detail
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public CrawlerXmlModel getCrawlerXmlById(Integer cid,String type) throws BusinessException{
		CrawlerXmlModel query=new CrawlerXmlModel();
		query.setCid(cid);
		query.setType(type);
		CrawlerXmlModel model=this.crawlerXmlDao.getCrawlerXmlById(query);
		return model;
	} 
	
	@Override
	@Transactional
	public void updateCrawlerXml(Integer id,String xml) throws BusinessException{
		CrawlerXmlModel query=new CrawlerXmlModel();
		query.setId(id);
		query.setCrawlerXml(xml);
		this.crawlerXmlDao.updateCrawlerXml(query);
	}
}
