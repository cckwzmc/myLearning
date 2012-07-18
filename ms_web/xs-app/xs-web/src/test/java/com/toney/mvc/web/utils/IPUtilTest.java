package com.toney.mvc.web.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.toney.mvc.web.utils.IPUtil;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 下午4:02:34
 *       </p>
 **************************************************************** 
 */
public class IPUtilTest {
    private MockHttpServletRequest mockRequest;

    @Before
    public void setUp() throws Exception {
        mockRequest = new MockHttpServletRequest();
    }

    @Test
    public void testGetRequestIPWithXForwardForHeader1() {
        // Client(202.106.0.20) -> LB(172.16.3.5) -> Server(172.16.3.41)

        mockRequest.addHeader("X-Forwarded-For", "202.106.0.20");
        mockRequest.setLocalAddr("172.16.3.41");
        mockRequest.setRemoteAddr("172.16.3.5");

        assertEquals("202.106.0.20", IPUtil.getRequestIP(mockRequest));
    }

    @Test
    public void testGetRequestIPWithXForwardedForHeader2() {
        // Client(202.106.0.20) -> LB(172.16.3.5) -> IHS(172.16.3.21)
        // -> LB(172.16.3.5) -> IHS(172.16.3.21) -> Server(172.16.3.41)

        mockRequest.addHeader("X-Forwarded-For", "202.106.0.20, 172.16.3.5, 172.16.3.21, 172.16.3.5");
        mockRequest.setLocalAddr("172.16.3.41");
        mockRequest.setRemoteAddr("172.16.3.5");

        assertEquals("202.106.0.20", IPUtil.getRequestIP(mockRequest));
    }

    @Test
    public void testGetRequestIPWithForwardedForHeader3() {
        // Client(202.106.0.20) -> Proxy(202.106.0.100) -> LB(172.16.3.5) -> Server(172.16.3.41)

        mockRequest.addHeader("X-Forwarded-For", "202.106.0.20, 172.16.3.5, 172.16.3.21, 172.16.3.5");
        mockRequest.setLocalAddr("172.16.3.41");
        mockRequest.setRemoteAddr("172.16.3.5");

        assertEquals("202.106.0.20", IPUtil.getRequestIP(mockRequest));
    }

    @Test
    public void testGetRequestIPWithoutXForwardedForHeader() {
        // Client(172.16.3.5) -> Server(172.16.3.41)

        mockRequest.setLocalAddr("172.16.3.41");
        mockRequest.setRemoteAddr("172.16.3.5");

        assertEquals("172.16.3.5", IPUtil.getRequestIP(mockRequest));
    }

}
