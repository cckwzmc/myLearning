package com.toney.core.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.toney.core.biz.impl.UserDemoManagerImpl;
import com.toney.core.dao.UserDao;
import com.toney.core.model.User;
public class UserDemoManagerTest extends BaseManagerTestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDemoManagerTest.class);

    @Autowired
    private UserDemoManager mgr;

    private User user;
    
    @Mock 
    UserDao userDao;
    
    @Before
    public void setUp() {
    	userDao=mock(UserDao.class);
    	user=new User();
    	user.setUsername("user");
		user.setId(123456L);
		when(userDao.getUserByUsername("user")).thenReturn(user);;
		assertNotNull(user.getId());
    }
    
	@Test
    public void testGetUser() throws Exception {
		UserDemoManagerImpl userManagerImpl=new UserDemoManagerImpl();
		ReflectionTestUtils.setField(userManagerImpl, "userDao", userDao);   
        user = userManagerImpl.getUserByUsername("user");
        assertNotNull(user);
    }

    @Test
    public void testSaveUser() throws Exception {
		UserDemoManagerImpl userManagerImpl=new UserDemoManagerImpl();
		ReflectionTestUtils.setField(userManagerImpl, "userDao", userDao);   
        user = userManagerImpl.getUserByUsername("user");
        user.setPhoneNumber("303-555-1212");

        LOGGER.debug("saving user with updated phone number: " + user);

        user = mgr.saveUser(user);
        assertEquals("303-555-1212", user.getPhoneNumber());
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);

        user = mgr.saveUser(user);
        assertEquals("john", user.getUsername());

        LOGGER.debug("removing user...");

        mgr.removeUser(user.getId().toString());

        try {
            user = mgr.getUserByUsername("john");
            //fail("Expected 'Exception' not thrown");
        } catch (Exception e) {
            LOGGER.debug(e.toString());
            assertNotNull(e);
        }
    }
}
