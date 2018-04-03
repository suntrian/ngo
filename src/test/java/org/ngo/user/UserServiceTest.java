package org.ngo.user;

import org.junit.Test;
import org.ngo.basic.BaseTest;
import org.ngo.user.model.User;
import org.ngo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    private User user;

    @Test
    public void testGetUser() {
        List<User> users = (ArrayList) userService.list();
        assert users.get(0).getUsername().equals("abc");
    }

}
