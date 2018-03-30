package org.ngo.user.service.Impl;

import org.ngo.basic.service.BaseServiceImpl;
import org.ngo.user.dao.IUserProfileDao;
import org.ngo.user.dao.UserDao;
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
    private IUserProfileDao userProfileDao;

    @Override
    public String userLogin(String uniqueName, String password){
        logger.info("{} login",uniqueName);
        if (logger.isDebugEnabled()) {

        }
        return "";
    }
    @Override
    public String userLogout(Integer userId) {
        logger.info("{} logout", userId);
        return "";
    }


    @Override
    public UserProfile getProfile(Integer id) {
        return userProfileDao.get(id);
    }

    @Override
    public Integer setProfile(UserProfile profile) {
        return userProfileDao.update(profile);
    }
}
