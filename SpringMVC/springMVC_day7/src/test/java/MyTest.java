import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.dao.User;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxd.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.getName());
        user.setName("Tom");
        System.out.println(user.getName());
    }
}
