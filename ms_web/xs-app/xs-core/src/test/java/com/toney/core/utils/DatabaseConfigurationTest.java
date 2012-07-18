package com.toney.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml", "classpath:/applicationContext-core.xml" })
public class DatabaseConfigurationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private Configuration config;

    @Test
    public void testGetValueByKey() throws Exception {
        assertEquals("value", config.getString("key"));
        assertEquals("prefix.value", config.getString("prefix.key"));
    }

    @Test
    public void testGetMissingKey() throws Exception {
        assertNull(config.getString("missingkey"));
    }
    
    public void testGetKeyWithNullValue() throws Exception {
        assertNull(config.getString("key_with_null_value"));
    }
        
    @Test
    public void testGetKeyFromOtherName() throws Exception {
        assertNull(config.getString("global1.key"));
    }
    

}
