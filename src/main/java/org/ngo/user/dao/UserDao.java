package org.ngo.user.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends BaseDao<User, Integer> {

}
