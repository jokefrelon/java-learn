import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.jokeme.config.config;
import top.jokeme.pojo.User;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(config.class);
        User ruser = (User) context.getBean("Ruser");
        System.out.println(ruser.getName());

    }
}
