import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.jokeme.config.config;
import top.jokeme.service.UserService;
import top.jokeme.service.serviceImpl.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(config.class);
        UserServiceImpl impl = (UserServiceImpl) context.getBean("getNewUserServiceImpl");
        impl.add();
    }
}
