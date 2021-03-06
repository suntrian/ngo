package org.ngo.user.service.Impl;

import org.ngo.basic.service.BaseServiceImpl;
import org.ngo.user.dao.UserDao;
import org.ngo.user.dao.UserProfileDao;
import org.ngo.user.model.User;
import org.ngo.user.model.UserProfile;
import org.ngo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserProfileDao userProfileDao;

    @Override
    public Boolean userLogin(String uniqueName, String password){
        User user = userDao.getUserByUsernameAndPassword(uniqueName, password);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public String userLogout(Integer userId) {
        logger.info("{} logout", userId);
        return "";
    }


    @Override
    public UserProfile getProfile(Integer id) {
        return userProfileDao.selectOneById(id);
    }

    @Override
    public Integer setProfile(UserProfile profile) {
        return userProfileDao.updateByBean(profile);
    }
}
