package cn.jia.mybatis.test;

import cn.jia.mybatis.dao.OrdersDao;
import cn.jia.mybatis.dao.UserDao;
import cn.jia.mybatis.dao.impl.UserDaoImpl;
import cn.jia.mybatis.entity.Orders;
import cn.jia.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jia on 2018/3/15.
 */
public class UserDaoImplTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws IOException {
        //1.获取路径
        String resource="SqlMapConfig.xml";
        //2.获取文件流
        InputStream stream = Resources.getResourceAsStream(resource);
        //3.根据文件流创建会话工厂
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
    }

    @Test
    public void test() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User userById = userDao.findUserById(1);
        System.out.println(userById);

    }
    @Test
    public void test1() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User userById = userDao.findUserById(1);
        System.out.println(userById);
        sqlSession.close();

    }

    /**
     * 测试resultMap的使用
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User userById = userDao.findUserById1(1);
        System.out.println(userById);
        sqlSession.close();

    }
    /**
     * 一对一查询
     */
    @Test
    public void test3(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersDao mapper = sqlSession.getMapper(OrdersDao.class);
        List<Orders> ordersWithUser = mapper.findOrdersWithUser();
        sqlSession.close();
        System.out.println();
    }
    /**
     * 一对多查询
     */
    @Test
    public void test4(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersDao mapper = sqlSession.getMapper(OrdersDao.class);
        List<Orders> ordersAndOrderDetailResultMap = mapper.findOrdersAndOrderDetailResultMap();
        sqlSession.close();
    }
    /**
     * 多对多查询
     */
    @Test
    public void test5() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> list = mapper.findUserAndItemsResultMap();
        System.out.println(list);
        sqlSession.close();
    }
    /**
     * 懒加载查询
     */
    @Test
    public void test6() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersDao ordersDao = sqlSession.getMapper(OrdersDao.class);
        //查询订单表（单表）
        List<Orders> list = ordersDao.findOrdersWithUserByLazy();
        //为了证明实现了懒加载，所以不能用debug模式，因为debug模式会默认调用user的get方法，
        //所以使用tostring方法打印user是否为空
        for (Orders orders : list) {
            orders.print();
        }
        //遍历上边的订单列表
        for(Orders orders : list) {
            //执行getUser()去查询用户信息，这里实现按需加载
            User user = orders.getUser();
            System.out.println(user); //mybatis使用的是一级缓存，所以三个对象是一样的
        };
        sqlSession.close();
    }

    /**
     * 测试mybatis二级缓存
     */
    @Test
    public void test7() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession(); //这个缓存用来清空缓存，当修改user，commit时就清空了缓存
        UserDao mapper1 = sqlSession1.getMapper(UserDao.class);
        User user1 = mapper1.findUserById(1);
        sqlSession1.close();

        //sqlSession2查询用户信息
        UserDao mapper2 = sqlSession2.getMapper(UserDao.class);
        User user2 = mapper2.findUserById(1);
        sqlSession2.close();

        //sqlSession3修改用户信息
        UserDao mapper3 = sqlSession3.getMapper(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setUsername("贾全伟");
        int count = mapper3.updateUserById(user);
        sqlSession3.commit();//提交会清空二级缓存及一级缓存
        sqlSession3.close();




    }
}
