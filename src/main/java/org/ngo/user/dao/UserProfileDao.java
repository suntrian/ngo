package org.ngo.user.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.user.model.UserProfile;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public interface UserProfileDao extends BaseDao<UserProfile, Integer> {
}
