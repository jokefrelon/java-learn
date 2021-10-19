import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.dao.UserDaoMariadbImpl;
import top.jokeme.dao.UserDaoMongoImpl;
import top.jokeme.dao.UserDaoMysqlImpl;
import top.jokeme.service.UserServiceImpl;

public class myTest {
    public static void main(String[] args) {

//        用户只需要调用service层，不用接触dao层
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.getUser();

//----------------------------------------------------------------------------------------------------------------
//        UserServiceImpl userService = new UserServiceImpl();
//
//        userService.setUserDao(new UserDaoMongoImpl());
//        userService.getSql();
//
//        userService.setUserDao(new UserDaoMariadbImpl());
//        userService.getSql();
//
//        userService.setUserDao(new UserDaoMysqlImpl());
//        userService.getSql();
//        这个方法就是利用了setter来适应用户的不同需求。 现在的控制权已经由程序转移到了用户手上了。这就是IOC的精髓，也是早期的原型。
//----------------------------------------------------------------------------------------------------------------


        //利用 ClassPathXmlApplicationContext 拿到 Spring 给我们创建好的容器。
        ApplicationContext context = new ClassPathXmlApplicationContext("apct.xml");

        //拿到Spring给我们的对象以后咱就 getBean("xxx") ，获取这个容器的实例 或者说拿到容器里的对象/bean。
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");

        //拿到实例以后再调用这个实例的方法。
        userServiceImpl.getSql();

        // 这样做的好处就是咱们代码写好了以后，用户需求有变，咱们只需要专心添加用户的需求即可，不需要再动别的地方的代码，代码的耦合性大大降低了。
        // 而用户这里只需要改成他们需要的对象执行即可。
    }
}
