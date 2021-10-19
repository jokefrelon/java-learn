# Bean的自动装配

Spring有3种装配模式
- xml里面配置
- Java类里面配置
- 隐式自动装配（以后最常用的）


按照咱们目前所学的知识，已经可以利用xml来注入bean了，也就是下面的这种。

```xml

<bean id="Human" class="top.jokeme.top.jokeme.pojo.Human">
    <property name="hum1" value="Frelon_Lee"></property>
    <property name="dog1" ref="Dog"></property>
    <property name="cat1" ref="Cat"></property>
</bean>
<bean id="Cat" class="top.jokeme.top.jokeme.pojo.Cat"></bean>
<bean id="Dog" class="top.jokeme.top.jokeme.pojo.Dog"></bean>
```

但是人总是在进步的嘛！咱们要学更先进的方法了

## 1. 自动注入 - autowire

### 1.1 ByName

```xml

<bean id="cat" class="top.jokeme.top.jokeme.pojo.Cat"></bean>
<bean id="dog" class="top.jokeme.top.jokeme.pojo.Dog"></bean>

<bean id="Hum" class="top.jokeme.top.jokeme.pojo.Human" autowire="byName">
<property name="human" value="Frelon_Lee"></property>
</bean>
```
咱给Bean加上 **autowire** 属性，并且还是`ByName`的

```java
public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxb.xml");
        Human bean = (Human) context.getBean("Hum");
        bean.getDog().setWorld("F**K");
        bean.getDog().action();
    }
}
```

这里需要注意的是ByName是大小写敏感的，如果你`<bean id="cat" class="top.jokeme.top.jokeme.pojo.Cat"></bean>`

里面的 **id** 为大写，但是你实体类里面的变量为小写，那么恭喜你喜提空指针异常。

### 1.2 ByType
ByType有一个好处，就是当你只需要用到同一类型一次的时候非常好用，但是当这种类型出现了两次及以上，就会直接报错

```xml

<bean id="catxxx" class="top.jokeme.top.jokeme.pojo.Cat"></bean>
<bean id="dogxxx" class="top.jokeme.top.jokeme.pojo.Dog"></bean>

<bean id="Hum" class="top.jokeme.top.jokeme.pojo.Human" autowire="byType">
<property name="human" value="Frelon_Lee"></property>
</bean>
```

就像上面，咱们把id变成了dogxxx，但是它的类型仍然为Dog，所以程序仍然可以正确运行✅。
你甚至可以省略id不写，因为就它一个，没有别的。


[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P12 自动装配Bean](https://www.bilibili.com/video/BV1WE411d7Dv?p=12)






