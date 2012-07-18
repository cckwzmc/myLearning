package com.toney.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-3-27 下午3:36:31
 *       </p>
 **************************************************************** 
 */
@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml", "classpath:/applicationContext-core.xml" })
public class SpringResourceLocatorTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void testGetDatabaseConfiguration() {
        Configuration config = SpringResourceLocator.getDatabaseConfiguration();

        assertNotNull(config);

        assertEquals("value", config.getString("key"));
        assertEquals("prefix.value", config.getString("prefix.key"));
    }

}
