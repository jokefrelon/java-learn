 # IOC简介及其精髓
```java
//UserDao
public interface UserDao {
    public void getUser();
}
```

```java
//UserService
public interface UserService {
    public void getSql();
}
```

```java
UserDaoMariadbImpl implements UserDao
UserDaoMysqlImpl implements UserDao 
UserDaoMongoImpl implements UserDao

UserServiceImpl implements UserService
```

```java
//UserServiceImpl
private UserDao userDao;

public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
    //  先定义好一个UserDao，利用setter()进行动态赋值 ✅
@Override
public void getSql() {
        userDao.getUser();
        }
```

下面是测试代码中的主要内容。

```java
//myTest
public class myTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoMongoImpl());
        userService.getSql();
    }
}
```
现在的控制权已经由程序转移到了用户手上了。这就是IOC的精髓，也是早期的原型。

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P3 IOC理论推导](https://www.bilibili.com/video/BV1WE411d7Dv?p=3)

---

**看完module2再看下面的内容更好理解**

---

现在咱们可以用xml配置文件来创建 Spring bean/对象

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
    <bean id="MysqlImpl" class="top.jokeme.dao.UserDaoMysqlImpl"></bean>
    <bean id="MongoImpl" class="top.jokeme.dao.UserDaoMongoImpl"></bean>
    <bean id="MariadbImpl" class="top.jokeme.dao.UserDaoMariadbImpl"></bean>

    <bean id="UserServiceImpl" class="top.jokeme.service.UserServiceImpl">
        <!-- 这里property 不能直接用value 而是要用 ref 去指向一个容器中已经创建好的bean/对象 来调用 -->
        <property name="userDao" ref="MysqlImpl"></property>
    </bean>
</beans>
```
上面一样的代码，现在加上了这个xml以后咱们就可以直接在容器里面获取对象
```java
//myTest
public class myTest {
    public static void main(String[] args) {
        
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
```

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P5 HelloSpring](https://www.bilibili.com/video/BV1WE411d7Dv?p=5)