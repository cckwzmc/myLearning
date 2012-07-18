package com.toney.core.utils;

import java.util.Collection;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-3-28 下午12:49:37
 *       </p>
 **************************************************************** 
 */
public final class CommonUtils {
    @SuppressWarnings("rawtypes")
    public static Collection collectByPropertyName(Collection collection, String propertyName) {
        return CollectionUtils.collect(collection, new BeanToPropertyValueTransformer(propertyName));
    }
}
