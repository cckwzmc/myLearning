package com.toney.crawler.collection.cache;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author toney.li
 *
 */
@Service("applicationCacheManager")
@Lazy(value=false)
public class ApplicationCacheManagerImpl implements ApplicationCacheManager {

}
