package org.ngo.user;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ngo.basic.BasicTest;
import org.ngo.user.dao.UserDao;
import org.ngo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoTest extends BasicTest {

    @Autowired
    private UserDao userDao;

    private User user;

    @Before
    public void setUpClass() {
        user = new User("abcde", "12345");
    }

    @Test
    @Transactional
    public void testAddUser() {
        int c = userDao.insert(user);
        logger.debug("inserted {}", c);
        assert c == 1;
    }


    @Ignore
    public void testGetUser() {
        this.user = userDao.get(1);
        assert user.getUsername().equals("abc");
    }

}
