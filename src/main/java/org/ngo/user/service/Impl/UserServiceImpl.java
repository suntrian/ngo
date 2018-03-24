package org.ngo.user.service.Impl;

import org.ngo.basic.BasicServiceImpl;
import org.ngo.basic.IBasicDao;
import org.ngo.user.bean.User;
import org.ngo.user.dao.IUserDao;
import org.ngo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl extends BasicServiceImpl<User, Long> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private IUserDao userDao;

    @Override
    public IBasicDao<User, Long> getBasicDao(){
        return userDao;
    }

    @Override
    public String userLogin(String uniqueName, String password){
        logger.info("{} login",uniqueName);
        return "";
    }
    @Override
    public String userLogout(String userId){
        logger.info("{} logout", userId);
        return "";
    }

}
