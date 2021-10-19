# Spring 注解开发

```java
public class User {
     public String name  ;
}
```

自古以来当我们创建完一个实体类以后，一般都是要去xml文件里面添加对应的 Bean 组件。但是现在不一样了！

我们可以利用注解来帮我们干这事了


## @Component

这就是现在的最新写法。但是注意！你还是需要在xml里面指定那些包下面的注解会生效。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config />
    <context:component-scan base-package="top.jokeme"/>
</beans>
```

然后只需要在实体类上加上注解`@Component`就可以了

```java
@Component
public class User {
     public String name ;
}

```

然后咱们在检验一下这种方法是不是可以，虽然这一步是多余的，因为肯定是不会出错的。但还是尝试验证一下吧。
```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxd.xml");
        User user = (User) context.getBean("user");
        user.setName("Tom");
        System.out.println(user.getName());
    }
}
```

由`@Component`的衍生注解有如下几个，并且在MVC三场中的作用都是将类交给Spring管理。只是术业有专攻。

```
dao         --- @Repository
service     --- @Service
controller  --- @Controller
```




[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P14 Spring注解开发](https://www.bilibili.com/video/BV1WE411d7Dv?p=14)