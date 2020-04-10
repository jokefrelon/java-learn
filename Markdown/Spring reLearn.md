# Spring学习

## 1. 配置文件pom.xml



~~~xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <java.version>1.8</java.version>
</properties>
<!-- 首先配置一下基本的文件编码和Java版本 -->

<dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
<!-- 提供一些 mvc，aop 的依赖包，这是玩Spring-boot必备的依赖包 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.2.2.RELEASE</version>
            <type>pom</type>
        </dependency>
<!--  这个依赖是帮助我们自动选择一些依赖最合适的版本，这个是玩Spring-Boot必备的 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.1.7.RELEASE</version>
        </dependency>
<!--该插件是将Springboot项目打包为可执行的jar包 -->
</dependencies>
~~~



## 2. 建立一个Controller类，和Starter类

~~~java
package top.jokeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
    }
}
#~~~~~~~~~~~~~~~~
package top.jokeme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/word")
    public String restr(){
        return "<p style='color:red;text-align:center'>Awesome Java</p>";
    }
}
~~~

这就是一个最简单的**SpringBoot** 应用程序

提醒⏰:idea建立项目是必须要改的jdk版本有那几处,要不然在运行的时候会报一些错

1.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Project >>> Project SDK:
2.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Project >>> Project language level
3.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Modules >>> Sources >>> Languages level
4.  File >>> Settings(CTRL+ALT+S) >>> Build,Execution,Deployment >>> Compiler >>> Java Compiler >>> Project bytecode version
5.  File >>> Settings(CTRL+ALT+S) >>> Build,Execution,Deployment >>> Compiler >>> Java Compiler >>>Per-module bytecode version

由于**@SpringBootApplication** 有一个默认的**ComponentScan**注解指定了扫描包の方式,所以在我们需要DIY扫包方式的时候就需要用 **@ComponentScan** 来指定我们需要扫描的包

eg: **@ComponentScan("top.jokeme.ayibe")** 指定扫描 **ayibe** 这个包....如果指定以后就不会 使用默认的扫描方式,只会扫描指定の包

~~~java
package top.jokeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ComponentScan("top.jokeme.ayibe")
@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
    }
}
~~~

