package org.ngo.user.service;

import org.ngo.basic.service.BaseService;
import org.ngo.user.model.User;
import org.ngo.user.model.UserProfile;

public interface UserService extends BaseService<User, Integer> {

    String userLogin(String uniqueName, String password);

    String userLogout(Integer userId);

    UserProfile getProfile(Integer id);

    Integer setProfile(UserProfile profile);
}
