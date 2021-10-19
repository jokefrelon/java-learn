# 基于注解的自动装配

首先需要导入约束，idea 生成的约束只适用于springboot好像，

然后记得添加 `<context:annotation-config />` 注解支持

## @Autowire


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

    <bean id="cat" class="top.jokeme.top.jokeme.pojo.Cat"></bean>
    <bean id="dog" class="top.jokeme.top.jokeme.pojo.Dog"></bean>

    <bean id="human" class="top.jokeme.top.jokeme.pojo.Human"></bean>
</beans>
```

然后在java文件的实体类上也需要加上`@autowire`注解。

```java
public class Human {

    @Autowired
    private Dog dog;

    @Autowired
    private Cat cat;

    public Dog getDog() { return dog; }

    public void setDog(Dog dog) { this.dog = dog; }

    public Cat getCat() { return cat; }

    public void setCat(Cat cat) { this.cat = cat; }
}
```

```java
public class MyTest {
    @Test
    public void mtest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aptxc.xml");
        Human human = (Human) context.getBean("human");
        human.getDog().setWorld("F**K");
        human.getDog().action();
        human.getCat().setWorld("Miao");
        human.getCat().action();
    }
}
```

如果使用 `@Autowire` 的环境比较复杂，无法通过一个注解就完成自动装配的时候，就可以使用注解：`@Qualifier(value="xxx")`来配合Autowire。


## @Resource
作用和 `@Autowire` 差不多的，也可以自动装配，它自动装配的过程是先通过变量名，再然后是变量类型。就和上面的ByName，ByType差不多。

假设现在咱们的代码需求如下

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

    <bean id="cat1" class="top.jokeme.top.jokeme.pojo.Cat"></bean>
    <bean id="dog1" class="top.jokeme.top.jokeme.pojo.Dog"></bean>

    <bean id="dog232" class="top.jokeme.top.jokeme.pojo.Dog"></bean>

    <bean id="human" class="top.jokeme.top.jokeme.pojo.Human"></bean>
</beans>
```

```java
public class Human {

    @Resource
    private Dog dog;

    @Resource
    private Cat cat;

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
```

不出意外的话，就会报错 `NoUniqueBeanDefinitionException`

这是因为 dog类不唯一造成的，并且也没有为`@Resource`指定变量名。只需要把代码改成下面这样的

```java
@Resource(name="dog232")
private Dog dog;
```


[【狂神说Java】Spring5最新完整教程IDEA版通俗易懂 P13 注解实现自动装配](https://www.bilibili.com/video/BV1WE411d7Dv?p=13)




