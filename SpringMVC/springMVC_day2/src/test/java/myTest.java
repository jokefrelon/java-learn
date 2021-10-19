import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.pojo.Car;

public class myTest {
    public static void main(String[] args) {
        //利用 ClassPathXmlApplicationContext 拿到 Spring 给我们创建好的容器。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("AppliicationContext.xml");

        //拿到Spring给我们的对象以后咱就 getBean("xxx") ，获取这个容器的实例 或者说拿到容器里的对象/bean。
        Car car = (Car) applicationContext.getBean("car");

        //拿到实例以后再调用这个实例的方法。
        System.out.println(car.toString());
    }
}
