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

## ① Spring IoC (Inverse of Control)

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

## ② DI (dependency  Injection)

略~~~

<hr/>
## 1. 正式开始学 **Spring Boot** 

### 1.1 创建Maven项目

打包方式选择 **jar**

~~~xml
<packaging>jar</packaging>
~~~



### 1.2 导入Spring Boot的依赖

~~~xml
    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.2.2.RELEASE</version>
            <type>pom</type>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.1.7.RELEASE</version>
        </dependency>
		<!--该插件是将Springboot项目打包为可执行的jar包 -->

    </dependencies>
~~~

### 1.3 编写一个主程序,用于启动 Spring Boot应用

~~~java
package top.jokeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication用来说明这是一个SpringBoot应用
@SpringBootApplication
public class MainOfApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainOfApplication.class,args);
    }
}
~~~

### 1.4 编写Controller & Service

~~~java
package top.jokeme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//表示这是一个Controller节点
@Controller
public class cont {
    
    @RequestMapping("/mano")//表示接受浏览器请求的mano请求
    @ResponseBody//表示SpringBoot响应浏览器的请求
    public String hel(){
    return "Hello Python";

    }
}
~~~

## 2 项目原理简析

### 2.1 pom.xml文件

要想学好 **SpringBoot** 那么底层的东西也应该了解,这个pom.xml就是在底层帮我们导依赖

~~~xml
<artifactId>spring-boot-starter-web</artifactId>
<artifactId>spring-boot-starter-parent</artifactId>
~~~

这些 **stater** 依赖和我们的项目一样,都还依赖一些更底层的 **jar** 包,那么这些更底层的 **jar** 包又由谁来帮他们™导呢???🤔

#### 2.1.1 父项目

其实,我们的 **pom.xml** 📄都是继承至™の父项目,这些更加底层的  **jar** 包就不用我们管了,这也是一种面向对象的思想吧.我们不需要再调用底层的一些接口,而是直接调用这些 **Stater** 启动器! ! Java 🐂🍺

### 2.2 主程序类,入口类

当开发 **Spring Boot** 程序时,我们最长用到的就是注解开发,连 **Spring Boot** 的入口程序也需要用注解来标识,否则会报错,如下:

~~~java
@SpringBootApplication
public class MainOfApplication { }
~~~

补充知识:

**@SpringBootApplication** 又有以下主要属性


- @SpringBootConfiguration

  标注在某个类上,表示这是 **Spring Boot** 配置类,该属性又来自 **Spring** の 	👇

  - @Configuration,( •̀ ω •́ )y是用来表示配置类の




- @EnableAutoConfiguration

  ⅰ用法:标注在某个类上,以开启该类的自动配置功能

  ⅱ原理:将主配置类(@SpringBootApplication标记的类)所在的包以及下面的子包里的所有组件都扫描到 **Spring** 容器中

  ⅲ来源:该属性可追述到它的父类注解👇

  - @import(AutoConfigurationPackages.Registrar.class)

    @import是Spring的底层注解,给容器导入一个由AutoConfigurationPackages.Registrar.class决定の组件,

- import (EnableAutoConfigurationImportSelector.class);

  给容器中导入的组件都是由EnableAutoConfigurationImportSelector所决定の,然后EnableAutoConfigurationImportSelector.class再返回◀一个类全名的数组,在该数组里的都会被导入;其效果就是给容器中导入自动配置类(xxxAutoConfiguration),并配置好这些组件

### 2.3知识点小结

以我目前对 **Spring Boot** 的理解,

⑴创建的 **Spring Boot** 项目必须要导好依赖, **pom.xml** 文件是一个小的重点 

⑵主程序类必须要用 **@SpringBootApplication** 标注,然后调用SpringApplication.run(xxx.class,arg1)来开始 **Spring Boot Application** 

⑶

------

​                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                