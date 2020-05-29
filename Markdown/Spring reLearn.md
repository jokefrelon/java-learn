# Spring学习

提醒⏰:在开始之前,我们需要配置以下基本环境(还有文件的编码方式也要注意)

1.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Project >>> Project SDK:

2.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Project >>> Project language level
3.  File >>> Project Structcture(CTRL+ALT+Shift+S) >>> Modules >>> Sources >>> Languages level
4.  File >>> Settings(CTRL+ALT+S) >>> Build,Execution,Deployment >>> Compiler >>> Java Compiler >>> Project bytecode version
5.  File >>> Settings(CTRL+ALT+S) >>> Build,Execution,Deployment >>> Compiler >>> Java Compiler >>>Per-module bytecode version

## 1. 配置文件 <pom.xml>

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

**spring-boot-starter-parent** 是版本仲裁者,所以我们在导入依赖时,可以省略一些依赖的版本号

**spring-boot-starter-web** web 模块的场景启动器组件,还有别的一些 **starter** 都是被 **Sprint boot** 抽取出来的,以后在项目里只需导入这些启动器就可以开箱即用

## 2. 建立Controller & Starter

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

这就是一个最简单的 **SpringBoot** 应用程序


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

**@SpringBootApplication** 该注解就标识着 **Springboot** 的主配置类,启动类,从这里的 **main** 方法开始执行

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
    ......
}
```

注: **@import** 是 **SpringBoot** の底层注解.其作用就是给容器导入组件

### 2.1 需要注意两个比较重要的注解

#### 2.1.1 Registrar.class

```
@SpringBootApplication 中的 
@EnableAutoConfiguration 中的 
@AutoConfigurationPackage 中的
@Import({Registrar.class})
```

其中 Registrar.clss就是

```java 
static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
        Registrar() {
        }

        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
            AutoConfigurationPackages.register(registry, (new AutoConfigurationPackages.PackageImport(metadata)).getPackageName());
        }
//这个方法就是将标记了@SpringBootApplication的类,及其子包的所有组件扫描到Spring容器里面去
    
        public Set<Object> determineImports(AnnotationMetadata metadata) {
            return Collections.singleton(new AutoConfigurationPackages.PackageImport(metadata));
        }
```

#### 2.1.2 AutoConfigurationImportSelector

```
@SpringBootApplication 中的 
@EnableAutoConfiguration 中的 
@Import({AutoConfigurationImportSelector.class})
```

**AutoConfigurationImportSelector** 就是导入哪些组件的选择器,并且会将需要导入的组件以全类名の方式返回,然后这些组件就会被添加到容器之中;

在这个过程中,会导入非常多的自动配置类诸如 [ xxxAutoConfiguration ] 等,目的就是给容器导入这些场景所需的所有组件,并自动配置好

上 **AutoConfigurationImportSelector** 代码片段:

```java
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        } else {
            AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
            AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this.getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);
            return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
        }
    }
    protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AutoConfigurationMetadata autoConfigurationMetadata, AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return EMPTY_ENTRY;
        } else {
            AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
            List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
            configurations = this.removeDuplicates(configurations);
            Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
            this.checkExcludedClasses(configurations, exclusions);
            configurations.removeAll(exclusions);
            configurations = this.filter(configurations, autoConfigurationMetadata);
            this.fireAutoConfigurationImportEvents(configurations, exclusions);
            return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
        }
    }
```

```java
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```

然后就用到了 **SpringFactoriesLoader.loadFactoryNames** 

```java
    public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        String factoryTypeName = factoryType.getName();
        return (List)loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
    }
    private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
        MultiValueMap<String, String> result = (MultiValueMap)cache.get(classLoader);
        if (result != null) {
            return result;
        } else {
            try {
                Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
                LinkedMultiValueMap result = new LinkedMultiValueMap();

                while(urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    UrlResource resource = new UrlResource(url);
                    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                    Iterator var6 = properties.entrySet().iterator();

                    while(var6.hasNext()) {
                        Entry<?, ?> entry = (Entry)var6.next();
                        String factoryTypeName = ((String)entry.getKey()).trim();
                        String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                        int var10 = var9.length;

                        for(int var11 = 0; var11 < var10; ++var11) {
                            String factoryImplementationName = var9[var11];
                            result.add(factoryTypeName, factoryImplementationName.trim());
                        }
                    }
                }

                cache.put(classLoader, result);
                return result;
            } catch (IOException var13) {
                throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var13);
            }
        }
    }
```

**SpringBoot** 在启动的时候,主要就是从类路径下的 **META-INF/factories** 里获取  **EnableAutoConfiguration** 指定的值,并将这些值作为自动配置类导入容器中,然后自动配置类生效,帮我们实现自动配置的工作

这些都归功于:

**spring-boot-autoconfigure-2.2.2.RELEASE.jar**


注:

~~~properties
Part 2.1的一些内容可能与 Part 3 的内容有冲突,以 Part 3 为准,Part 2.1有点老
~~~



## 3. 自动配置原理

**application.properties** 配置文件里面都可以配置什么内容?

SpringBoot在启动的时候会加载主配置类,这个主配置类就是我们添加了注解 [ **@SpringBootApplication** ]的类

并且为这个类开启了自动配置功能 (继承至注解:**@SpringBootApplication** の注解 **@EnableAutoConfiguration**)

*小技巧 :在使用Idea的时候,我们想要看导入的一些第三方代码的源码可以按 ALT+CTRL+鼠标点击你要看源码的 类/接口/注解...*

~~~java
package org.springframework.boot.autoconfigure;

import ......;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
......
}
~~~
### 3.1 @EnableAutoConfigurationの作用:

-   使用 **AutoConfigurationImportSelector** 给容器导入相关组件

-   虽然没太看懂 **selectImports** 代码,但是我猜大概的意思是:

-   依赖 **AutoConfigurationMetadataLoader** 来导入 **META-INF** 下面的 **properties** 文件

    详细解释可以看这个 **[Other+](https://www.cnblogs.com/milicool/p/11718099.html)** 的文章

~~~java
public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        } else {
            AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
            AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this.getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);
            return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
        }
    
~~~

-   所有可以在配置文件中配置的属性,都在xxxProperties类中

由于**@SpringBootApplication** 有一个默认的**ComponentScan**注解指定了扫描包の方式,所以在我们需要DIY扫包方式的时候就需要用 **@ComponentScan** 来指定我们需要扫描的包

eg: **@ComponentScan("top.jokeme.ayibe")** 指定扫描 **ayibe** 这个包....如果指定以后就不会 使用默认的扫描方式,只会扫描指定の包

## 4. YAML & Properties

#### 4.1. yaml基本语法:

```yaml
K: V
##############
K:
    V1: 443
    V2: sew
```

yaml是大小写敏感的,并且需要注意缩进和空格

注意:yaml里面

```yaml
" " & ' ' 作用不一样
```

 " "会转义字符串,比如 \n 等

' ' 不会转义字符串,输入啥,输出啥

#### 4.2. yamlの 对象

```yaml
food:
    name: apple
    tasty: true
####################
food: {name: apple,tasty: true}
```

#### 4.3. yamlの数组

```yaml
interesting:
 - 13
 - 14
 - 15
#####################
interesting: [13,14,15]
```

#### 4.4. yaml文件 & 数据绑定

~~~yaml
person:
  sname: zhangsan
  sage: 18
  Sod: 'AnHui Provience'
  Lovelydog: {age: 13,name: lisi}
~~~

```java
package top.day2_yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
}
```

如果不出意外:

```java
#localhost:8080/se 
#会出现以下效果
Person{sname='zhangsan', sage=18, Sod='AnHui Provience', Lovelydog=dog{age=13, name='lisi'}}
```

注意1:**@Component** 注解是帮助绑定值的一个注解,所以不能忘记添加该注解

注意2:这里可能需要导入以下依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-configuration-processor</artifactId>
  <version>2.3.0.RELEASE</version>
</dependency>
<!-- 配置文件处理模块,配置文件的数据绑定就依赖该模块 -->
```

#### 4.5. Properties

**properties** の用法大致与 **yaml** 差不多,但是比 **yaml** 费事一点点

```properties
person.sname=zhangsan
person.sage=18
person.Sod='AnHui Provience'
person.lovelydog.age=13
person.lovelydog.name=lisi
server.port=8088
```

∵ **Properties** 是大小写不敏感的

∴ **person.age** = **person.Age**

## 5. @Value

#### 5.1 **@Value** 使用方法

```java
package top.day3_atValue;

import org.springframework.beans.factory.annotation.Value;

public class human {
    @Value("Person")
    String Typeof;
}
```



#### 5.2 **@ConfigurationProperties & @Value** の区别

|                  |   @ConfigurationProperties   |     @Value     |
| :--------------: | :--------------------------: | :------------: |
|     **特征**     | **批量注入配置文件里的属性** | **一个个指定** |
|   **松散语法**   |           **😁YES**           |    **😭NO**     |
|     **SpEL**     |           **😭NO**            |    **😁YES**    |
|    **JSR303**    |           **😁YES**           |    **😭NO**     |
| **复杂类型封装** |           **😁YES**           |    **😭NO**     |

eg: 复杂类型封装就是指只, map/list/...

#### 5.3 我们在何时使用他们

如果我们只是某项业务逻辑中需要获取某个属性,推荐使用 

如果我们是需要进行大规模配置文件,就要使用 **@ConfigurationProperties**

## 6. @PropertySource & @ImportResource

#### 6.1 @PropertySource使用方法

```java
package top.day3_atValue;

import org.springframework.context.annotation.PropertySource;

@PropertySource(value={"classpath:per.properties"})
public class human {
    
}
```



*@PropertySource 加载指定☞的配置文件*

*@ConfigurationProperties 默认获取全局の配置文件里的属性*

⚠注意: **@PropertySource** 必须和 **@ConfigurationProperties** 一起使用才有效,单独使用没有效果

#### 6.2 @ImportResource使用方法

其作用是导入 **Spring** 里面的配置文件并让其生效

```java
@ImportResource(locations = {classpath:"abc.xml"})
```

## 7. 配置文件占位符

先来一个实例:

```properties
person.sname=${random.uuid}
person.sage=${random.int}
person.Sod='AnHui Provience'
person.lovelydog.age=${person.lol:233}
person.lovelydog.name=lisi
server.port=8080
```

这就是用随机数 $random 来生成的,还有用占位符生成の

**${person.lol:233}** の意思就是 有person.lol就用它,没有就用233

```
Person{sname='d573e71a-f8b2-4a48-a688-10449b4b55fc', sage=-1817647899, Sod=''AnHui Provience'', Lovelydog=dog{age=233, name='lisi'}}
```

## 8. Profile文件支持

#### 8.1 多Profile文件

##### 8.1.1 properties

可以用多个带有 **application-{env}.properties** 来切换环境

注: 默认的profile是 **application.properties** 

如果需要切换生产环境,可以在 **application.properties**  里面配置

```properties
spring.profiles.active=env
```

```properties
dog2.SunDay=24
dog2.Example.k1=sim
dog2.Example.k2=sib
dog2.Friends={lisi,zhangsan}
dog2.soul.Weight=30
dog2.soul.Data=35
dog2.soul.Power=80
dog2.soul.Lovely=true
spring.profiles.active=ssr

person.sname=${random.uuid}
person.sage=${random.int}
person.Sod='AnHui Provience'
person.lovelydog.age=${person.lol:233}
person.lovelydog.name=lisi
server.port=8080
spring.profiles=ssr
```

##### 8.1.2 yaml

如果配置文件是 **yaml** 我们还可以用更为简便の多文档块の方式

这里就涉及到了基本の yaml 语法 **- - -** 这种连续三个 **-** 就表示文本块,看操作:

```yaml
person:
  sname: zhangsan
  sage: 18
  Sod: 'AnHui Provience'
  Lovelydog: {age: 13,name: lisi}
spring:
  profiles: 
    active: ssr
---
person:
  sname: ${random.uuid}
  sage: ${random.int}
  Sod: 'AnHui Provience'
  Lovelydog: {age: 13,name: lisi}
server.port: 8088
spring:
  profiles: ssr
```

## 9 SpringBoot配置文件の加载顺序

首先关心一下,配置文件放哪里才会被加载

|   **1.file:./config**    |   **2.file:/**    |
| :----------------------: | :---------------: |
| **3.classpath:/config/** | **4.classpath:/** |

按照顺序,1-4按顺序扫描,然后1-4の优先级依次降低,当配置文件里的属性冲突时,以优先级高的配置文件为准

还有一点需要注意的就是,这些文件都会被加载,形成互补配置

#### 9.1 外部配置文件加载顺序

```php
1、命令行参数：所有的配置都可以在命令行参数中指定，每个配置项前使用--，多个配置间使用空格隔开，例如：

java -jar spring-boot-02-0.0.1-SNAPSHOT.jar --server.port=8088 --server.context-path=boot
2、来自java:comp/env的JNDI属性

3、java的系统属性(System.getProperties(""))

4、操作系统环境变量

5、RandomValuePropertySource配置的random.*属性值

6、jar包外部的application-{profile}.properties或application-{profile}.yml(带spring.profile配置)

7、jar包内部的application-{profile}.properties或application-{profile}.yml(带spring.profile配置)

8、jar包外部的application.properties或application.yml(不带spring.profile配置)

9、jar包内部的application.properties或application.yml(不带spring.profile配置)

10、@Configuration注解类上的@PropertySource

11、通过SpringApplication.setDefaultProperties()指定的默认属性

注：

    ①以上配置文件的优先级顺序由高到低，高优先级的覆盖低优先级的并形成互补

    ②6、8所指的jar包外指的是和jar包同一个文件夹下
```





































