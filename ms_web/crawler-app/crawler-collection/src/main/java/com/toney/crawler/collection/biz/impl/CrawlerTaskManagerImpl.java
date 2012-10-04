package com.toney.crawler.collection.biz.impl;


import static com.toney.crawler.collection.constants.GlobalConstants.IS_DISABLED;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.crawler.collection.biz.CrawlerTaskManager;
import com.toney.crawler.collection.biz.CrawlerXmlManager;
import com.toney.crawler.collection.dao.CrawlerTaskDao;
import com.toney.crawler.collection.model.CrawlerTaskModel;
import com.toney.crawler.collection.model.CrawlerXmlModel;
import com.toney.crawler.common.exception.BusinessException;

@Service("crawlerTaskManager")
public class CrawlerTaskManagerImpl implements CrawlerTaskManager{

	private static final XLogger logger=XLoggerFactory.getXLogger(CrawlerTaskManagerImpl.class);
	
	@Autowired
	private CrawlerTaskDao crawlerTaskDao;
	@Autowired
	private CrawlerXmlManager  crawlerXmlManager;
	
	
	@Override
	public List<CrawlerTaskModel> getAll() throws BusinessException {
		List<CrawlerTaskModel> list=this.crawlerTaskDao.getAll();
		return list;
	}
	
	@Override
	public List<CrawlerTaskModel> getEnabledAll() throws BusinessException {
		List<CrawlerTaskModel> list=this.crawlerTaskDao.getAll();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				CrawlerTaskModel model=list.get(i);
				if(model.getIsEnable()==IS_DISABLED){
					list.remove(i);
					i--;
				}
			}
		}
		return list;
	}

	@Override
	public void crawlerHome() throws BusinessException {
		List<CrawlerXmlModel> list=this.crawlerXmlManager.getEnabledAll();
		
	}

	
}
