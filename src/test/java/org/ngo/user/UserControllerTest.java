package org.ngo.user;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ngo.basic.BasicTest;
import org.ngo.user.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest extends BasicTest {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testUserLogin() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/login").param("uniquename", "abc").param("passwd","123"));
        MvcResult mvcResult = resultActions.andReturn();
        logger.debug("Status Code:{}", mvcResult.getResponse().getStatus());
        logger.debug("Response Text:{}", mvcResult.getResponse().getContentAsString());
        // 也可以从response里面取状态码，header,cookies...
//        System.out.println(mvcResult.getResponse().getStatus());

    }

}
