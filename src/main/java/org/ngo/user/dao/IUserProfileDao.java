package org.ngo.user.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.user.model.UserProfile;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public interface IUserProfileDao extends BaseDao<UserProfile, Integer> {
}
