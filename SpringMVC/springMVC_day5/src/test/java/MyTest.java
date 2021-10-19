import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.pojo.Human;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxb.xml");
        Human bean = (Human) context.getBean("Hum");
        bean.getDog().setWorld("F**K");
        bean.getDog().action();
    }
}
