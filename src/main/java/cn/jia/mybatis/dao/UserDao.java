package cn.jia.mybatis.dao;

import cn.jia.mybatis.entity.User;

import java.util.List;

/**
 * Created by jia on 2018/3/15.
 */
public interface UserDao {
    User findUserById(int id) throws Exception;

    User findUserById1(int id);
    List<User> findUserAndItemsResultMap();
    int updateUserById(User user);
}
