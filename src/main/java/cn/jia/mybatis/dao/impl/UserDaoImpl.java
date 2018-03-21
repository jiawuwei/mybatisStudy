package cn.jia.mybatis.dao.impl;

import cn.jia.mybatis.dao.UserDao;
import cn.jia.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by jia on 2018/3/15.
 */
public class UserDaoImpl implements UserDao{
    //注入sqlSerssionFactory，通过构造方法的方式注入
    private SqlSessionFactory sqlSessionFactory;
    public UserDaoImpl(){}
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    @Override
    public User findUserById(int id) throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", id);
        sqlSession.close();
        return user;

    }

    @Override
    public User findUserById1(int i) {
        return null;
    }

    @Override
    public List<User> findUserAndItemsResultMap() {
        return null;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }
}
