package cn.jia.mybatis.test;

import cn.jia.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jia on 2018/3/15.
 */
public class MybatisFirst {
    private final SqlSessionFactory sqlSessionFactory;
    public MybatisFirst() throws IOException {
        this.sqlSessionFactory=getSessionFactory();
    }
    //1.设置SQLSessionFactory
    private static SqlSessionFactory getSessionFactory() throws IOException {
        //1.获取路径
        String resource="SqlMapConfig.xml";
        //2.获取文件流
        InputStream stream = Resources.getResourceAsStream(resource);
        //3.根据文件流创建会话工厂
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(stream);
        return build;

    }
    //2.获取session
    public SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
    //3.根据session查询
    @Test
    public void findUserById(){
        SqlSession sqlSession = getSession();
        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);
        sqlSession.close();
    }
}
