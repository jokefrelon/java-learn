import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.pojo.Human;

public class MyTest {
    @Test
    public void mtest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxc.xml");
        Human human = (Human) context.getBean("human");
        human.getDog().setWorld("F**K");
        human.getDog().action();
        human.getCat().setWorld("Miao");
        human.getCat().action();
    }
}
