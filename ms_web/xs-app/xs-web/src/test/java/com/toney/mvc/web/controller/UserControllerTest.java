package com.toney.mvc.web.controller;


public class UserControllerTest extends BaseControllerTestCase {
//    // 1、从Spring容器中加载AnnotationMethodHandlerAdapter
//    @Autowired
//    private AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter;
//
//    @Autowired
//    private UserController controller;  
//
//    @Autowired
//    private UserManager userManager;   
//
//	//3、声明Request与Response模拟对象
//	private MockHttpServletRequest request;
//	private MockHttpServletResponse response;
//	//执行测试前先初始模拟对象
//	
//	
//	private UserDetailDTO userDetailDTO;
//	
//	@Before
//	public void before() {
//		request = new MockHttpServletRequest();
//		request.setCharacterEncoding("UTF-8");
//		response = new MockHttpServletResponse();
//		
//        userDetailDTO = new UserDetailDTO();
//        userDetailDTO.setUserId(Long.valueOf("1000001"));
//        userDetailDTO.setEmail("test@xiu.com");
//        userDetailDTO.setPassword("111111");
//        
//        ModifyUserPassword modifyUserPassword = new ModifyUserPassword();
//        modifyUserPassword.setUserId(1000001169l);
//        modifyUserPassword.setOldPassword("123456");
//        modifyUserPassword.setNewPassword("111111");
//        
//        Result result1 = new Result();
//        result1.setSuccess(FacadeConstant.SUCCESS);
//        result1.setData(userDetailDTO);
//
//        UserManager userManager = Mockito.mock(UserManager.class);
//		Mockito.doNothing().when(userManager).modifyUserPassword(modifyUserPassword);
//	}
//
//
//	@Test
////	@Ignore
//	public void testModifyUserPassword() throws Exception{		
//        request.setRequestURI("/user/modifyPassword");
//        request.addParameter("oldPassword", "123456"); // ⑥ 设置请求URL及参数
//        request.addParameter("newPassword", "111111");
//        request.setParameter("format", "json");
//        request.setMethod("POST");
//        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING,true);
//        
//        ReflectionTestUtils.setField(controller, "userManager", userManager);
//        ModelAndView mav = annotationMethodHandlerAdapter.handle(request, response, controller);
//        assertNotNull(mav);
//        assertEquals(mav.getViewName(), "/user/modifyUserPassword");
//	}
//	
//
//
//    @Test
////    @Ignore
//    public void testGetUserDetailInfoByUserId() throws Exception{      
//        request.setRequestURI("/user/showPersonInfo");
//        request.setMethod("POST");
//        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING,true);
//        UserAuthInfo user=new UserAuthInfo();
//        user.setSsoUserId(1234567l);
//		UserAuthInfoHolder.setUserAuthInfo(user);
//        ReflectionTestUtils.setField(controller, "userManager", userManager);
//        ModelAndView mav = annotationMethodHandlerAdapter.handle(request, response, controller);
//        assertNotNull(mav);
//        assertEquals(mav.getViewName(), "pages/user/personInfo");
//    }

}
