# Spring Boot

### 简述

##### Spring boot:

简化Spring开发的一个框架,是对Spring技术栈开发的一个整合

##### Microservice

微服务建构,将应用拆分为一个个可独立提供服务的微服务,各个服务间通过HTTP进行通信

### 学习 Spring boot 前提

- 会使用 **spring** 框架
- 会使用 **maven**
- 会使用 **IDEA / Eclipse**

<hr>

得先学**Spring**吧

<hr>

## 1 Spring IoC (Inverse of Control)

### 1.1 导jar包

~~~xml
  <dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.0.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.1.0.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>5.1.0.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-expression</artifactId>
        <version>5.1.0.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>
    </dependency>

  </dependencies>
~~~

所需依赖都直接通过maven导入 

### 1.2 目标类 

~~~properties
💛提供接口和实现类
🧡获得实现类的实例
——————————————————————————————
普通Java开发:  需要实例 >>> new对象
SpringJava开发: 需要实例 >>> Spring创建实例对象
			 当需要用到实例对象时，从Spring的容器中获得，需要将实现类的全限定名称配置到xml文件里
~~~

### 1.3 配置文件

- 位置：任意，一般在src下
- 名称：任意，一般叫applicationContext.xml
- 内容：schema约束

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="userimpl" class="userimpl"></bean>
</beans>
~~~

### 1.4 代码实例

~~~java
public interface userInfo {
    public void helloioc();
}
————————————————————————————
public class userimpl implements userInfo {

    public void helloioc() {
        System.out.println("hello IOC");
    }
}
————————————————————————————
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void the(){
//        获得Spring 容器
         String xml = "src/main/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xml);
//        从Spring 容器内获取内容
        userimpl userimpl = (userimpl) applicationContext.getBean("userimpl");
        userimpl.helloioc();
    }

}
~~~

🖤这些代码已经废弃了，仅了解原理即可🖤

## 2 DI (dependency  Injection)

略~~~

# 正式开始学 **Spring Boot** 

