package com.toney.istyle.core.platform;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.PlatFormBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormQueryServiceImpl.java
 * @DESCRIPTION : 平台商查询接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@Service("platFormQueryService")
public class PlatFormQueryServiceImpl implements PlatFormQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormQueryServiceImpl.class);

	@Autowired
	PlatFormRespository platFormRespository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.platform.PlatFormQueryService#getPlatFormAll()
	 */
	@Override
	public List<PlatFormBO> getPlatFormAll() throws ServiceException {
		try {
			return this.platFormRespository.getPlatFormAll();
		} catch (RespositoryException e) {
			LOGGER.error("获取所有平台商信息失败", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.platform.PlatFormQueryService#getPlatFormById(java
	 * .lang.Long)
	 */
	@Override
	public PlatFormBO getPlatFormById(Long id) throws ServiceException {
		try {
			return this.platFormRespository.getPlatFormById(id);
		} catch (RespositoryException e) {
			LOGGER.error("根据ID查询平台商信息失败 , id={}", id, e);
			throw new ServiceException(e);
		}

	}
}
