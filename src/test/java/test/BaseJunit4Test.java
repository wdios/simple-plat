package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:conf/spring.xml"}) //加载配置文件
public class BaseJunit4Test {

	@Test   //标明是测试方法
    public void testSuper() {
       //
    }
    
}
