package org.ngo.user.service;

import org.ngo.basic.IBasicService;
import org.ngo.user.bean.User;

public interface UserService extends IBasicService<User, Long> {

    public String userLogin(String uniqueName, String password);
    public String userLogout(String userId);

}
