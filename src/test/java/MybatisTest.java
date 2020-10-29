import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author Suzy
 * @Date 2020-10-25
 */
public class MybatisTest {

  /**
   * 传统方式
   *
   * @throws IOException
   */
  @Test
  public void startTest1() throws IOException {
    // 1.读取配置文件的输入流
    InputStream resource = Resources.getResourceAsStream("sqlMapper.xml");

    // 2.将解析出的内容封装为configuration对象，并构建DefaultSqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

    // 3.生产了DefaultSqlSession对象，并设置事务不自动提交，完成了executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 4. （1）根据statementid来从Configuration中map集合中指定的MappedStatement对象
    //    （2）将查询任务委派了executor执行器
    List<Object> objects = sqlSession.selectList("namespace.id");

    sqlSession.close();
  }

  /**
   * mapper代理方式
   */
  @Test
  public void startTest2() throws IOException {
    // 1.读取配置文件的输入流
    InputStream resource = Resources.getResourceAsStream("sqlMapper.xml");

    // 2.将解析出的内容封装为configuration对象，并构建DefaultSqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

    // 3.生产了DefaultSqlSession对象，并设置事务不自动提交，完成了executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();

    // 使用JDK动态代理来对mapper接口产生代理对象
    IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

    List<Object> objects = mapper.selectList();

  }
}
