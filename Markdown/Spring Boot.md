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

  ⅱ原理:将主配置类( **@SpringBootApplication** 标记的类)所在的包以及下面的子包里的所有组件都扫描到 **Spring** 容器中

  ⅲ来源:该属性可追述到它的父类注解👇

  - **@import(AutoConfigurationPackages.Registrar.class)**

    @import是Spring的底层注解,给容器导入一个由 **AutoConfigurationPackages.Registrar.class** 决定の组件,

- **import (EnableAutoConfigurationImportSelector.class)**;

  给容器中导入的组件都是由EnableAutoConfigurationImportSelector所决定の,然后**EnableAutoConfigurationImportSelector.class** 再返回◀一个类全名的数组,在该数组里的都会被导入;其效果就是给容器中导入自动配置类(xxxAutoConfiguration),并配置好这些组件

### 2.3知识点小结

以我目前对 **Spring Boot** 的理解,

⑴创建的 **Spring Boot** 项目必须要导好依赖, **pom.xml** 文件是一个小的重点 

⑵主程序类必须要用 **@SpringBootApplication** 标注,然后调用SpringApplication.run(xxx.class,arg1)来开始 **Spring Boot Application** 

⑶**Spring Boot** 在启动的时候从类路径下的 **META-INF/spring.factories** 文件中获取 **EnableAutoConfiguration** 指定的值,将这些值作为自动配置类导入到容器中,自动配置类就生效,帮我们进行自动配置

所以这个 **spring-boot-autoconfigure-2.2.2.RELEASE.jar** 是我们开发中最(❤ ω ❤)喜欢的JavaEE开发 jar 包

------

## 3 专注于Controller的开发                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      

~~~java
@Controller
public class cont {
    @ResponseBody
    @RequestMapping("/mano")
    public String hel() {
        return "awesome TypeScript";
    }
}
~~~

当我们在写 **Controller**时,需要给该类加上一个 **@controller** の注解,如果该类的方法中有多个响应浏览器请求的方法,那我们就可以把写在方法上的注解写在类上,通过继承来简化开发

~~~java
@Controller
@ResponseBody
public class cont {
    @RequestMapping("/mano")
    public String hel() {
        return "awesome TypeScript";
    }
}
//------------也可以用下面这种方法------------//
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController		//@ResponseBody属于 RESTAPI
public class cont {
    @RequestMapping("/mano")
    public String hel() {
        return "awesome TypeScript";
    }
}
~~~

## 4 Spring Boot配置



### 4.1 配置文件

①application.properties

②application.yml

主要就是修改**Spring Boot** 自动配置的默认值, 其中 **YAML** 是以数据为中心,更适合做配置文件 , 来对比一下两种配置文件の区别🎯

~~~yaml
 seserver :
	port : 088
~~~

~~~xml
<?xml version="1.0" encoding="gb2312"?>
<server>
    <port>8088</port>
</server>
~~~

可以看出啊,**yml** 更加省时省力,而且所占的资源也更少

#### 4.1.1 YAML语法

eg: **Key : Value**

以缩进来控制层级关系,冒号左右需要有一个空格,大小写敏感

##### yaml 存储对象

~~~yaml
fruit :
	apple : yes
	fishes : no
	banana : yes
#---------------------
#行内式写法
fruit : {apple : yes,banana:yes}
~~~

##### **yaml** 存数组(List,Set):

~~~yaml
pets :
	- cat
	- dog
#---------------------
pets : [cat,dog]
~~~

### 4.2 从yml文件中读取配置

略```,老师用的是专业版,我用的社区版,没有该功能

### 4.3 此处插播一个 Error

~~~properties
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.2.RELEASE)

2020-01-16 18:42:40.977  INFO 7476 --- [           main] top.jokeme.startApp                      : Starting startApp on Frelon with PID 7476 (Z:\Java_Project\webapps\target\classes started by Frelon in Z:\Java_Project\webapps)
2020-01-16 18:42:40.988  INFO 7476 --- [           main] top.jokeme.startApp                      : No active profile set, falling back to default profiles: default
2020-01-16 18:42:45.065  INFO 7476 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-01-16 18:42:45.105  INFO 7476 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-01-16 18:42:45.105  INFO 7476 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.29]
2020-01-16 18:42:45.406  INFO 7476 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-01-16 18:42:45.406  INFO 7476 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3717 ms
2020-01-16 18:42:45.426 ERROR 7476 --- [           main] o.s.b.web.embedded.tomcat.TomcatStarter  : Error starting Tomcat context. Exception: org.springframework.beans.factory.BeanCreationException. Message: Error creating bean with name 'formContentFilter' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.web.servlet.filter.OrderedFormContentFilter]: Factory method 'formContentFilter' threw exception; nested exception is java.lang.NoClassDefFoundError: Could not initialize class com.fasterxml.jackson.databind.ObjectMapper
2020-01-16 18:42:45.556  INFO 7476 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2020-01-16 18:42:45.566  WARN 7476 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
2020-01-16 18:42:45.576  INFO 7476 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-01-16 18:42:45.596 ERROR 7476 --- [           main] o.s.boot.SpringApplication               : Application run failed

~~~

我也不知道怎么就出现了这个问题,就很突然,前一秒还可以正常运行,后一秒就报错了

















