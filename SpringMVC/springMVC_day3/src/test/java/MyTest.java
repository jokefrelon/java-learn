import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.pojo.User;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptc.xml");
        User user = (User) context.getBean("User");
        System.out.println(user.toString());
    }
}
