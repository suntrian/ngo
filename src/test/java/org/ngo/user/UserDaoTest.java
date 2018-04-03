package org.ngo.user;

import com.google.common.base.Strings;
import org.junit.Before;
import org.junit.Test;
import org.ngo.basic.BaseTest;
import org.ngo.user.dao.UserDao;
import org.ngo.user.model.User;
import org.ngo.user.model.UserProfile;
import org.ngo.util.util.RandomUtil;
import org.ngo.util.util.StringUtil;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest extends BaseTest {


    Marker event = MarkerFactory.getMarker(UserDaoTest.class.getName());

    @Autowired
    private UserDao userDao;

    @Before
    public void setUpClass() {
        org.springframework.util.StringUtils stringUtils;
        StringUtil stringUtil;
        com.alibaba.druid.util.StringUtils stringUtils1;
        com.sun.deploy.util.StringUtils stringUtils2;
        com.sun.xml.internal.ws.util.StringUtils stringUtils3;
        Strings strings;
        org.apache.logging.log4j.util.Strings strings1;
    }

    @Test
    @Transactional
    public void testAddUser() {

        User user = new User(RandomUtil.String(5), RandomUtil.String(5));
        int c = userDao.insertByBean(user);
        logger.debug("inserted {}, UserId {}", c, user.getId());
        assert c == 1;

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", RandomUtil.String(5));
        userMap.put("password", RandomUtil.String(5));
        c = userDao.insertByMap(userMap);
        logger.debug("inserted by Map {}", c);
        assert c == 1;

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            userList.add(new User(RandomUtil.String(5), RandomUtil.String(5)));
        }
        c = userDao.insertByBeanList(userList);
        assert c == 3;

    }

    @Test
    @Transactional
    public void testGetUser() {
        User user = userDao.selectOneById(1);
        assert user.getUsername().equals("abc");
        logger.debug(user.getUsername());
        user = userDao.selectOneById(1);
        user = userDao.selectOneById(1);

        UserProfile profile = user.getUserProfile();
        assert profile != null;
        logger.debug(profile.toString());

        user = userDao.getUserByUsernameAndPassword("abc", "123");
        logger.debug(user.toString());

    }

    @Test
    public void testListUsers() {
        List<User> users = (List<User>) userDao.selectAll();
        Assert.notEmpty(users, "table user is empty or query Users failed");
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        Integer c;
        c = userDao.deleteByBean(new User(1));
        assert c == 1;
        c = userDao.deleteByIdArray(new Integer[]{2, 3});
        assert c == 2;
        List<User> users = new ArrayList<User>() {{
            add(new User(4));
            add(new User(5));
            add(new User(6));
        }};
        c = userDao.deleteByBeanList(users);
        assert c == 3;
    }

    @Test
    public void testExecSql() {
        Map user = userDao.selectOneBySql("select * from user where id = 1");
        //assert  (Integer)user.get("id") == 1;
        logger.debug(user.toString());

        List<Map> map = userDao.selectListBySql("select * from user");
        logger.debug(map.toString());
    }

    @Test
    @Transactional
    public void testExecDeleteSql() {
        Integer c = userDao.deleteBySql("delete From user where id = 8");
        logger.debug("Count= {}", c);
        assert c == 1;
    }

    @Test
    @Transactional
    public void testExecInsertSql() {
        Integer c = userDao.insertBySql("insert into user(username, password) values('qqq','bbb')");
        logger.debug("inserted by sql{}", c);
        assert c == 1;
    }

    @Test
    public void testGetNameSpace() {
        logger.debug(this.getClass().getName());
        super.testGetNameSpace();
    }

}
