# DI注入实例
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
    <bean id="mess" class="top.jokeme.top.jokeme.pojo.Student">
        <property name="name" value="Frelon_Lee"></property>
        <property name="fam" ref="family"></property>
        <property name="room">
            <array>
                <value>King301</value>
                <value>Julu333</value>
            </array>
        </property>
        <property name="famWeight">
            <list>
                <value>12398</value>
                <value>98789</value>
                <value>89782</value>
            </list>
        </property>
        <property name="chairModule">
            <map>
                <entry key="child1" value="Jhon"></entry>
                <entry key="child2" value="Tims"></entry>
            </map>
        </property>
        <property name="aspn">
            <set>
                <value>3.1415926x</value>
                <value>Hero Aria</value>
                <value>King of Bee</value>
            </set>
        </property>
        <property name="hash" >
            <props>
                <prop key="sex">Boy</prop>
                <prop key="job">student</prop>
            </props>
        </property>

    </bean>
    <bean id="family" class="top.jokeme.top.jokeme.pojo.family">
        <property name="fmad" value="山水大街2501 卡卡西 斯巴坦克尔 葵颐邯"></property>
    </bean>
</beans>
```

```java
//Student
public class Student {
    private String name;
    private family fam;
    private String[] room;
    private List<Integer> famWeight;
    private Map<String,String> chairModule;
    private Set<String> aspn;
    private Properties hash;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", fam=" + fam.toString()+
                ", room=" + Arrays.toString(room) +
                ", famWeight=" + famWeight +"\n"+
                ", chairModule=" + chairModule +
                ", aspn=" + aspn +
                ", hash=" + hash +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public family getFam() {
        return fam;
    }

    public void setFam(family fam) {
        this.fam = fam;
    }

    public String[] getRoom() {
        return room;
    }

    public void setRoom(String[] room) {
        this.room = room;
    }

    public List<Integer> getFamWeight() {
        return famWeight;
    }

    public void setFamWeight(List<Integer> famWeight) {
        this.famWeight = famWeight;
    }

    public Map<String, String> getChairModule() {
        return chairModule;
    }

    public void setChairModule(Map<String, String> chairModule) {
        this.chairModule = chairModule;
    }

    public Set<String> getAspn() {
        return aspn;
    }

    public void setAspn(Set<String> aspn) {
        this.aspn = aspn;
    }

    public Properties getHash() {
        return hash;
    }

    public void setHash(Properties hash) {
        this.hash = hash;
    }
}
```

```java
//family
public class family {
    private String fmad;

    public String getFmad() {
        return fmad;
    }

    @Override
    public String toString() {
        return "family{" +
                "fmad='" + fmad + '\'' +
                '}';
    }

    public void setFmad(String fmad) {
        this.fmad = fmad;
    }
}
```

```java
//MyTest
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.jokeme.top.jokeme.pojo.Student;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxa.xml");
        Student student = (Student) context.getBean("mess");
        System.out.println(student.toString());
    }
}
```
从上面的代码里面可以看到，spring可以支持各种类型，咱们只需要在xml里面配置好，咱们在代码里面直接就可以

获取到实例了，咱们不需要一个一个new 对象后再慢慢的一个一个赋值了。

[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P9 依赖注入之set注入](https://www.bilibili.com/video/BV1WE411d7Dv?p=9)

---

然后C命名空间和P命名空间我就跳过了，几乎用不到。


[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P10 c命名和p命名空间注入](https://www.bilibili.com/video/BV1WE411d7Dv?p=10)

---
单例模式（singleton）：默认情况下，我们的spring程序是单例模式，也就是咱们无论getBean()多少次，它返回给咱们的都是一个对象

原型模式（prototype）：就是俺们getBean()一次，它就给咱们返回一个新的对象。

```xml

<bean id="mess" class="top.jokeme.top.jokeme.pojo.Student" scope="singleton"/>
<bean id="mess" class="top.jokeme.top.jokeme.pojo.Student" scope="prototype"/>
```
[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P11 Bean的作用域](https://www.bilibili.com/video/BV1WE411d7Dv?p=11)

