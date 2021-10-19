# IOC实际运用
```java
//Car 
public class Car {
    private String module;
    private String price;
    
    @Override
    public String toString() { return "Car{" + "module='" + module + '\'' + ", price='" + price + '\'' + '}'; }

    public String getModule() { return module; }

    public void setModule(String module) { this.module = module; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }
}

```

```xml
<!--    使用Spring的xml配置文件来创建对象 也就是说 bean = Object（对象） -->
<!--    一个bean就相当于 new 一个对象 ，id = 变量名 ，property 的 name & value = 对变量赋值 -->
<bean id="car" class="top.jokeme.top.jokeme.pojo.Car">
    <property name="module" value="Tesla Module 3"></property>
    <property name="price" value="270,000¥"></property>
</bean>
```

```java
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
``` 


这个过程就叫做控制反转（`IOC`），对象的创建管理都交给了spring，我们只需要配置好了xml直接调用即可。
现在的程序本身不会再去创建对象了，只需要接收对象调用即可。

依赖注入：其实就是利用set方法注入的。


下面是ApplicationContext -> ClassPathXmlApplicationContext 的路线
```
ApplicationContext -> ConfigurableApplicationContext -> AbstractApplicationContext -> AbstractRefreshableApplicationContext -> AbstractRefreshableConfigApplicationContext -> AbstractXmlApplicationContext -> ClassPathXmlApplicationContext
```
[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P5 HelloSpring](https://www.bilibili.com/video/BV1WE411d7Dv?p=5)

