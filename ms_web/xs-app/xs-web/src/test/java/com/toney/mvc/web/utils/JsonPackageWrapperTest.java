package com.toney.mvc.web.utils;

import static com.github.restdriver.serverdriver.Json.asJson;
import static com.github.restdriver.serverdriver.Json.containingValue;
import static com.github.restdriver.serverdriver.Json.hasJsonArray;
import static com.github.restdriver.serverdriver.Json.hasJsonValue;
import static com.github.restdriver.serverdriver.Matchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.toney.core.model.User;
import com.toney.web.commons.utils.JsonPackageWrapper;

/*
 * please refer: 
 *    https://github.com/rest-driver/rest-driver/issues/108
 *    
 */
public class JsonPackageWrapperTest {
    private JsonPackageWrapper jsonp;
    private JsonNode jsonNode;

    @Before
    public void setUp() {
    }

    @Test
    public void testDataWithNull() throws Exception {
        // {"data":null,"scode":0,"stitle":"","smessage":""}
        jsonp = new JsonPackageWrapper();
        jsonp.setData(null);

        jsonNode = asJson(toJson(jsonp));

        assertThat(jsonNode, hasJsonValue("data", equalTo(null)));
        assertThat(jsonNode, hasJsonValue("scode", equalTo("0")));
        assertThat(jsonNode, hasJsonValue("smsg", equalTo("")));
    }

    @Test
    public void testDataWithString() throws Exception {
        jsonp = new JsonPackageWrapper();
        jsonp.setData("data");

        jsonNode = asJson(toJson(jsonp));

        assertThat(jsonNode, hasJsonValue("data", equalTo("data")));
    }

    @Test
    public void testDataWithList() throws Exception {
        // {"data":["one1","two"],"scode":0,"stitle":"","smessage":""}
        ArrayList<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");

        jsonp = new JsonPackageWrapper();
        jsonp.setData(list);

        jsonNode = asJson(toJson(jsonp));

        assertThat(jsonNode, hasJsonArray("data", containingValue(equalTo("one"))));
    }

    @Test
    public void testDataWithMap() throws Exception {
        // {"data":{"two":"twovalue","one":"onevalue"},"scode":0,"stitle":"","smessage":""}
        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "onevalue");
        map.put("two", "twovalue");

        jsonp = new JsonPackageWrapper();
        jsonp.setData(map);

        jsonNode = asJson(toJson(jsonp));

        assertThat(jsonNode, hasJsonPath("data.one"));
        assertThat(jsonNode, hasJsonPath("data.two"));

        assertThat(jsonNode, hasJsonValue("data", hasJsonValue("one", equalTo("onevalue"))));
        assertThat(jsonNode, hasJsonValue("data", hasJsonValue("two", equalTo("twovalue"))));
    }

    @Test
    public void testDataWithUser() throws Exception {
        // {"data":{"id":-100,"password":"password","username":"username","email":"email","phoneNumber":"phonenumber","version":null},"scode":0,"stitle":"","smessage":""}
        User user = new User();
        user.setEmail("email");
        user.setId(-100L);
        user.setPassword("password");
        user.setPhoneNumber("phonenumber");
        user.setUsername("username");

        jsonp = new JsonPackageWrapper();
        jsonp.setData(user);

        jsonNode = asJson(toJson(jsonp));

        assertThat(jsonNode, hasJsonPath("data.username"));
        assertThat(jsonNode, hasJsonValue("data", hasJsonValue("username", equalTo("username"))));
    }

    private String toJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
