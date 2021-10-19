# 使用Config类来配置

```java
public class User {

    @Value("jjj")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
@Configuration
public class config {

    @Bean
    public User Ruser(){
        return new User();
    }
}
```

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(config.class);
        User ruser = (User) context.getBean("Ruser");
        System.out.println(ruser.getName());

    }
}
```

这里说一下哈，那个User类，秦疆老师说需要在类上加注解：`@Component`，但是实际上不加也是可以的，
因为config类里面的getUser()方法返回的是一个 **new User**，所以加不加都不影响程序运行。我看弹幕里面有的人说
加这个注解是为了用 **@Value** 注解，然后根据咱们代码可以得知，没有半毛钱关系。

然后我看弹幕里面说 config类里面的注解：**@Configuration**也不用加，试了一下，这个是真的。我个人的理解是，给
getUser加上 `@Bean` 注解以后，就相当于在xml里面配置了User类。然后咱们后面的 MyTest里面是直接
`new AnnotationConfigApplicationContext(config.class)` 拿到了Spring创建的容器。
Configuration起到了标识的作用，点进去可以看到注解内部如下。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
    
}
```

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P15 使用JavaConfig来配置](https://www.bilibili.com/video/BV1WE411d7Dv?p=15)