package com.toney.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.model.User;

public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao dao;

    public void testGetUserInvalid() throws Exception {
        assertNull(dao.getUser(1000L));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.getUser(-1L);

        assertNotNull(user);
        assertEquals("user", user.getUsername());
    }

    public void testUpdateUser() throws Exception {
        User user = dao.getUser(-1L);

        user.setEmail("test_xxx@xiu.com");
        assertEquals(1, dao.updateUser(user));

        assertEquals("test_xxx@xiu.com", user.getEmail());

        // verify that violation occurs when adding new user with same username
        user.setId(null);

        dao.addUser(user);
    }

    public void testAddUser() throws Exception {

        User user = new User();

        user.setEmail("test_add_user@xiu.com");
        user.setPassword("testpasswd");
        user.setPhoneNumber("13601111111");
        user.setUsername("test_add_user");

        dao.addUser(user);
        assertNotNull(user.getId());

        assertEquals(1, dao.deleteUser(user.getId()));

        assertNull(dao.getUser(user.getId()));
    }

    @Test
    public void testLoadUserByUsername() {
        assertNull(dao.getUserByUsername("not_exit_users"));

        User user = dao.getUserByUsername("user");
        assertNotNull(user);
        assertEquals("user", user.getUsername());
    }

}
