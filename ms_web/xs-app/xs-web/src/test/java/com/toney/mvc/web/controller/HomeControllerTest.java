package com.toney.mvc.web.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

public class HomeControllerTest extends BaseControllerTestCase {
    // 1、从Spring容器中加载AnnotationMethodHandlerAdapter
    @Autowired
    private AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter;

    // 2、从Spring容器中加载LoginController
    @Autowired
    private HomeController controller;

    @Autowired
    private RestTemplate restTemplate;

	//3、声明Request与Response模拟对象
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	//执行测试前先初始模拟对象
	
	@Before
	public void before() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	/**
	  * @Title: testChekcedNotice
	  * @Description: request method 必须一一对应 
	  * @param 
	  * @return void
	  * @throws
	  */
	@Ignore
	public void testChekcedNotice(){
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "toney.li@xiu.com");
		map.add("phone", "13022212223");
		String result = restTemplate.postForObject("http://localhost:8081/portal-web/notice", map, String.class);
		assertNotNull(result);
		assertThat(result, containsString("登记成功"));
		map.clear();
		map.add("email", "toney.li@xiu.com");
		map.add("phone", "13022212223");
	    result = restTemplate.getForObject(
	    		"http://localhost:8081/portal-web/home", String.class,map);
	    assertNotNull(result);
		assertThat(result, containsString("我的走秀"));
	}

    @Test
    public void testChekcedNoticeByMock() throws Exception {
        request.setRequestURI("/notice");
        request.addParameter("email", "toney.li@xiu.com"); // ⑥ 设置请求URL及参数
        request.addParameter("phone", "1234");
        request.setMethod("POST");
        ModelAndView mav = annotationMethodHandlerAdapter.handle(request, response, controller);

        assertNotNull(mav);
        assertEquals(mav.getViewName(), "home");
        assertThat(mav.getModel().get("email").toString(), equalTo("toney.li@xiu.com"));//
    }

    @Test
    public void testHomeRequest() {
        Model model = new ExtendedModelMap();
        Locale locale = new Locale("en", "US");
        String viewName = controller.home(locale, model);
        assertEquals("home", viewName);
        assertNotNull(model.addAttribute("serverTime"));
    }
}
