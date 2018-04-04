package org.ngo.user;

import org.junit.Test;
import org.ngo.basic.TestBase;
import org.ngo.user.dao.UserDao;
import org.ngo.user.dao.UserProfileDao;
import org.ngo.user.model.User;
import org.ngo.user.model.UserProfile;
import org.ngo.util.util.DateUtil;
import org.ngo.util.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class TestUserProfileDao extends TestBase {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserProfileDao profileDao;

    @Test
    public void testListUserProfile(){

    }

    @Test
    public void testAddUserProfile(){
        List<User> users = (List<User>) userDao.selectAll();
        UserProfile profile = new UserProfile();
        for (User user: users){
            profile.setId(user.getId());
            profile.setCity(new RandomUtil().nextInt(100));
            profile.setComment(RandomUtil.nextString(66));

            profileDao.insertByBean(profile);
        }
    }

    @Test
    @Transactional
    public void testUpdateUserProfile() throws Exception{
        List<UserProfile> profiles = (List<UserProfile>) profileDao.selectAll();
        for (UserProfile profile: profiles){
            profile.setCreateTime(new Date());
            profile.setBirthday(DateUtil.date("1989-04-10"));
            profile.setGender(new RandomUtil().nextBoolean());
            profile.setHomeAddress(RandomUtil.justString(10));
            profile.setComment(RandomUtil.justString(50));
            profileDao.updateByBean(profile);
        }
    }

}
