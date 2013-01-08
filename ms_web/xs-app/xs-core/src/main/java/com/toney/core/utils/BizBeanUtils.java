package com.toney.core.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : beta.luo@xiu.com
 * @DATE :2012-4-9 下午3:56:42
 *       </p>
 **************************************************************** 
 */
public  class BizBeanUtils  extends BeanUtils{

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(BeanUtils.class);

    private BizBeanUtils() {

    }

    static {
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
    }

    public static void copyProperties(Object target, Object source) {
        try {
        BeanUtils.copyProperties(target, source);
    } catch (IllegalAccessException e) {
        LOGGER.debug("BeanUtils.copyProperties() IllegalAccessException   object={} ",
                new Object[] { source.toString() });
        //FIXME: throw exception
//        throw new BusinessException(ErrorConstants.SystemCode.PORTAL,
//                ErrorConstants.BusinessErorrCode.PORTAL_01000, e);
    } catch (InvocationTargetException e) {
        LOGGER.debug("BeanUtils.copyProperties() IllegalAccessException   object={} ",
                new Object[] { source.toString() });
        //FIXME: throw exception
//        throw new BusinessException(ErrorConstants.SystemCode.PORTAL,
//                ErrorConstants.BusinessErorrCode.PORTAL_01000, e);
    }
}
}
