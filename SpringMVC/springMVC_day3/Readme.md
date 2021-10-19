# 探索IOC底层原理
通过前面的代码我们已经知道了**IOC**是利用 **set**方法进行注入的，那么更详细的过程呢？

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
    <bean id="User" class="top.jokeme.top.jokeme.pojo.User">
        <property name="name" value="Liu-Jun-Nan"></property>
    </bean>
</beans>
```

```java
//User
public class User {
    private String Name;
    
    public User() {
        System.out.println("User 调用了无参构造");
    }

    @Override
    public String toString() { return "User{" + "Name='" + Name + '\'' + '}'; }

    public String getName() {return Name; }

    public void setName(String name) { Name = name; }
}

```

```java
//MyTest
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptc.xml");
        User user = (User) context.getBean("User");
    }
}

```
这就是一个典型的利用无参构造来注入的例子，getBean() 以后就会获取到容器里面对象的实例。

---

第二种就是利用有参构造

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
    <bean id="User" class="top.jokeme.top.jokeme.pojo.User">
        <constructor-arg name="name" value="linus"></constructor-arg>
    </bean>
</beans>
```
这种有参构造就是咱们需要才创建对象时给它指定一个参数让它可以实例化成功。

constructor-arg 的实例化方式有 `index type name`,
一般情况下，咱们不经常用到有参构造，掌握，知道咋用就可以了。

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P6 IOC创建对象的方式](https://www.bilibili.com/video/BV1WE411d7Dv?p=6)

---
Spring bean 配置简介

```xml
<alias name="User" alias="xc"></alias>
```
一般是给实体类起别名用的，了解即可。


```xml
<import resource="aptc.xml"></import>
<import resource="apct.xml"></import>
```
这个一般是团队开发用的多，或者说大项目里面用的比较多，需要的配置多，放在一个配置文件里面显然不太利于修改

所以就放在多个配置文件里面，然后在一个总的配置文件里面 `import` 即可。

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P7 Spring配置说明](https://www.bilibili.com/video/BV1WE411d7Dv?p=7)

