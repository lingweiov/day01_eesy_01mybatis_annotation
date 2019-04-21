package com.cn.test;


import com.cn.bean.User;
import com.cn.inteface.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //使用工厂生产SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        IUserDao userDao=sqlSession.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> list=userDao.findAll();
        list.forEach(s-> System.out.println(s));
        //释放资源
        sqlSession.close();
        in.close();

    }
}
