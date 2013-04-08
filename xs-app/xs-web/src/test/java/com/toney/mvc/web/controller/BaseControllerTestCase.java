package com.toney.mvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/applicationContext-core.xml", "classpath:/applicationContext-web.xml",
        "/WEB-INF/dispatcher-servlet.xml" })
public abstract class BaseControllerTestCase extends AbstractTransactionalJUnit4SpringContextTests {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerTestCase.class);

    /**
     * Convenience methods to make tests simpler
     * 
     * @param url
     *            the URL to post to
     * @return a MockHttpServletRequest with a POST to the specified URL
     */
    public MockHttpServletRequest newPost(String url) {
        return new MockHttpServletRequest("POST", url);
    }

    public MockHttpServletRequest newGet(String url) {
        return new MockHttpServletRequest("GET", url);
    }
}
