package com.toney.istyle.core.product.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.constants.ProductConstants;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.product.ProductPicQueryService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductPicQueryServiceImpl.java
 * @DESCRIPTION : 商品图片查询
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productPicQueryService")
public class ProductPicQueryServiceImpl implements ProductPicQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductPicQueryServiceImpl.class);

	@Autowired
	ProductPicRepository productPicRepository;

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductPicQueryService#getProductPicById(java.lang.Long)
	 */
	@Override
	public List<ProductPicBO> getProductPicById(Long pid) throws ServiceException {
		return this.productPicRepository.getProductPicById(pid);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductPicQueryService#getProductMasterPic(java.lang.Long)
	 */
	@Override
	public ProductPicBO getProductMasterPic(Long pid) throws ServiceException {
		List<ProductPicBO> list=this.getProductPicById(pid);
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(list)){
			for(ProductPicBO bo:list){
				if(ProductConstants.PIC_MASTER_TYPE.equals(bo.getPicType())){
					return bo;
				}
			}
		}
		return null;
	}
}
