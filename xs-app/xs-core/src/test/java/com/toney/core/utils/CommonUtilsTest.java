package com.toney.core.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.toney.core.model.User;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-3-28 下午12:53:31
 *       </p>
 **************************************************************** 
 */
public class CommonUtilsTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testCollectByPropertyName() {
        ArrayList<User> list = new ArrayList<User>();
        User user = new User();
        user.setUsername("username1");
        list.add(user);

        user = new User();
        user.setUsername("username2");
        list.add(user);

        Collection<String> res = CommonUtils.collectByPropertyName(list, "username");

        Collection<String> expected = new ArrayList<String>();
        expected.add("username1");
        expected.add("username2");

        assertEquals(expected, res);
    }

}
