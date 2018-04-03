package org.ngo.user.dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ngo.basic.dao.BaseDao;
import org.ngo.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Integer> {

    @Select("select username,password from user where username=#{username} and password=#{password}")
    User getUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);



}
