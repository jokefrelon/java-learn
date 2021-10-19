import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.pojo.Student;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxa.xml");
        Student student = (Student) context.getBean("mess");
        System.out.println(student.toString());
    }
}
